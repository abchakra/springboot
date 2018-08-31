https://medium.com/@itseranga/kafka-and-zookeeper-with-docker-65cff2c2c34f

## Docker commands
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

## Important
modify the KAFKA_ADVERTISED_HOST_NAME and ZOOKEEPER_IP to match your docker host IP (Note: Do not use localhost or 127.0.0.1 as the host ip if you want to run multiple brokers.)


# Run Kafka and Zookeeper with docker

## 1. Run zookeeper
docker run -d \
--name zookeeper \
-p 2181:2181 \
jplock/zookeeper

## 2. Run kafka
docker run -d \
--name kafka \
-p 7203:7203 \
-p 9092:9092 \
-e KAFKA_ADVERTISED_HOST_NAME=10.4.1.29 \
-e ZOOKEEPER_IP=10.4.1.29 \
ches/kafka

## 3. Create topic
docker run \
--rm ches/kafka kafka-topics.sh \
--create \
--topic senz \
--replication-factor 1 \
--partitions 1 \
--zookeeper 10.4.1.29:2181

## 4. List topics
docker run \
--rm ches/kafka kafka-topics.sh \
--list \
--zookeeper 10.4.1.29:2181

## 5. Create publisher
docker run --rm --interactive \
ches/kafka kafka-console-producer.sh \
--topic senz \
--broker-list 10.4.1.29:9092

## 6. Create consumer
docker run --rm \
ches/kafka kafka-console-consumer.sh \
--topic senz \
--from-beginning \
--zookeeper 10.4.1.29:2181


# Kafka related data in Zookeeper
## 1. Go inside Zookeeper container
docker exec -it zookeeper bash
## 2. Connect to Zookeeper server
bin/zkCli.sh -server 127.0.0.1:2181

## 3. List root
ls /

## 4. List brokers
ls /brokers

## 5.List topics
ls /brokers/topics

## 6.List consumers
ls /consumers

## 7. List consumer owner
ls /consumers/console-consumer-1532/owners

