/* データベース自体を削除 */
DROP DATABASE IF EXISTS awsdemo;
/* ユーザーを削除 */
DROP USER IF EXISTS 'carin'@'%';
/* 権限をリフレッシュ */
FLUSH PRIVILEGES;

create database awsdemo;
/* ユーザー */
create user 'carin'@'%' identified by 'startover';
grant select, update, insert, create, delete, drop on quizoo.* to 'carin'@'%';
use awsdemo;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

INSERT INTO users (username, password, email) VALUES ('crage', '4646', 'crage4646.com');
