Запуск 

* Сборка `mvn clean install -Dmaven.test.skip=true`
* Запуск `java -jar currency-0.0.1-SNAPSHOT.jar`

Docker

* Сборка docker-контейнера `docker-compose build`
* Запуск docker-контейнера (предварительно остановив контейнер) `docker-compose up`


* Просмотр запущенных контейнеров - `sudo docker ps`
* Остановить контейнер            - `sudo docker stop container-id`

HTTP endpoint 

http://localhost:8080/compare_cur?code=USD