# 1. Базовый образ для сборки
FROM eclipse-temurin:21-jdk-jammy AS builder

# 2. Рабочая директория
WORKDIR /app

# 3. Копируем ВСЕ необходимые файлы для сборки
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# 4. Даем права на выполнение mvnw
RUN chmod +x mvnw

# 5. Собираем проект
RUN ./mvnw clean package -DskipTests

# 6. Финальный образ
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# 7. Копируем только JAR из стадии builder
COPY --from=builder /app/target/*.jar app.jar

# 8. Порт приложения
EXPOSE 8080

# 9. Точка входа
ENTRYPOINT ["java", "-jar", "app.jar"]