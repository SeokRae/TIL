---
description: Behavioral Pattern
---

# Iterator Pattern

## Intro

- 필드에 대한 정보를 노출하지 않고 컬렉션의 요소를 탐색할 수 있는 패턴

## 패턴이 필요한 상황

- 컬렉션에 복잡한 데이터 구조가 있지만 클라이언트로부터 복잡성을 숨기려는 경우 Iterator 패턴을 사용한다.
- 패턴을 사용하여 순회 코드의 중복을 줄일 수 있다.
- 코드가 다른 데이터 구조를 탐색 할 수 있도록하거나 이러한 구조 유형을 미리 알 수 없는 경우 Iterator 를 사용한다.

## 구현 방법

1. Iterator 인터페이스를 선언
	- 최소한 컬렉션에서 다음 요소를 가져 오는 메서드가 있어야 한다.
	- 그러나 편의를 위해 이전 요소 가져오기, 현재 위치 추적 및 반복 종료 확인과 같은 몇 가지 다른 방법을 추가할 수 있다.
	
2. 컬렉션 인터페이스를 선언하고 Iterator 를 가져오는 방법을 설명
	- 반환 타입은 Iterator 인터페이스의 타입과 같아야 한다.
	- 여러 개의 고유한 Iterator 그룹을 보유하려는 경우 유사한 메서드를 선언할 수 있다.
	
3. Iterator를 사용하여 순회할 수 있는 컬렉션에 대한 구체적인 Iterator 클래스를 구현한다.
	- Iterator 개체는 단일 컬렉션 인스턴스와 연결되어야 한다.
	- 일반적으로 이 링크는 Iterator의 생성자를 통해 결정된다.
	
4. 컬렉션 클래스에서 컬렉션 인터페이스를 구현한다.
	- 주요 아이디어는 클라이언트에게 특정 컬렉션 클래스에 맞게 조정된 Iterator를 만드는 API를 제공하는 것이다.
	- 컬렉션 객체는 그들 사이에 링크를 설정하기 위해 반복자의 생성자에 자신을 전달해야 한다.
	
5. 클라이언트 코드를 검토하여 모든 컬렉션 순회 코드를 Iterator를 사용하여 교체한다.
	- 클라이언트는 컬렉션 요소를 반복해야 할 때 새로운 Iterator 개체를 가져온다.
	
## 장점

- SRP: 부치가 큰 순회 알고리즘을 별도의 클래스로 추출하여 클라이언트 코드와 컬렉션을 정리할 수 있다.
- OCP: 새로운 유형의 컬렉션 및 Itrator를 구현하고 아무것도 중단하지 않고 기존 코드에 전달할 수 있다.
- 각 Iterator 객체에는 자체 반복 상태가 포함될 수 있으므로 동일한 컬렉션을 병렬로 반복할 수 있다.
- 단순 컬렉션에만 작동하는 경우 패턴이 과도한 작업이 될 수 있다.
- Iterator를 사용하는 것은 일부 특수 컬렉션의 요소를 직접 살펴보는 것보다 덜 효율적일 수 있다.