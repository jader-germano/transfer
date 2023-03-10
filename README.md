<h1 align="center">Welcome to Docker Compose Java Server with Postgres 👋</h1>
<p>
  <a href="#" target="_blank">
    <img alt="Version" src="https://img.shields.io/badge/version-1.0-blue.svg?cacheSeconds=2592000" />
  </a>
  <a href="https://twitter.com/jaderphilipe" target="_blank">
    <img alt="Twitter: jaderphilipe" src="https://img.shields.io/twitter/follow/jaderphilipe.svg?style=social" />
  </a>
</p>

> Project created for practice publish a 
> Spring-boot Application with Java 17, docker and docker-compose

[//]: # (### 🏠 [Homepage]&#40;https://&#41;)

[//]: # (### ✨ [Demo]&#40;https://&#41;)


## Build TransferApi Postgres Image

```sh
docker-compose -f .\docker-compose.db.yml build --no-cache
```
--no-cache *optional. 
## Start TransferApi Postgres Image

```sh
docker-compose -f .\docker-compose.db.yml up
```
Can be run with -d for detach console

## Log running detached named-docker-service

```sh
docker logs -f named-docker-service
```
detach console docker container example = api-transfer

## Build TransferApi Image

```sh
docker-compose -f .\docker-compose.api.yml build --no-cache
```

## Start TransferApi Image

```sh
docker-compose -f .\docker-compose.api.yml up
```

## Log running detached named-docker-service

```sh
docker logs -f named-docker-service
```
detach console docker container example = db-transfer

Alternatively, for the api, it can be started without building a image, 
for development purposes and practicality

## Start TransferApi With Spring

```sh
mvn spring-boot:run
```
In order for the application connection work properly with the database, application.properties, 
line 11, must be edited from:

```sh
spring.datasource.url=jdbc:postgresql://db:5432/transferdb
```
to

```sh
spring.datasource.url=jdbc:postgresql://0.0.0.0:5432/transferdb
```

## Documentation on Cli:
* [Documantation](https://documenter.getpostman.com/view/2634814/2s8ZDbVfQh)

## Author

👤 **jadergermano <jader.germano@icloud.com>**

* Twitter: [@jaderphilipe](https://twitter.com/jaderphilipe)
* Github: [@jader-germano](https://github.com/jader-germano)
* LinkedIn: [@jader-germano](https://linkedin.com/in/jader-germano)

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page]().

## Search your support

Give a ⭐️ if this project helped you!
