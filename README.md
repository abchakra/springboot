# springboot



#Run MySql Docker container
docker run -p 6603:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=test" mysql

docker run --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=test" mysql

#Build docker file
docker build -f Dockerfile -t gradle-springboot-docker .

#Run springboot docker file
docker run -t --name gradle-springboot-docker --link docker-mysql:mysql -p 8080:8080 gradle-springboot-docker


# MongoDB with Docker
## RUN
docker run -d --name mongo -p 27017:27017 mongo
## USE
docker container exec -it mongo mongo
## Find Details eg IP
docker inspect mongo

