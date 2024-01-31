# spending_tracker
System to track my expenses built in Java

Project for accounting expenditures by categories, in project expenditure can be grouped by category, by category and by month, to display all categories and there is a separate search by id and deletion.

PostgreSQL database on Heroku is connected.

The project is written using SpringBoot with Maven support, Swagger connected. The Unit tests are written. To view the swagger project you can go to the link: https://spendingtracker.herokuapp.com/swagger-ui/index.html#/

A bot telegram is attached to the project.

github: https://github.com/pochkas/spending_tracker_bot

The telegram bot: https://t.me/My_spend_bot

To fill in the database, each user generates its UUID at startup, then the user enters the data through the bot, the date of expenditure is generated automatically.
