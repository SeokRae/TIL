---
description: public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
---

# Item 16 발표 내용

## Intro

![intro](/java/effactive/item16/images/item16.001.png)

## 참조 자료 및 목차

- 참고 자료
	- [Access Modifiers in Java](https://www.baeldung.com/java-access-modifiers)
	- [Controlling Access to Members of a Class](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)

![reference](/java/effactive/item16/images/item16.002.png)

## 캡슐화에 대한 브레인 스토밍

![Object Oriented Principle](/java/effactive/item16/images/item16.003.png)

## 캡슐화를 위반하여 생기는 OOP 원칙

- 객체 지향 설계 5원칙 (SOLID)
	- SRP: Single Responsibility Principle
	- OCP: Open Cloned Principle
	- LSP: Liskov Substitution Principle
	- ISP: Interface Segregation Principle
	- DIP: Dependency Inversion Principle

![캡슐화와 연관된 OOP 원칙](/java/effactive/item16/images/item16.004.png)

## 자바에서 제공하는 캡슐화 기능

- Java
	- Access Modifier
		- private
		- protected
		- default (package-private)
		- public

![Access Modifier](/java/effactive/item16/images/item16.005.png)

## public 클래스에서 상호작용하는 방식

![public 클래스](/java/effactive/item16/images/item16.006.png)

## package-private or private 클래스에서 상호작용하는 방식

![package-private or private 클래스](/java/effactive/item16/images/item16.007.png)

## 정리

![정리](/java/effactive/item16/images/item16.008.png)
