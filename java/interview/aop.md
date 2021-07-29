---
description: 스프링 질문
---

# AOP(Aspect-oriented Programming)

## Intro

- 어플리케이션 전체에 걸쳐 사용되는 기능을 재사용할 수 있는 컴포넌트
- 시스템의 여러 컴포넌트에 관련되는 경향이 있으므로 횡단 관심사(cross-cutting concerns)이라 한다.

## AOP가 적용되지 않는 경우

- 시스템 전반에 걸친 관심사를 구현하는 코드가 여러 컴포넌트에 중복되어 나타난다.
- 관심사 구현에 대한 수정이 필요한 경우 여러 컴포넌트를 모두 변경해야 한다.

- 컴포넌트의 코드가 본연의 기능과 관련 없는 코드로 인해 지저분해진다.

## AOP 용어

- Target
	- 부가 기능을 사용할 대상

- Advice
	- 부가기능
	- Before, AfterReturning, AfterThrowing, After, Around

- Join point
	- 사용 위치
	- 메서드, 필드, 객체, 생성자

- PointCut
	- 실제 advice가 적용되는 지점
	- 스프링은 advice가 적용되는 메서드를 선정

## AOP 구현 방법

- 컴파일
	- .java -> .class

- 클래스 로드시
	- .class

- 프록시 패턴
	- proxy로 wrapping
