---
description: 자바 기반 공부하기
---

# Java

* 자바 기반 관련 개념 정리

## [Cracking the Coding Interview](cracking_the_coding_interview.md)

* 코딩 인터뷰 완전분석 [영문](https://www.crackingthecodinginterview.com/)

  , [번역본](https://blog.insightbook.co.kr/2017/08/07/%ec%bd%94%eb%94%a9-%ec%9d%b8%ed%84%b0%eb%b7%b0-%ec%99%84%ec%a0%84-%eb%b6%84%ec%84%9d-189%ea%b0%80%ec%a7%80-%ed%94%84%eb%a1%9c%ea%b7%b8%eb%9e%98%eb%b0%8d-%eb%ac%b8%ec%a0%9c%ec%99%80-%ed%95%b4%eb%b2%95/)

* 코딩 테스트에 대한 감이 없어서 공부
* 자료구조와 해당 자료구조에 대한 접근법\(알고리즘\)을 차근차근 공부하면 좋아보임
* 코딩 테스트 연습을 할 수 있는 사이트와 병행하는 것이 좋을 듯

### Contents

* [배열과 문자열](https://github.com/SeokRae/TIL/tree/27b5b8caab8e8306e370774974f989ea83a2b1ca/java/contents/cci/array_string.md)
* [연결리스트](https://github.com/SeokRae/TIL/tree/27b5b8caab8e8306e370774974f989ea83a2b1ca/java/contents/cci/linked_list.md)
* [스택 & 큐](https://github.com/SeokRae/TIL/tree/27b5b8caab8e8306e370774974f989ea83a2b1ca/java/contents/cci/stack_queue.md)
* [트리](https://github.com/SeokRae/TIL/tree/27b5b8caab8e8306e370774974f989ea83a2b1ca/java/contents/cci/tree.md)
* [그래프](https://github.com/SeokRae/TIL/tree/27b5b8caab8e8306e370774974f989ea83a2b1ca/java/contents/cci/graph.md)

## [TDD, Clean Code with Java 11기](tdd/)

* nextstep 의 TDD, Clean Code with Java 11기 진행
* TDD, OOP 관점에 대한 이해
* 개발 자유도가 높아 구조적인 것 외에는 코드가 다 다를 수 있음

### Contents

* 단순한 로직으로 OOP 패턴 구현하기
	* 메서드의 분리
	* TDD 실습 및 습관화를 위한 연습
* [자동차 경주](tdd/racing.md)
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
* [로또](tdd/lotto.md)
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
* [사다리 타기](tdd/ladder.md) - FP, OOP
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
* [볼링 게임 점수판](tdd/bowling.md) - OOP
	* 경험해야할 학습 목표
		* Q&A 서비스를 활용해 레거시 코드를 리팩토링하는 경험
		* 지금까지 학습한 내용을 기반으로 TDD, 클린코드, 객체지향 프로그래밍하는 경험
	* 객체지향 생활 체조 원칙
		* 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
		* 규칙 2: else 예약어를 쓰지 않는다.
		* 규칙 3: 모든 원시값과 문자열을 포장한다.
		* 규칙 4: 한 줄에 점을 하나만 찍는다.
		* 규칙 5: 줄여쓰지 않는다\(축약 금지\).
		* 규칙 6: 모든 엔티티를 작게 유지한다.
		* 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
		* 규칙 8: 일급 콜렉션을 쓴다.
		* 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.

## Effective Java

* [이펙티브 자바](effective/)
	* 객체 생성과 파괴
	* [Item 1 - 생성자 대신 정적 팩터리 메서드를 고려하라](https://github.com/SeokRae/TIL/tree/a4b39a9f4d4d80d2e9422187995f991dd679db49/java/contents/effactive/item_1.md)
	* [Item 7 - 다 쓴 객체 참조를 해제하라](effective/item_7.md)
	* [Item 7 발표 내용](effective/item7/item_7_ppt.md)
	* [Item 13 - clone 재정의는 주의해서 진행하라](effective/item_13.md)
	* [Item 13 발표 내용](effective/item13/item_13_mystyle.md)
	* [Item 16 - public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라](effective/item_16.md)
	* [Item 16 발표 내용](effective/item16/item_16_ppt.md)
	* [Item 26 - 로 타입은 사용하지 말라](effective/item_26.md)
	* [Item 28 - 배열보다는 리스트를 사용하라](effective/item_28.md)
	* [Item 28 발표 내용](effective/item28/item_28_ppt.md)
	* [Item 29 - 이왕이면 제네릭 타입으로 만들라](effective/item_29.md)
	* [Item 30 - 이왕이면 제네릭 메서드로 만들라](effective/item_30.md)
	* [Item 31 - 한정적 와일드 카드를 사용해 API 유연성을 높이라](effective/item_31.md)
	* [Item 35 - ordinal 메서드 대신 인스턴스 필드를 사용하라](effective/item_35.md)
	* [Item 37 - ordinal 인덱싱 대신 EnumMap을 사용하라](effective/item_37.md)
	* [Item 37 발표 내용](effective/item37/item_37_ppt.md)
	* [Item 43 - 람다보다는 메서드 참조를 사용하라](effective/item_43.md)
	* [Item 43 발표 내용](effective/item43/item43_ppt.md)
	* [Item 56 - 공개된 API 요소에는 항상 문서화 주석을 작성하라](effective/item_56.md)
	* [Item 56 발표 내용](effective/item56/item_56_ppt.md)
	* [Item 62 - 다른 타입이 적절하다면 문자열 사용을 피하라](java/effective/item_62.md)
	* [Item 62 발표 내용](effective/item62/item_62_ppt.md)
	* [Item 73 - 추상화 수준에 맞는 예외를 던지라](effective/item_73.md)
	* [Item 83 - 지연 초기화는 신중히 사용하라](effective/item_83.md)
	* [Item 83 발표 내용](effective/item83/item_83_ppt.md)

## 궁금한 부분

* Java
    * [자바8 이상에서 인터페이스와 추상클래스의 차이](interview/interface_vs_abstract.md)
    * [자바의 제네릭은 어떻게 이전 버전과 호환되는 걸까?](interview/generics.md)
    * [스프링 MVC 기본 구조](interview/dispatcher_servlet.md)
    * [마샬링과 직렬화](interview/marshalling_n_serialization.md)
    * [데이터 캐싱](interview/data_cache.md)
