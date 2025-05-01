# Используем LTS-версию Java 17
FROM eclipse-temurin:17-jdk-jammy AS builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы для сборки
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Даем права на выполнение mvnw
RUN chmod +x mvnw

# Скачиваем зависимости (кешируем этот слой)
RUN ./mvnw dependency:go-offline

# Собираем проект
RUN ./mvnw clean package -DskipTests

# Финальный образ
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Копируем собранный JAR
COPY --from=builder /app/target/*.jar app.jar

# Открываем порт
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]