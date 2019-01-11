http://localhost:8000/currency-exchange/from/EUR/to/INR
http://localhost:8100/currency-converter/from/EUR/to/INR/quantity/10000
http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000



java -jar -Dserver.port=8001 target/spring-boot-forex-service-0.0.1-SNAPSHOT.jar
mvn spring-boot:run -Drun.arguments="--server.port=8001"