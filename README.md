# BCB-Big-Chat-Brasil-IRRAH
Projeto para testar e aprender arquitetura micro services 
As requests podem ser testadas pelo swagger-ui.

Para rodar usando o docker compose basta usar o comando abaixo no diretorio onde est;a o arquivo compose.yml
```
docker compose up -d
```
Caso queira rodar sem o compose, rodar na ordem abaixo:

(Caso não queira pular os teste remova o "-DskipTests", os testes de integração estão utilizando TestContainers então se não estiver com docker instalado e rodando eles vão quebrar).

Criar os containers dos DBs dos services. 
```
docker container run -d --net=bcb -p 5432:5432 -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=bcb -e POSTGRES_DB=bcb -v bcb_client_volume:/var/lib/postgresql/data --name bcb_client_db postgres:alpine3.20
```
```
docker container run -d --net=bcb -p 5433:5433 -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=bcb -e POSTGRES_DB=bcb -v bcb_sms_volume:/var/lib/postgresql/data --name bcb_sms_db postgres:alpine3.20
```
Para rodar o naming-server Port 8761
```
cd bcb-naming-server
mvn clean package -DskipTests && java -jar target/bcb-naming-server-0.0.1-SNAPSHOT.jar
```

Para rodar a api-gateway Port 8765
```
cd bcb-api-gateway
mvn clean package -DskipTests && java -jar target/bcb-api-gateway-0.0.1-SNAPSHOT.jar
```

Para rodar o service do client Port 8080
```
cd bcb-client-service
mvn clean package -DskipTests && java -jar target/bcb-0.0.1-SNAPSHOT.jar
```

Para rodar o service do sms Port 8100
```
cd bcb-sms-service
mvn clean package -DskipTests && java -jar target/bcb-sms-service-0.0.1-SNAPSHOT.jar
```
