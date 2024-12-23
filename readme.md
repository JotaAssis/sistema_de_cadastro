# Sistema de Cadastro de Clientes

Este projeto foi desenvolvido como parte de um desafio proposto pela Segsat, com o objetivo de solucionar um problema específico. Ele consiste em uma aplicação web para cadastro de clientes, implementando operações CRUD (Create, Read, Update, Delete) e integrando a API do ViaCEP para automatizar a obtenção dos dados de endereço.

##  Tecnologias Utilizadas

Ferramentas e frameworks usadas na criação desse projeto

* [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Na versão 17
* [Spring Boot](http://www.dropwizard.io/1.0.2/docs/) - O framework web usado
* [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html) - Framework padrão MVC
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/index.html) - Persistência de dados
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [H2](https://www.h2database.com/html/main.html) - Banco de dados em memoria


###  Pré-requisitos

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Postman](https://www.postman.com/) ou outra ferramenta para executar as requisições HTTP da API
* [Maven](https://maven.apache.org/) - 4.0.0

###  Execução
*  Clone o repositório:

```
git clone https://github.com/JotaAssis/sistema_de_cadastro.git
```

* Abra o arquivo na sua IDE e execute, ou abra o prompt e acesse o diretório do projeto

```
cd sistema-de-cadastro
```
* Build:

```
mvnw clean package
```
* Executar a aplicação:

```
java -jar target/sistema-de-cadastro-0.0.1-SNAPSHOT.jar
```
A API poderá ser acessada em localhost:8080

## API Endpoints

Requisições HTTP no postman ou outro framework:

* Cadastrar cliente:

  Rota: localhost:8080/cliente/cadastrar

Body Json:
```
{
  "nome": "thiago",
  "email": "thiago@gmail.com",
  "telefone": "81988888888",
  "cep": "50860200"
}
```
***
* Listar todos os clientes

  Rota: localhost:8080/cliente/listar

Retorno:
```
[
  {
    "id": 1,
    "nome": "thiago",
    "email": "thiago@gmail.com",
    "telefone": "81988888888",
    "cep": "50860200",
    "endereco": "Rua Luiz Mendonça",
    "bairro": "Areias",
    "cidade": "Recife",
    "estado": "PE"
  }
]
```
***
* Atualizar dados do cliente

    Rota: localhost:8080/cliente/atualizar/1
    
    Body:
```
{
    "nome": "Thiago dos Santos",
    "email": "thiago@gmail.com",
    "telefone": "81988888888",
    "cep": "51210090"
}
```

* Remover cliente
  
  Rota: localhost:8080/cliente/deletar/1

  Retorno:
```
Cliente removido
```
***

* Buscar cliente
  
  Rota: localhost:8080/cliente/buscar/2

  Retorno:
```
{
  "id": 2,
  "nome": "joao",
  "email": "joao@gmail.com",
  "telefone": "81988888888",
  "cep": "50860200",
  "endereco": "Rua Luiz Mendonça",
  "bairro": "Areias",
  "cidade": "Recife",
  "estado": "PE"
}
```
***

## Versões

Neste projeto foi usado o [Git](https://git-scm.com/) para controle de versão.
