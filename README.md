# Desafio Simples Dental - Cadastro de Profissionais

## 📑 Descrição

Projeto desenvolvido como desafio técnico do processo seletivo para Desenvolvedor Fullstack Pleno na [Simples Dental](simplesdental.com). Consiste em uma API que realiza o controle do cadastro de profissionais e contatos.

## 🔧 Tecnologias utilizadas

- Java 21
- Maven
- Spring Boot 3.2.3
- PostgreSQL
- Docker

## ⚙️ Execução em ambiente local

Para executar o projeto em ambiente local, é necessário substituir no arquivo `application.properties` os valores `${DB_URL}`, `${DB_USERNAME]` e `${DB_PASSWORD}` pelas respectivas informações do banco de dados PostgreSQL local.

Para iniciar a aplicação, devem ser executados os comandos:

```
mvn clean package
```

```
java -jar target/cadastro-profissionais.jar
```

Após a execução dos comandos, a API estará disponível em `http://localhost:8080`. A documentação Swagger se encontra no endpoint `/docs`.

## 🐋 Execução em ambiente Docker

Para facilitar a execução da aplicação, foram criados os arquivos `docker-compose.yaml` e `Dockerfile` que realizam o deploy tanto da API quanto do banco de dados, bastando que se execute o comando abaixo no diretório raiz do projeto para iniciá-lo:

```
docker-compose up -d
```

Após a execução do comando, a API estará disponível em `http://localhost:8080`. A documentação Swagger se encontra no endpoint `/docs`.

## 🏛️ Arquitetura

A estrutura "Package by Feature" foi escolhida para organizar os pacotes do projeto, visando proporcionar maior coesão e menor acoplamento em comparação com com a estrutura "Package by Layer". Considero que existem várias vantagens em utilizar a estrutura "Package by Feature", como a rapidez de encontrar classes relacionadas entre si e a facilidade na evolução do projeto, visto que os módulos possuem poucos pontos de interação.

<img src="https://miro.medium.com/v2/resize:fit:720/format:webp/1*1Gp2CkZKaj_myY9srSWaiQ.jpeg">

A aplicação foi dividida em 2 pacotes principais:

- core (pacote com classes compartilhadas entre os módulos)
- modules (pacote com os módulos da aplicação)

A programação genérica foi amplamente utilizada na aplicação, de modo que o pacote `core.base` armazena classes genéricas que abstraem lógicas comuns a todos os módulos. Tal ponto foi muito importante para evitar a repetição de código, de modo a aumentar a produtividade no desenvolvimento do projeto.

O padrão DTO foi utilizado nos endpoints de insert e update para realizar a separação entre as lógicas de validação e persistência.

A classe `GlobalExceptionHandler` foi criada para tratar globalmente as exceções lançadas pela aplicação, de modo a padronizar as respostas de erro.

Decidi implementar manualmente a lógica dos endpoints findAll, de modo a ter criado a classe `SqlBuilder`, a qual é responsável por encapsular a lógica de construção de queries SQL baseado nos parâmetros enviados e no tipo da classe requisitada.