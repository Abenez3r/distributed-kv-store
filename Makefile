build:
	mvn clean install

run:
	java -jar target/kvstore.jar

docker-build:
	docker build -t kvstore .

docker-run:
	docker-compose up
