# Mini FDU User

2024年春季学期《高级Web技术》课程项目实践

## 技术栈

- Java 17
- Spring Boot 3
- MySQL 8.0
- 数据库操作：Spring Data Jpa
- 日志：Slf4j

# 开发指南

配置好数据库，用idea打开本项目进行开发即可。

## 数据库配置
1. 修改配置文件[application.properties](./src/main/resources/application.properties)，启用dev配置

```properties
spring.profiles.active=dev
```

2. MySQL初始化：

- 方式一：运行 `src/main/resources/init.sql` 文件
- 方式二：手动运行下面的 SQL 语句

```shell
mysql> DROP DATABASE IF EXISTS mini_fdu;
mysql> CREATE DATABASE mini_fdu;
mysql> CREATE USER 'mini_fdu_admin'@'%' IDENTIFIED BY 'password123';
mysql> GRANT ALL ON mini_fdu.* TO 'mini_fdu_admin'@'%';
mysql> FLUSH PRIVILEGES;
mysql> quit
```