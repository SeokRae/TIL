---
description: 자바 기반 공부하기
---

# Java

- 자바 기반 관련 개념 정리

## [Cracking the Coding Interview](contents/cracking_the_coding_interview.md)

* 코딩 인터뷰 완전분석 [영문](https://www.crackingthecodinginterview.com/), [번역본](https://blog.insightbook.co.kr/2017/08/07/%ec%bd%94%eb%94%a9-%ec%9d%b8%ed%84%b0%eb%b7%b0-%ec%99%84%ec%a0%84-%eb%b6%84%ec%84%9d-189%ea%b0%80%ec%a7%80-%ed%94%84%eb%a1%9c%ea%b7%b8%eb%9e%98%eb%b0%8d-%eb%ac%b8%ec%a0%9c%ec%99%80-%ed%95%b4%eb%b2%95/)
* 코딩 테스트에 대한 감이 없어서 공부
* 자료구조와 해당 자료구조에 대한 접근법\(알고리즘\)을 차근차근 공부하면 좋아보임
* 코딩 테스트 연습을 할 수 있는 사이트와 병행하는 것이 좋을 듯

### Contents

* [배열과 문자열](contents/cci/array_string.md)
* [연결리스트](contents/cci/linked_list.md)
* [스택 & 큐](contents/cci/stack_queue.md)
* [트리](contents/cci/tree.md)
* [그래프](contents/cci/graph.md)

## [TDD, Clean Code with Java 11기](contents/tdd.md)

* nextstep 의 TDD, Clean Code with Java 11기 진행
* TDD, OOP 관점에 대한 이해
* 개발 자유도가 높아 구조적인 것 외에는 코드가 다 다를 수 있음

### Contents

* 단순한 로직으로 OOP 패턴 구현하기
    * 메서드의 분리
    * TDD 실습 및 습관화를 위한 연습

* [자동차 경주](contents/tdd/racing.md)

    * 경험해야할 학습 목표
        * Github 기반으로 온라인 코드 리뷰하는 경험
        * JUnit 사용법을 익혀 단위 테스트하는 경험
        * 자바 code convention을 지키면서 프로그래밍하는 경험
        * 메소드를 분리하는 리팩토링 경험
    * 경험할 객체지향 생활 체조 원칙
        * 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
        * 규칙 2: else 예약어를 쓰지 않는다.
        * 이 두가지 원칙을 통해 메소드를 분리해 메소드가 한 가지 작업만 담당하도록 구현하는 연습을 목표로 한다.
        * 이 같은 원칙 아래에서 메소드의 라인 수를 15라인이 넘지 않도록 구현한다.

* [로또](contents/tdd/lotto.md)

    * 경험해야할 학습 목표
        * TDD 기반으로 프로그래밍하는 경험
        * 메소드 분리 + 클래스를 분리하는 리팩토링 경험
        * 점진적으로 리팩토링하는 경험
    * 경험할 객체지향 생활 체조 원칙
        * 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
        * 규칙 2: else 예약어를 쓰지 않는다.
        * 규칙 3: 모든 원시값과 문자열을 포장한다.
        * 규칙 5: 줄여쓰지 않는다\(축약 금지\).
        * 규칙 8: 일급 콜렉션을 쓴다.

* [사다리타기](contents/tdd/ladder.md) - FP, OOP

    * 경험해야할 학습 목표
        * 자바8의 스트림, 람다를 사용해 함수형 프로그래밍하는 경험
        * In -&gt; Out, Out -&gt; In 방식으로 도메인 객체를 설계하는 경험
        * 책임주도설계 기반으로 인터페이스 활용해 프로그래밍하는 연습
    * 경험할 객체지향 생활 체조 원칙
        * 규칙 4: 한 줄에 점을 하나만 찍는다.
        * Clean Code 가이드의 디미터 법칙을 지키는 것을 의미한다.
        * 규칙 6: 모든 엔티티를 작게 유지한다.
        * 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
        * 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.

* [볼링 점수판](contents/tdd/bowling.md) - OOP

    * 경험해야할 학습 목표
        * Q&A 서비스를 활용해 레거시 코드를 리팩토링하는 경험
        * 지금까지 학습한 내용을 기반으로 TDD, 클린코드, 객체지향 프로그래밍하는 경험

    * 객체지향 생활 체조 원칙
        * 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
        * 규칙 2: else 예약어를 쓰지 않는다.
        * 규칙 3: 모든 원시값과 문자열을 포장한다.
        * 규칙 4: 한 줄에 점을 하나만 찍는다.
        * 규칙 5: 줄여쓰지 않는다(축약 금지).
        * 규칙 6: 모든 엔티티를 작게 유지한다.
        * 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
        * 규칙 8: 일급 콜렉션을 쓴다.
        * 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.

> GRASP 

* GRASP - General Responsibility Assignment Software Patterns
* 책임 기반 객체지향 관점에서 객체에 책임을 할당하기 위한 패턴을 정리한 것을 의미
* [책임할당에 기반한 객체 설계 원칙](http://contents.kocw.or.kr/KOCW/document/2014/Seowon/SongHaesang/08.pdf) : applying uml and patterns 책의 GRASP 설명 정리한 내용
* [GRASP](http://www.kamilgrzybek.com/design/grasp-explained/) : GRASP을 잘 설명하고 있는 영어로 된 문서
      
* Information Expert
    * 역할을 수행할 수 있는 정보를 가지고 있는 객체에 역할을 부여하자.
    * Problem: What is a basic principle by which to assign responsibilities to objects?
    * Solution: Assign a responsibility to the class that has the information needed to fulfill it.
* Creator
    * 객체의 생성은 생성되는 객체의 컨텍스트를 알고 있는 다른 객체가 있다면, 컨텍스트를 알고 있는 객체에 부여하자.
    * Problem: Who creates object A?
    * Solution: Assign class B the responsibility to create object A if one of these is true (more is better)
        * B contains or compositely aggregates A
        * B records A
        * B closely uses A
        * B has the initializing data for A
* Controller
    * 시스템 이벤트(사용자의 요청)를 처리할 객체를 만들자. 시스템, 서브시스템으로 들어오는 외부 요청을 처리하는 객체를 만들어 사용하라.
    * Problem: What first object beyond the UI layer receives and coordinates “controls” a system operation?
    * Solution: Assign the responsibility to an object representing one of these choices:
    * Represents the overall “system”, “root object”, device that the software is running within, or a major 
      subsystem (these are all variations of a facade controller)
    * Represents a use case scenario within which the system operation occurs (a use case or session controller)
* Low Coupling
    * 객체들간, 서브 시스템들간의 상호의존도가 낮게 역할을 부여하자.
    * Problem: How to reduce the impact of change? How to support low dependency and increased reuse?
    * Solution: Assign responsibilities so that (unnecessary) coupling remains low. Use this principle to evaluate 
      alternatives.
* High Cohesion
    * 각 객체가 밀접하게 연관된 역할들만 가지도록 역할을 부여하자.
    * Problem: How to keep objects focused, understandable, manageable and as a side effect support Low Coupling?
    * Solution: Assign a responsibility so that cohesion remains high. Use this to evaluate alternatives.
* Indirection
    * 두 객체 사이의 직접적인 Coupling을 피하고 싶으면, 그 사이에 다른 객체를 사용하라.
    * Problem: Where to assign a responsibility to avoid direct coupling between two or more things?
    * Solution: Assign the responsibility to an intermediate object to mediate between other components or services 
      so that they are not directly coupled.
* Polymorphism
    * 객체의 종류에 따라 행동양식이 바뀐다면, Polymorphism 기능을 사용하자.
    * Problem: How handle alternatives based on type?
    * Solution: When related alternatives or behaviors vary by type (class), assingn responsibility for the 
      behavior (using polymorphi operations) to the types for which the behavior varies.
* Pure Fabrication
    * Information Expert 패턴을 적용하면 Low Coupling과 High Cohesion의 원칙이 깨어진다면, 기능적인 역할을 별도로 한 곳으로 모으자.
    * Problem: What object should have the responsibility, when you do not want to viloate High Cohesion and Low 
      Coupling but solutions offered by other principles are not appopriate?
    * Solution: Assign a highly cohesive set of responsibilites to an artifical or convenience class that does not 
      represent a problem domain conecept.
* Protected Variations
    * 변경될 여지가 있는 곳에 안정된 인터페이스를 정의해서 사용하자.
    * Problem: How to design objects, subsystems and systems so that the variations or instability in these 
      elements does not have an undesirable impact on other elements?
    * Solution: Identify points of predicted variation or instability, assign responsibilities to create a stable 
      interface around them.
            
> 객체지향 5원칙(SOLID)

* SRP (단일책임의 원칙: Single Responsibility Principle)
    * 작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축: axis of change)을 수행하는 데 집중되어 있어야 한다
* OCP (개방폐쇄의 원칙: Open Close Principle)
    * 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다.
* LSP (리스코브 치환의 원칙: The Liskov Substitution Principle)
    * 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다. 즉, 서브 타입은 언제나 기반 타입과 호환될 수 있어야 한다.
* ISP (인터페이스 분리의 원칙: Interface Segregation Principle)
    * 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다.
* DIP (의존성역전의 원칙: Dependency Inversion Principle)
    * 구조적 디자인에서 발생하던 하위 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의 역전 원칙이다.
