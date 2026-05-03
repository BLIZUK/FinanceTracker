# Быстрая настройка:

## Запуск в консоли:

```bush
cd C:\dev\projects\FinanceTracker

mvn compile

mvn exec:java -Dexec.mainClass="com.blizuk.financetracker.Main"
```

## Сбор таблиц для БД:

```postgresql
CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE transactions (
                              id SERIAL PRIMARY KEY,
                              amount DOUBLE PRECISION NOT NULL,
                              type VARCHAR(10) NOT NULL,
                              category_id INT REFERENCES categories(id),
                              description TEXT,
                              created_at TIMESTAMP
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL
);


INSERT INTO categories (name) VALUES ('Food');
INSERT INTO categories (name) VALUES ('Salary');

ALTER TABLE transactions
    ADD COLUMN user_id INT;

ALTER TABLE transactions
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES users(id);
```