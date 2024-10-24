# Projeto: Desenvolvimento e Deploy de Sistema Escolar

Este projeto tem como objetivo desenvolver uma aplicação web para gerenciamento de alunos, turmas e funcionários em uma instituição de ensino. A aplicação permitirá o cadastro, consulta, atualização e remoção dessas entidades, além de garantir a segurança dos dados através de autenticação.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **PostgreSQL** (ou outro banco de dados relacional)
- **Docker** (para containerização)
- **Swagger/OpenAPI** (para documentação da API)

## Estrutura do Banco de Dados

- **Aluno**
  - id: PK, auto increment
  - nome: Obrigatório, mínimo 3 caracteres
  - email: Obrigatório, padrão de email, único
  - idade: Apenas valores positivos
  - nota Primeiro Módulo: Nota máxima 10.0
  - nota Segundo Módulo: Nota máxima 10.0
  - média: Média das duas notas anteriores
  
- **Turma**
  - id: PK, auto increment
  - nome: Obrigatório, mínimo 3 caracteres
  - instrutor: Obrigatório
  - alunos: FK (entidade Aluno)

- **Funcionário**
  - id: PK, auto increment
  - nome: Obrigatório, mínimo 3 caracteres
  - email: Obrigatório, padrão de email, único
  - senha: Obrigatório, mínimo 6 caracteres
  - cargo: Obrigatório
 
 ## Funcionalidades

- **CRUD para Alunos, Funcionários e Turmas**:

  
   - **Alunos**  
     - `GET /alunos` - Listar todos os alunos
     - `GET /alunos/{id}` - Obter aluno por ID
     - `POST /alunos` - Cadastrar novo aluno (autenticação necessária)
     - `PUT /alunos/{id}` - Atualizar aluno (autenticação necessária)
     - `DELETE /alunos/{id}` - Deletar aluno (autenticação necessária)

   - **Funcionarios**
     - `GET /funcionarios` - Listar todos os funcionários
     - `GET /funcionarios/{id}` - Obter funcionário por ID
     - `POST /funcionarios` - Cadastrar novo funcionário (sem autenticação)
     - `POST /funcionarios/login` - Realizar login e gerar token JWT
     - `PUT /funcionarios/{id}` - Atualizar funcionário (autenticação necessária)
     - `DELETE /funcionarios/{id}` - Deletar funcionário (autenticação necessária)

   - **Turmas**
     - `GET /turmas` - Listar todas as turmas
     - `GET /turmas/{id}` - Obter turma por ID
     - `POST /turmas` - Cadastrar nova turma (autenticação necessária)
     - `PUT /turmas/{id}` - Atualizar turma (autenticação necessária)
     - `DELETE /turmas/{id}` - Deletar turma (autenticação necessária)

## Regras de Negócio
1. **Cadastro de Alunos e Funcionários:**
   - Não é permitido cadastrar dois funcionários ou alunos com o mesmo e-mail. A aplicação retornará um erro neste caso.
   
2. **Segurança e Autenticação:**
   - Toda a aplicação é protegida por uma camada de segurança que requer autenticação via token JWT (JSON Web Token) com validade de 3 horas.
   - As únicas operações permitidas sem autenticação são o **Cadastro de Funcionário** e o **Login**.
   - Todas as outras operações requerem um cabeçalho de autenticação com o token JWT válido.

3. **Alocação de Alunos nas Turmas:**
   - Cada aluno pode estar matriculado em apenas uma turma.

- **Documentação:**
   - Todas as outras operações requerem um cabeçalho de autenticação com o token JWT válido.


## Configurações de Segurança

A aplicação utiliza **JWT (JSON Web Tokens)** para autenticação. Para realizar operações que necessitam de autenticação, você precisa:

1. Fazer o login via `POST /funcionarios/login` para gerar um token JWT.
2. Enviar o token gerado no cabeçalho das requisições como `Authorization: Bearer {token}`.

## Configuração do Projeto

### Requisitos

- **Java 17**
- **Maven**
- **PostgreSQL** (ou outro banco de dados relacional)
- **Docker** (para containerização)
- **Render** (para deploy da aplicação)

### Configuração do Banco de Dados

No arquivo `application.properties`, as seguintes configurações devem ser feitas para se conectar ao banco de dados:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Configuração do Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Formatação de datas
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=Brazil/East
api.security.token.secret=${JWT_SECRET:my-secret-key}

## Docker

# Use a imagem do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR para o contêiner
COPY target/seu_projeto.jar app.jar

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]


## Estrutura do Projeto

```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── seu_pacote
│   │           ├── controller
│   │           ├── model
│   │           ├── repository
│   │           ├── security
│   │           ├── service
│   │           └── dto
│   └── resources
│       ├── application.properties
│       └── ...
└── test
