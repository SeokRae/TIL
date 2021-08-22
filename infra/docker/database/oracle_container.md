# Oracle In Docker

## Intro

## Docker 로 Oracle 서버 구축하기

```shell
seok@SRMac ~ % docker search oracle-xe
```

### Oracel 이미지

```shell
seok@SRMac ~ % docker pull jaspeen/oracle-xe-11g
```

```shell
# xe 버전
seok@SRMac ~ % docker run --name sql_level -d -p 8080:8080 -p 1523:1521 jaspeen/oracle-xe-11g
```

### Oracle 컨테이너 생성

```shell
seok@SRMac ~ % docker ps -a
CONTAINER ID   IMAGE                        COMMAND                  CREATED         STATUS                      PORTS                                            NAMES
193a2843689c   jaspeen/oracle-xe-11g        "/entrypoint.sh "        5 minutes ago   Up 5 minutes                0.0.0.0:8080->8080/tcp, 0.0.0.0:1523->1521/tcp   sql_level
```

### Oracle Docker Container 접근

```shell
docker exec -it sql_level sqlplus
```

## Oracle 계정 설정

> Oracle Client 접근

```shell
seok@SRMac ~ % docker exec -it sql_level sqlplus

SQL*Plus: Release 11.2.0.2.0 Production on Wed Jul 7 09:56:13 2021

Copyright (c) 1982, 2011, Oracle.  All rights reserved.

Enter user-name: system  
Enter password: 

Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

SQL>    
SQL> 
SQL> create user seok identified by 1234;

User created.

SQL> grant connect, resource, dba to seok;

Grant succeeded.

SQL> conn seok/1234
Connected.

```
