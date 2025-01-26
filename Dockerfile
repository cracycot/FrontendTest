# ---- STAGE 1: BUILD ----
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Скопировать pom.xml и скачать зависимости для оффлайн-режима
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Скопировать исходный код
COPY src ./src

# Собрать проект и пропустить тесты
RUN mvn clean package -DskipTests

# Проверка содержимого директории target для отладки
RUN ls -l /app/target

FROM maven:3.9.2-openjdk-17 AS build

WORKDIR /app

# Копировать скомпилированный JAR-файл из стадии сборки
COPY --from=build /app/target/demo-historical-psql-app-1.0.0.jar app.jar

EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]