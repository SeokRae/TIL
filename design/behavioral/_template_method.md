---
description: Behavioral Pattern
---

# Template Method Pattern

## Intro

![Intro](/design/behavioral/template/template_method.001.png)

![Reference](/design/behavioral/template/template_method.002.png)

## Template Method 패턴이란?

![What is Template Method](/design/behavioral/template/template_method.003.png)

## Template Method 패턴의 아키텍처

- 훅(hook) 메서드란?
	- 슈퍼 클래스에서 디폴트 기능을 정의해두거나 비워뒀다가 서브 클래스에서 선택적으로 재정의 할 수 있도록 만들어 둔 메서드
	- 서브 클래스에서 추상 메서드를 구현하거나, 훅 메서드를 재정의하는 방법을 이용하여 기능의 일부를 확장

![Architecture](/design/behavioral/template/template_method.004.png)

## Template Method 패턴의 예시

![Example](/design/behavioral/template/template_method.005.png)

## 적용 가능한 상황

![Applicability](/design/behavioral/template/template_method.006.png)

## 장 단점

![Pros and Cons](/design/behavioral/template/template_method.007.png)

## 다른 디자인 패턴과의 관계

![Relations with Other Patterns](/design/behavioral/template/template_method.008.png)
