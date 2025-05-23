# Запуск проекта

## Клонируем:
```sh
git clone https://github.com/Dewreeh/TicketsService
```

## Далее:
```sh
docker compose build
docker compose up
```
docker-compose запустит 2 микросервиса, kafka, redis и развернёт БД с тестовыми данными.
Микросервисы на 8080 и 8081 портах

Также собрал небольшую postman коллекцию для тестирования API: [https://drive.google.com/drive/folders/1S7yHqlHaajIUskcyb43_rnn5EJno981e?hl=ru](https://drive.google.com/drive/folders/1S7yHqlHaajIUskcyb43_rnn5EJno981e?hl=ru&q=sharedwith:public%20parent:1S7yHqlHaajIUskcyb43_rnn5EJno981e)
Можно импортировать к себе и отправлять подготовленные запросы

В БД добавил 3 тестовых пользователей:
1) {
  "username": "ADMIN",
  "password": "12345"
}
2) {
  "username": "USER1",
  "password": "12345"
}
3) {
  "username": "USER2",
  "password": "12345"
}

При запросе на http://localhost:8080/login выдаётся access_token и refresh_token, access_token надо прикладывать в Headers к защищённым эндпоинтам

Незащищенные эндпоинты:

http://localhost:8080/tickets/get (именно для получения всех билетов, а не купленных билетов у конкретного пользователя как в /tickets/get_my?userId=...)

http://localhost:8080/login

http://localhost:8080/user/register

Также при покупке и получении купленных билетов проходит проверка, что токен принадлежит тому же пользователю, для которого происходит попытка купить билет/получить список купленных билетов, поэтому для успешной операции следует указывать именно тот id пользователя, под которым прошла авторизация (иначе прилетает 403 и сообщение  "message": "Доступ запрещён (ошибка авторизации): Попытка работы с данными другого пользователя")
  
