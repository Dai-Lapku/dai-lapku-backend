## Quick start
1. Clone this project
2. Run PostgreSQL process or use docker command: `docker run --name postgres-latest -v /opt/db/postgres-latest:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root -e POSTGRES_DB=postgres -p 5432:5432 -d postgres:9.4`.
3. Run in console `gradle jar`, `java -jar build/libs/spring-boot-postgresql-gradle.jar` or just `gradle bootRun`
4. Point your browser to [http://localhost:8080/](http://localhost:8080/) to use api.
5. API docs are available at [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)
