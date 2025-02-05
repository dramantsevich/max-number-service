# Max Number Service

## Описание проекта

Max Number Service — это RESTful API на базе Spring Boot, который позволяет находить N-е максимальное число из локального файла .xlsx. Сервис поддерживает следующие функции:

- Принимает файл .xlsx, содержащий целые числа в первом столбце.
- Находит N-е максимальное число без использования библиотечных функций сортировки.
- Предоставляет документацию API через Swagger UI.

### Технологии
- Java 11
- Spring Boot
- Apache POI (для чтения .xlsx файлов)
- OpenAPI 3 (документация API через Swagger UI)
- Maven (управление зависимостями)

### Установка

1. **Клонируйте репозиторий**

   ```sh
   git clone https://github.com/your-username/max-number-service.git

2. **Соберите проект с помощью Maven**
   
   ```sh
   mvn clean install

3. **Запустите приложение**
   ```sh
   mvn spring-boot:run

Приложение будет доступно по адресу: http://localhost:8080 .

### Swagger

Откройте следующий URL в браузере:
http://localhost:8080/swagger-ui.html

**Описание ендпоинта**
Ендпоинт: /api/max-number
Метод: POST
Параметры:
filePath: Полный путь к файлу .xlsx (например, C:\Users\path\numbers.xlsx).
n: Номер максимального значения, которое нужно найти (целое положительное число).

**Пример запроса через Swagger**

filePath: C:\Users\path\to\numbers.xlsx
n: 3
