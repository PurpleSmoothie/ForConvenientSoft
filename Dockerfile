# 1. Базовый образ
FROM eclipse-temurin:17-jdk-jammy AS builder

# 2. Установка зависимостей для работы с Excel (если нужно)
RUN apt-get update && \
    apt-get install -y libreoffice-core && \
    rm -rf /var/lib/apt/lists/*

# 3. Рабочая директория
WORKDIR /app

# 4. Копируем только нужные файлы для сборки
COPY pom.xml .
COPY src ./src

# 5. Собираем проект (для Maven)
RUN ./mvnw clean package -DskipTests

# 6. Финальный образ
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# 7. Копируем только JAR из стадии builder
COPY --from=builder /app/target/testTask-*.jar app.jar

# 8. Создаем папку для входных файлов
RUN mkdir -p /app/input_files

# 9. Порт приложения
EXPOSE 8080

# 10. Точка входа с параметрами JVM
ENTRYPOINT ["java", "-jar", "app.jar"]