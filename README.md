# Lab 03. Мультистейджинг, сборка и запуск
**Simple Java Spring "Hello world" application**
*Использовали чужой проект [https://github.com/OldAl67/Samples.Sample1](https://github.com/OldAl67/Samples.Sample1) .

1. Клонируем репозиторий:
```git clone https://github.com/OldAl67/Samples.Sample1 ./app_src```
2. Используем ```docker compose up``` чтоб собрать и запустить.
3. Используем [http://localhost:**8081**/say/hello](http://localhost:8081/say/hello) чтобы проверить ответ java приложения.

## Dockerfile

>Берем образ c java и maven
```
FROM maven:3.8.3-openjdk-17 AS build
```

>Объявляем аргумент сборки и присваиваем дефолтное значение
```
ARG SRC="./app_src"
```
>Создаем переменную окружения и присваиваем значение аргумента 
```
ENV APP_SRC=${SRC}
```
>Задаем рабочую директорию для сборки
```
WORKDIR /tmp/sample1
```

>Копируем из заданной папки файл конфиг. maven-проекта
```
COPY ${APP_SRC}/pom.xml ./pom.xml
```
>Также копируем папку с исходным кодом java приложения
```
COPY ${APP_SRC}/src ./src   
```

>Собираем .jar  файл с помощью maven и чистим старые артефакты
```
RUN mvn clean package
```

>Берем легковесный образ, содержащий только JRE(java ran env) и 
приложение со всеми зависимостями, которых достаточно для запуска 
```
FROM alpine/java:17-jre 
```

>Создаем нового пользователя и группу для запуска java приложения
```
RUN addgroup -g 2025 servicegroup && \
    adduser -u 2025 -G servicegroup -s /bin/sh -D serviceuser
```

>Устанавливаем его текущим пользователем, от лица которого выполняются команды
```
USER serviceuser
```

>Устанавливаем директорию, куда поместим легкое веб-приложение /opt/sample1/ 
```
WORKDIR /opt/sample1
```

>Копируем сборку .jar файла из собранного образа в итоговый образ(второй) в workdir для контейнера запуска приложения 
```
COPY --from=build "/tmp/sample1/target/sample1-1.0-SNAPSHOT.jar" .
```
>Приложение использует порт 8080
```
EXPOSE 8080
```

>Запускаем .jar файл в java
```
ENTRYPOINT ["java", "-jar", "sample1-1.0-SNAPSHOT.jar"]
```
