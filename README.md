# BCB-Big-Chat-Brasil-IRRAH
Para rodar usando o docker compose basta usar o comando abaixo no diretorio do docker-compose
```
docker compose up -d
```
Caso queira rodar sem o compose, criar os containers dos DBs dos services.
```
docker container run -d --net=bcb -p 5432:5432 -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=bcb -e POSTGRES_DB=bcb -v bcb_client_volume:/var/lib/postgresql/data --name bcb_client_db postgres:alpine3.20
```
```
docker container run -d --net=bcb -p 5433:5433 -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=bcb -e POSTGRES_DB=bcb -v bcb_sms_volume:/var/lib/postgresql/data --name bcb_sms_db postgres:alpine3.20
```

Para rodar o service do client
```
cd bcb-client-service
./mvnw package && java -jar target/bcb-0.0.1-SNAPSHOT.jar
```

Para rodar o service do sms
```
cd bcb-sms-service
./mvnw package && java -jar target/bcb-sms-service-0.0.1-SNAPSHOT.jar
```
