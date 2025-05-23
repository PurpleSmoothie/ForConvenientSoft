📝 Инструкция по запуску проекта
1. Локальный запуск (рекомендуется для разработки)
   Через IntelliJ IDEA:

Откройте проект в IDEA

Убедитесь, что используете JDK 17

Запустите DemoApplication (зелёная стрелка)

Swagger будет доступен: http://localhost:8080/swagger-ui.html

Через Maven:

bash
./mvnw spring-boot:run
2. Docker-запуск (для тестирования)
   bash
# Сборка и запуск
docker-compose up --build -d

# Проверка работы
curl -X POST "http://localhost:8080/api/path/min-element" \
-H "Content-Type: application/json" \
-d '{"path":"/app/input_files/sample.xlsx","n":2}'
3. Работа с файлами
   Для локального запуска:

Указывайте абсолютный путь к файлу:

json
{
"path": "C:/Users/user/Desktop/numbers.xlsx",
"n": 2
}
Для Docker:

Положите файл в папку input_files в проекте

Используйте путь:

json
{
"path": "/app/input_files/numbers.xlsx",
"n": 2
}
🔧 Альтернативные решения
Если не работает монтирование папок в Docker:
Скопируйте файл прямо в контейнер:

bash
docker cp numbers.xlsx test-app:/app/input_files/
Используйте в запросе:

json
{"path":"/app/input_files/numbers.xlsx","n":2}]

📌 Важные замечания
Формат файла - строго .xlsx (Excel 2007+)

Числа должны быть в первом столбце

Алгоритм не использует сортировку (как требуется в ТЗ)

Для Windows путей используйте / или экранируйте \