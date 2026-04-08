# 🚀 README - Projeto KMP + Ktor (API com PostgreSQL)

Este projeto é uma API construída com **Kotlin Multiplatform (KMP)** utilizando **Ktor** no backend, com banco de dados **PostgreSQL** rodando via Docker.

---

## 📦 Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

* Docker
* Docker Compose
* JDK 17+
* IntelliJ IDEA (recomendado)

---

## 🐘 Subindo o banco de dados (PostgreSQL)

O projeto já possui um `docker-compose.yml` configurado.

### 🔧 Configuração

```yaml
services:
  db:
    image: postgres:15-alpine
    container_name: mergeskills-db
    restart: unless-stopped
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: devuser
      POSTGRES_PASSWORD: devpassword
      POSTGRES_DB: mergeskills
    volumes:
      - pgdata:/var/lib/postgresql/data

volumes:
  pgdata:
```

### ▶️ Rodar o container

Execute na raiz do projeto:

```bash
docker-compose up -d
```

Isso irá:

* Criar o container `mergeskills-db`
* Subir o PostgreSQL na porta `5432`
* Criar o banco `mergeskills`

---

## ⚙️ Configuração da aplicação

Verifique se sua aplicação está apontando para o banco corretamente:

```
Host: localhost
Port: 5432
Database: mergeskills
User: devuser
Password: devpassword
```


## ▶️ Rodando a aplicação

Você pode rodar o backend de duas formas:

### 💻 Via IntelliJ

1. Abra o projeto
2. Localize a classe `ApplicationKt`
3. Clique em **Run**

---


---

## 🌐 Acessando a API

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 📚 Swagger (Documentação da API)

A documentação interativa estará disponível em:

```
http://localhost:8080/swagger
```

*(ou `/docs`, dependendo da configuração do projeto)*

---

## 🔁 Rotas principais

Exemplo de rota existente:

```
GET /agents
```

Essa rota retorna os agentes cadastrados no banco.

---

## 🛑 Parando o ambiente

Para parar o banco:

```bash
docker-compose down
```

Se quiser remover os dados também:

```bash
docker-compose down -v
```

---

## 🧠 Observações

* O banco persiste os dados no volume `pgdata`
* Caso altere credenciais, lembre de atualizar no código também
* Logs da aplicação aparecem no console (Ktor + Exposed)

---

## ✅ Pronto!

Agora é só subir o banco, rodar o projeto e acessar:

👉 http://localhost:8080

---
