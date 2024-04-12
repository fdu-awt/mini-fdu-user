-- prepare mysql
-- create database
DROP DATABASE IF EXISTS mini_fdu;
CREATE DATABASE IF NOT EXISTS mini_fdu;
-- create user and grant privileges
CREATE USER IF NOT EXISTS 'mini_fdu_admin'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON mini_fdu_admin.* TO 'mini_fdu_admin'@'%';
FLUSH PRIVILEGES;