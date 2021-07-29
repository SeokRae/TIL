# RabbitMQ

## Intro

## Settings

```shell
seok@SRMac ~ % docker pull rabbitmq:3-management
3-management: Pulling from library/rabbitmq
a31c7b29f4ad: Already exists 
0ea5da5fa011: Already exists 
9e67bc98a8bb: Pull complete 
267962a3eb06: Pull complete 
f3e19ca3b08c: Pull complete 
9dca6229c31c: Pull complete 
59e0907eef05: Pull complete 
e8b437bf7e57: Pull complete 
3bef15ebabc1: Pull complete 
Digest: sha256:ed083d74af12070524f0c26afca5110a5af6957a4a85bc01aa22f5a9f92beffa
Status: Downloaded newer image for rabbitmq:3-management
docker.io/library/rabbitmq:3-management
```

```shell
seok@SRMac ~ % docker run -d  -p 5672:5672 -p 15672:15672 --name rabbitmq-service rabbitmq:3-management
```

## RabbitMQ
