---
description: 일반적으로 통용되는 명명 규칙을 따르라
---

# Item 68

## Intro

- 자바 플랫폼은 명명 규칙이 잘 정립되어 있으며, 그 중 많은 것이 자바 언어 명세서에 기술되어 있다.
	- 철자 규칙, 매키지와 모듈 이름, 클래스와 인터페이스, 상수 필드, 지역 변수, 타입 매개변수
	
- 문법 규칙은 철자 규칙과 비교하면 더 유연하고 논란도 많다.
	- 객체를 생성할 수 있는 클래스
	- 인터페이스
	- 어노테이션
	- 메서드
	- 반환 타입
	
- 특수한 메서드 명
	- 다른 타입의 또 다른 객체를 반환하는 인스턴스 메서드의 이름 (to`Type`)
	- 정적 팩토리 (from, of, valueOf, instance, getInstance, newInstance, get`Type`, new`Type`)
	
- 필드 이름에 관한 문법 규칙은 덜 명확하고 덜 중요하다.
	- API를 잘 설계 했다면 필드가 노출될 일이 거의 없기 때문이다.

> 생각해보기

- [Oracle Java Convention](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf)
