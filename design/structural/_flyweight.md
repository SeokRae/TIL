---
description: Structural Pattern
---

# Flyweight Pattern

## Intro

* 효율성을 위해서 대량의 객체들간의 상태를 공유하는 것
* 특정 클래스의 인스턴스 한 개로 여러 개의 "가상 인스턴스를 제공하고 싶은 경우" 사용하는 패턴
* 즉, 인스턴스를 가능한 대로 공유시켜 쓸데 없이 new 연산자를 통한 메모리 낭비를 줄이는 패턴

### 상황

* 어플리케이션에 의해 생성되는 객체의 수가 많은 경우
* 생성된 객체가 오래도록 메모리에 상주하며, 사용하는 횟수가 많은 경우
* 객체의 특성을 내적 속성(Intrinsic Properties)과 외적 속성(Extrinsic Properties)으로 나눴을 때, 객체의 외적 특성이 클라이언트 프로그램으로부터 정의되어야 한다.

### 플라이웨이트 패턴 장점

* 공유 객체에 의해 메모리에 로드되는 객체의 개수를 줄일 수 있다.

### 플라이웨이트 패턴 단점

* 특정 인스턴스의 공유 컴포넌트를 다르게 행동하게 하는 것이 불가능하다.

## Java API

* String pool
	* 같은 문자열에 대해 다시 사용될 때 새로운 메모리를 할당하는 것이 아니라 pool 에서 존재하면 가져오고 없는 경우 새로 메모리 핧당하여 pool에 등록
* valueOf()

## code

* [Flyweight](https://github.com/SeokRae/java-in-action/tree/master/java-in-design/src/main/java/com/example/flyweight)
* [Flyweight vs Object Pool](https://stackoverflow.com/questions/30422525/what-are-the-differences-between-flyweight-and-object-pool-patterns)
