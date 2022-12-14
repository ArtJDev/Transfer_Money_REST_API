# Приложение «Сервис перевода денег»
Стек: Spring Boot, H2 database, JUnit, Mockito, Testcontainers, Maven, Docker.
## Описание
Проект предоставляет сервис для перевода денег с одной карты на другую. Веб приложение (FRONT) подключается к сервису и использует его функционал для перевода денег. Описание веб приложения (FRONT) доступно по адресу https://github.com/serp-ya/card-transfer. Запуск веб приложения осуществляется по ссылке https://serp-ya.github.io/card-transfer/
## Запуск приложения
Для запуска приложения используются команды Docker
1. Скачать или склонировать репозиторий приложения в программу IntelliJ IDEA
2. Упаковать проект в Maven
3. В терминале приложения командой `docker build -t restapp .` создать образ приложения
4. Упаковать образ в контейнер командой `docker-compose build`
5. Запустить контейнер командой `docker-compose up`

Для остановки приложения нажать Ctrl+C
## Пример использования
В базе данных приложения создаются две банковские карты
| Номер карты | Дата действия | Код CVV | Баланс карты |Валюта |
|---------------------|-------|-----|-------|-----|
| 1111 1111 1111 1111 | 11/22 | 111 | 10000 | RUB |
| 2222 2222 2222 2222 |	12/22 |	222 | 10000 | RUB |

В веб приложении, запускаемом по ссылке выше, на полях заполнить соответствующие данные карт и сумму перевода (учтите, что FRONT автоматически добавляет к вашей сумме два ноля). Если все верно заполнено, вы увидите сообщение об успешной переводе, если нет, то сообщение об ошибке перевода. В консоле разработчика браузера можно посмотреть данные запроса и ответа. Лог приложения сохраняется в папке Logs, файл logfile.log
## Схема приложения
![Схема приложения](https://user-images.githubusercontent.com/98458226/200128423-df2f3fbb-f6ba-4b58-a517-bbc19bd17c86.png)
## Описание архитектуры приложения
Настройки приложения хранятся в файлах папки resources, в которых прописано: порт, на котором стартует приложение, папка и имя файла с логами программы, схема таблицы, создаваемой в базе данных и данные создаваемые в этой таблице.

Слой контроллера принимает запросы клиента, передает данные слою бизнес-логики, принимает данные из слоя бизнес-логики и отправляет ответ клиенту.

Слой бизнес-логики принимает данные из котроллера, обрабатывает их, взаимодействует со слоем репозитория, получая и отправляя ему нужные данные, и отправляет новые данные контроллеру.

Слой репозитория взаимодействует со слоем бизнес-логики, получает, сохраняет и обновляет данные из базы данных.

Данные о картах хранятся в SQL базе данных H2, которая создается в оперативной памяти во время запуска приложения и хранится в ней пока приложение запущено.
