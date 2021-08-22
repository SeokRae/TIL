# MySQL in Docker

## Intro

## Docker기반의 MySQL 서버 구축하기

```shell
seok@SRMac ~ % docker search mysql
```

### MySQL 이미지

```shell
docker pull mysql
```

### MySQL 컨테이너 생성 및 확인

```shell
docker run -d -p 8081:8080 -p 3307:3306 -e MYSQL_ROOT_PASSWORD=1234 --name sql_level_m mysql  
```

```shell
seok@SRMac ~ % docker ps -a
CONTAINER ID   IMAGE                        COMMAND                  CREATED         STATUS                      PORTS                                                                                             NAMES
2c77c4df5f2f   mysql                        "docker-entrypoint.s…"   6 seconds ago   Up 3 seconds                33060/tcp, 0.0.0.0:3307->3306/tcp, :::3307->3306/tcp, 0.0.0.0:8081->8080/tcp, :::8081->8080/tcp   sql_level_m
```

### MySQL Docker Container 접근

```shell
docker exec -i -t sql_level_m bash 
```

## MySQL 계정 설정

```shell
mysql> use mysql;

mysql> CREATE USER 'seok'@'%' IDENTIFIED BY '1234';

mysql> GRANT ALL PRIVILEGES ON *.* TO 'seok'@'%' WITH GRANT OPTION;

mysql> FLUSH PRIVILEGES;
```

### 데이터베이스 생성

```shell
mysql> create database sql_level default character set utf8 collate utf8_general_ci;
Query OK, 1 row affected, 2 warnings (0.01 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sql_level          |
| sys                |
+--------------------+
5 rows in set (0.00 sec)

mysql> use sql_level 
Database changed
```
