# docker redis cluster

## Intro

## Redis Cluster with Docker

```yml
version: '3'
services:

  redis-master:
    image: redis:5.0-alpine   	# image 옵션으로 redis공식이미지를 기반으로 생성합니다.
    build:
      context: ..             	# build file에 대한 context를 지정
      dockerfile: Dockerfile  	# dockerfile명을 지정합니다. (Default는 "Dockerfile" 파일명을 가진 파일을 실행합니다.)
    network_mode: "host"      	# Image가 Container가 되었을때 network모드를 host모드로 설정합니다 (docker0 인터페이스를 사용하지 않고 Host OS의 eth0 인터페이스 사용)
    environment: 				# Container 내부에서 사용 할 환경변수를 설정합니다.
      - REQUIREPASS=password1234
      - CLIENTPORT=6379
      - CLIENTHOST=127.0.0.1
      - MASTERHOST=
      - MASTERPORT=
    volumes: # Host OS의 디렉터리와 Container내의 디렉터리와 연동합니다.
      - "../redis-data/redis1:/data"
    restart: always

  redis-slave1:
    image: redis:5.0-alpine
    build:
      context: ..
      dockerfile: Dockerfile
    network_mode: "host"
    environment:
      - REQUIREPASS=password1234
      - CLIENTPORT=6380
      - MASTERPORT=6379
      - CLIENTHOST=127.0.0.1
      - MASTERHOST=127.0.0.1
    volumes:
      - "../redis-data/redis2:/data"
    restart: always

  redis-slave2:
    image: redis:5.0-alpine
    build:
      context: ..
      dockerfile: Dockerfile
    network_mode: "host"
    environment:
      - REQUIREPASS=password1234
      - CLIENTPORT=6381
      - MASTERPORT=6379
      - CLIENTHOST=127.0.0.1
      - MASTERHOST=127.0.0.1
    volumes:
      - "../redis-data/redis3:/data"
    restart: always
```


```dockerfile
# redis 이미지를 기반으로 빌드됨을 의미합니다.
FROM redis:5.0-alpine 
 
MAINTAINER SR (seokrae@gmail.com)
 
# Copy Redis File
# 복사/추가 하는파일의 Container내 경로는 항상 절대경로로 작성하여야 합니다. !!!
# 기존의 docker-entryporint.sh 파일을 삭제합니다.
RUN rm -rf /usr/local/bin/docker-entrypoint.sh  
# 공통적으로 적용할 redis.conf 파일을 복사합니다.
ADD redis.conf /usr/local/bin/redis.conf    
# Container가 생성, 시작하는 시점에 실행 시킬 docker-entrypoint.sh 파일을 복사합니다.
ADD docker-entrypoint.sh /usr/local/bin        
 
## change access authority
RUN chmod 755 /usr/local/bin/redis.conf
RUN chmod 755 /usr/local/bin/docker-entrypoint.sh
 
RUN chown redis:redis /usr/local/bin/redis.conf
RUN chown redis:redis /usr/local/bin/docker-entrypoint.sh

#Redis Container에 대한 Port를 지정합니다. (내부포트이며 외부노출은 안됨)
EXPOSE $CLIENTPORT #CLIENTPORT에 대한 값은 docker-compose.yml에 정의되어 있습니다.          
# Container가 생성, 시작하는 시점에 실행됩니다.
ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"] 
# Container 빌드가 완료되고 Redis Server를 실행시킵니다.
CMD [ "redis-server","/usr/local/bin/redis.conf" ]
```

## Redis Cluster 설정


```shell
#!/bin/sh
set -e
 
## from redis-5.0
# Redis서버에 접근가능한 Host를 설정합니다.
sed -i "s/bind 127.0.0.1/bind $CLIENTHOST 127.0.0.1/g" /usr/local/bin/redis.conf

### redis port inside redis.conf
#redis.conf 파일의 port 6379 문자열을 port $CLINETPORT로 변경합니다. 
#($CLIENTPORT는 docker-compose.yml파일의 Environment에서 지정하였습니다.)
sed -i "s/port 6379/port $CLIENTPORT/g" /usr/local/bin/redis.conf 

# requirepass foobared 문자열을 requirepass $REQUIREPASS로 변경하였습니다. 
#(Redis 접속 시 비밀번호를 설정하였습니다.)
sed -i "s/# requirepass foobared/requirepass $REQUIREPASS/g" /usr/local/bin/redis.conf          
sed -i "s/# masterauth <master-password>/masterauth $REQUIREPASS/g" /usr/local/bin/redis.conf   

### slaveof <masterip> <masterport> => slaveof $MASTERHOST $MASTERPORT
# $MASTERPORT가 공백이 아닌 Container에 대해 slaveof 설정을 통해 Master-Slave 설정을 합니다.
if [ "$MASTERPORT" != "" ];then
    sed -i "s/# slaveof <masterip> <masterport>/slaveof $MASTERHOST $MASTERPORT/g" /usr/local/bin/redis.conf  
fi
 
# first arg is `-f` or `--some-option`
# or first arg is `something.conf`
if [ "${1#-}" != "$1" ] || [ "${1%.conf}" != "$1" ]; then
    set -- redis-server "$@"
fi
 
# allow the container to be started with `--user`
if [ "$1" = 'redis-server' -a "$(id -u)" = '0' ]; then
    chown -R redis .
    exec su-exec redis "$@"
fi
 
exec "$@"
```


## Redis Cluster Tutorial

- [Docker기반 Redis 구축하기 - (6) Docker를 이용한 Redis Cluster 설치하기](https://jaehun2841.github.io/2018/12/01/2018-12-01-docker-6/#docker를-이용한-redis-cluster-설치)
- [Redis cluster tutorial](https://redis.io/topics/cluster-tutorial)
