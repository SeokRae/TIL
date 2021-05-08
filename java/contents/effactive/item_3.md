---
description: private 생성자나 열거 타입으로 싱글턴임을 보증하라
---

# Item 3. Singleton Pattern

## Intro

### 싱글턴을 만드는 방식 3 가지

> 싱글턴을 작성하기 위한 공통점

- 생성자를 private 으로 감춰두고, 유일한 인스턴스에 접근할 수 있는 수단으로 public static 멤버를 하나 마련해둔다.

> 1. public static 멤버가 final 필드인 방식

> 2. 정적 팩터리 메서드를 public static 멤버로 제공하는 방식

> 3. 원소가 하나인 열거 타입을 선언하는 방식

### 싱글턴의 예외 상황

### 직렬화
