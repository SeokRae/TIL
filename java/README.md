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