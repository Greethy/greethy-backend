# shellcheck disable=SC2103
# shellcheck disable=SC2164
cd config-server;
./mvnw spring-boot:run;
cd ..;

cd services-discovery;
./mvnw spring-boot:run;
cd ..;

cd user-services;
./mvnw spring-boot:run;
cd ..;

cd api-gateway;
./mvnw spring-boot:run;
cd ..;
