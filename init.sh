#!/bin/sh
set -e

echo "Creating user: pgadmin ..."
$POSTGRES <<EOSQL
CREATE USER pgadmin WITH SUPERUSER PASSWORD 12345;
COMMIT;
EOSQL

echo "Creating database: transferdb ..."
$POSTGRES <<EOSQL
CREATE DATABASE transferdb;
GRANT ALL PRIVILEGES ON DATABASE transferdb TO pgadmin;
COMMIT;
EOSQL

echo "Creating schema: transfer ..."
psql --variable ON_ERROR_STOP=1 --username "pgadmin" --dbname "transferdb" --command "CREATE SCHEMA transfer; GRANT ALL PRIVILEGES ON SCHEMA transfer TO pgadmin;"