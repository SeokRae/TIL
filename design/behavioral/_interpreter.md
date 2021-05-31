---
description: Behavioral Pattern
---

# Interpreter Pattern

## Intro

- 언어 문법이나 표현을 평가하는 방법을 제공하는 패턴
- 특정 컨텍스트를 해석하도록 지시하는 표현식 인터페이스 구현을 포함한다.

## 구성

- Expression 인터페이스를 구현한 구체화 클래스를 작성
- 해당 컨텍스트의 주요 인터프리터 역할을 하는 TerminalExpression 클래스를 정의
- 그 외에 OrExpression, AndExpression은 조합식을 만드는 데 사용된다.
- 데모 클래스는 Expression 클래스를 사용하여 규칙을 만들고 구분 분석을 보여준다.

### 인터프리터

- Terminal
	- 형식적인 문법으로 정의된 언어의 기본 기호

- non-terminal
	- 생산 규칙에 따라 터미널 기호 그룹으로 대체되는 구문 변수
	- 일반적으로 합성 패턴을 사용한다.
