# Projeto Spring Data

![Java](https://img.shields.io/badge/Java-11-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-green)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-2.5.4-yellow)
![MySQL](https://img.shields.io/badge/MySQL-8.0.26-blue)
![Flyway](https://img.shields.io/badge/Flyway-7.0.0-orange)
![HATEOAS](https://img.shields.io/badge/HATEOAS-1.3.1-purple)

Este projeto demonstra o uso do Spring Data para simplificar o acesso a dados em aplicativos Spring Boot. Ele inclui exemplos de uso do Spring Data JPA para bancos de dados relacionais MySQL, juntamente com o Flyway para migrações de banco de dados e o HATEOAS para suporte a hipermídia em APIs RESTful.

## Funcionalidades

- Operações CRUD com MySQL usando o Spring Data JPA
- Interfaces de repositório e métodos de consulta
- Migrações de banco de dados com Flyway
- Suporte HATEOAS para APIs RESTful

## Tecnologias Utilizadas

- Java 11
- Spring Boot 2.5.4
- Spring Data JPA 2.5.4
- MySQL 8.0.26
- Flyway 7.0.0
- HATEOAS 1.3.1

## Começando

Para executar este projeto localmente, você precisará do Java 11, Maven e MySQL instalados. Clone o repositório e configure o arquivo `application.properties` com as configurações do seu banco de dados MySQL. Em seguida, use o Maven para compilar e executar o projeto:

```shell
git clone https://github.com/acbueno/spring-data.git
cd spring-data
mvn spring-boot:run
