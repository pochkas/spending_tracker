# spending_tracker
System to track my expenses built in Java

Проект для учета трат по категориям, в проекте траты можно группировать по категориям, по категориям и месяцу, вывести все категории, отдельно есть поиск по id и удаление.

Подключена база данных PostgreSQL на Heroku.

Проект написан с помощью SpringBoot с поддержкой Maven, подключен Swagger. Написаны Unit-тесты. Для просмотра swagger проекта можно перейти по ссылке: https://spendingtracker.herokuapp.com/swagger-ui/index.html#/

К проекту привязан телеграм бот.

github: https://github.com/pochkas/spending_tracker_bot

сам бот: https://t.me/My_spend_bot

Для заполнения базы данных, у каждого пользователя генерируется свой UUID при старте, далее пользователь самостоятельно вносит данные через бота, дата внесения траты генерируется автоматически.