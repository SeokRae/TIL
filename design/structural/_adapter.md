---
description: Structural Pattern
---

# Adapter Pattern

## Intro

* 한 클래스의 인터페이스를 클라이언트가 예상하는 다른 클래스로 변환하여 호환되지 않는 클래스와 함께 작동하도록 하는 것

## 적용 방법

* Object Adapter
	* 자바의 합성\(Composite\)를 통한 방법
* Class Adapter
	* 자바의 상속\(Inheritance\)를 통한 방법
	* Class Adapter는 Java 에서 지원되지 않는 다중 상속을 통해 작동한다.
	* 아이디어는 Adapter가 Adapter 클래스뿐만 아니라 클라이언트에서 사용중인 인터페이스를 모두 확장한다는 것이다.
	* 적용 방법은 구성 대신 상속을 통해 작동한다.

![Adapter Pattern](../../.gitbook/assets/coffee_adapter.png)

## Java API

* Enumeration 클래스의 hasMoreElements 와 nextElement는 추후에 Collections가 반영될 때 Iterator 요소를 제거할 수 있는 인터페이스가 도입되었다.
* 레거시 코드를 지원하기 위해 두 인터페이스간에 변환할 Adapter 클래스를 만들수 있으며 Enumeration 은 읽기 전용이므로 항목 제거가 요청될 때 예외를 던질 수 있다.
* java.io.InputStreamReader, java.io.OutputStreamWriter
