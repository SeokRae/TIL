---
description: 람다보다는 메서드 참조를 사용하라
---

# Item 43

## Contents

- 책에서 제공하는 정보

	- **람다**를 **메서드 레퍼런스**라는 방식으로 코드를 리펙토링 할 수 있다.
	- **메서드 레퍼런스**의 **유형**은 5가지
		- **정적 메서드**를 가리키는 메서드 레퍼런스
		- 수신 객체를 특정하는 **한정적** 인스턴스 메서드 레퍼런스
		- 수신 객체를 특정하지 않는 **비한정적** 인스턴스 메서드 레퍼런스
		- **클래스 생성자**를 가리키는 메서드 레퍼런스
		- **배열 생성자**를 가리키는 메서드 레퍼런스

- **람다의 장점**
	- 매개변수의 이름이 의미가 있는 경우, 유지보수에 좋다.

- **메서드 레퍼런스의 장점**
	- 매개변수의 의미가 중요하지 않은 경우, 간결한 코드 작성 가능
	- 메서드 명이 기능에 대한 유익한 의미를 갖도록 작성 가능

- 람다가 메서드 레퍼런스보다 **간결한 코드**가 되는 경우
	- 메서드와 람다가 같은 클래스에 있는 경우 더 간결하게 작성할 수 있다.

- **람다**로 할 수 없는 일이라면 **메서드 참조**로도 할 수 없다.
	- 기능적으로 동일
	- 예외) 제네릭 함수 타입(generic function type)

- **제네릭 함수 타입(generic function type)**
	- 함수형 인터페이스의 추상 메서드가 제네릭일 수 있듯이 함수 타입도 제네릭 일 수 있다.
	- 제네릭 함수 타입은 메서드 레퍼런스 표현식으로는 구현할 수 있지만, 람다식으로는 불가능하다.
	- 제네릭 람다식이라는 문접이 존재하지 않기 때문이다.

## Intro

![Intro](/java/effective/item43/images/item43.001.jpeg)

![Reference](/java/effective/item43/images/item43.002.jpeg)

## Simple

![Simple](/java/effective/item43/images/item43.003.jpeg)

## Method Reference Type

- **메서드 레퍼런스**의 **유형**은 5가지
	- **정적 메서드**를 가리키는 메서드 레퍼런스
	- 수신 객체를 특정하는 **한정적** 인스턴스 메서드 레퍼런스
	- 수신 객체를 특정하지 않는 **비한정적** 인스턴스 메서드 레퍼런스
	- **클래스 생성자**를 가리키는 메서드 레퍼런스
	- **배열 생성자**를 가리키는 메서드 레퍼런스

![Method Reference Type](/java/effective/item43/images/item43.004.jpeg)

## Reference to a static method

![Static Method Reference 1](/java/effective/item43/images/item43.005.jpeg)

![Static Method Reference 2](/java/effective/item43/images/item43.006.jpeg)

## Reference to an instance method of a particular object

![Reference to an instance method of a particular object](/java/effective/item43/images/item43.007.jpeg)

## Reference to an instance method of an arbitrary object of a particular type

![Reference to an instance method of an arbitrary object of a particular type](/java/effective/item43/images/item43.008.jpeg)

## Reference to a constructor

![Reference to a class constructor](/java/effective/item43/images/item43.009.jpeg)

![Reference to a array constructor](/java/effective/item43/images/item43.010.jpeg)

## 정리

![Reference to a array constructor](/java/effective/item43/images/item43.011.jpeg)
