# OMDBAPI BE + client coding test

## Prerequisites

| Library / framework   | Minimum version |
|------------------|-----------------|
| JDK          | 17 (or 21)      |
| Maven        | 3.9             |
| PostgreSQL   | 14              |
| Node.js      | 18              |
| npm          | 10              |
| Angular CLI  | `npm i -g @angular/cli@19` |

---

## 1 — Backend setup

```bash
cd backend

# 1. Initialise database (single run)
psql -U postgres -c "CREATE DATABASE favmovies;"
psql -U postgres -c "CREATE USER movies_user WITH PASSWORD 'secret';"
psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE movies TO movies_user;"

# 2. Run the API
./mvnw spring-boot:run
```

Default connection settings (`movies/src/main/resources/application.properties`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/movies
spring.datasource.username=movies_user
spring.datasource.password=secret
omdb.api-key=omdb_apikey
```

Also do not forget to put your DB username and passord into flyway plugin configuration in pom.xml

The API is available at **`http://localhost:8080/api`**.

---

## 2 — Frontend setup

```bash
cd frontend
npm install
npm start             # opens http://localhost:4200
```

---

## License

MIT – feel free to use, modify and share. A star ⭐ would be highly appreciated!
