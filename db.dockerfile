FROM postgres:13.2

# install dos2unix to fix file encode
RUN apt-get update && \
  apt-get install -y dos2unix

# copy initdb.sh do docker entrypoint init
COPY initdb.sh /docker-entrypoint-initdb.d/initdb.sh

# encoding initdb.sh to unix encode
RUN dos2unix /docker-entrypoint-initdb.d/initdb.sh

# removing the package and its lists from the image
RUN apt-get --purge remove -y dos2unix &&\
  rm -rf /var/lib/apt/lists/*