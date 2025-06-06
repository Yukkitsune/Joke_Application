# Joke Application

## Описание
Joke Application — это Android-приложение для просмотра и сохранения шуток. Приложение загружает шутки с API, позволяет сохранять их в локальной базе данных и добавлять свои собственные.

Проект был реализован в рамках курса по Android-разработке от ТБанка.

## Функционал
- Загрузка случайных шуток с внешнего API
- Сохранение шуток в локальную базу данных
- Возможность добавлять свои шутки
- Просмотр списка сохранённых шуток
- Удаление шуток из базы

## Технологии
- **Язык**: Kotlin
- **Архитектура**: MVVM (Model-View-ViewModel)
- **UI**: Jetpack Compose
- **Работа с сетью**: Retrofit
- **Локальное хранилище**: Room (SQLite)
- **Асинхронность**: Kotlin Coroutines

## Установка и запуск
1. Клонировать репозиторий:
   ```sh
   git clone https://github.com/Yukkitsune/Joke_Application.git
   ```
2. Открыть проект в Android Studio
3. Собрать и запустить приложение на эмуляторе или реальном устройстве
