# Budgeting: API de controle de gastos com IA

API REST desenvolvida em **Java com Spring Boot** durante o curso da **DIO**. O projeto permite registrar e consultar gastos por categoria e usa inteligência artificial para entender mensagens em texto e em áudio.

## O que o projeto faz

- **Cadastra e lista transações** financeiras por categoria (ex.: lazer, alimentação).
- **Chat com IA:** envia um texto e recebe a resposta de um modelo da OpenAI.
- **Transcrição de áudio:** recebe um arquivo de áudio, transforma em texto e envia à IA, que interpreta o pedido (como registrar um gasto).
- **Texto para fala:** converte um texto em áudio MP3.
- **Persistência em banco de dados** MySQL rodando em container Docker.

## Tecnologias utilizadas

- Java e Spring Boot
- Spring Web (API REST)
- Spring AI (integração com a OpenAI: chat, transcrição e fala)
- Spring Data JPA e MySQL
- Docker e Docker Compose
- Gradle
- JUnit (testes de integração)
- Git e GitHub



## Como executar

1. Tenha o Java, o Docker e uma chave da API da OpenAI.
2. Defina a variável de ambiente `OPENAI_API_KEY` com a sua chave.
3. Execute a classe `BudgetingApplication`.
4. A API fica disponível em `http://localhost:8080`.
