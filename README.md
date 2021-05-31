---
description: Today I Learned
---

# Contents

정리해서 남주기

* [Network](network.md)
* [Design Pattern](design/)
	* [Intro](design/_intro.md)
	* [Types of Design Patterns](design/_types_of_design_patterns.md)
	* [Builder Pattern](design/creational/_builder.md)
	* [Singleton Pattern](design/creational/_singleton.md)
	* [Prototype Pattern](design/creational/_prototype.md)
	* [Factory Pattern](design/creational/_factory.md)
	* [Abstract Factory Pattern](design/creational/_abstract_factory.md)
	* [Adapter Pattern](design/structural/_adapter.md)
	* [Bridge Pattern](design/structural/_bridge.md)
	* [Composite Pattern](design/structural/_composite.md)
	* [**`Decorator Pattern`**](design/structural/_decorator.md)
	* [Facade Pattern](design/structural/_facade.md)
	* [Flyweight Pattern](design/structural/_flyweight.md)
	* [Proxy Pattern](design/structural/_proxy.md)
	* [Chain of Responsibility Pattern](design/behavioral/_chain_of_responsibility.md)
* [오브젝트](object/)
	* [데이터 중심 설계](object/_1.md)
	* [책임 중심 설계](object/_2.md)
	* [책임 할당을 위한 GRASP 패턴](object/_3.md)
	* [메시지와 인터페이스](object/_4.md)
* [Java](java/)
	* [Cracking the Coding Interview](java/cracking_the_coding_interview.md)
	* [TDD, Clean Code with Java 11기](java/tdd/)
		* [자동차 레이싱](java/tdd/racing.md)
		* [로또](java/tdd/lotto.md)
		* [사다리 타기](java/tdd/ladder.md)
		* [볼링 게임 점수판](java/tdd/bowling.md)
* [Effective Java](java/effactive/)
	* [Item 7](java/effactive/item_7.md)
	* [Item 13](java/effactive/item_13.md)
	* [Item 13 발표 내용](java/effactive/item13/item_13_mystyle.md)
	* [Item 16](java/effactive/item_16.md)
	* [Item 16 발표 내용](java/effactive/item16/item_16_ppt.md)
	* [Item 26](java/effactive/item_26.md)
	* [Item 28](java/effactive/item_28.md)
* [Spring Jpa](spring-jpa/)
	* [1. 데이터 모델링 및 연관관계 설정](spring-jpa/springboot-jpa-shop.md)
	* [2. 최적화 내용](spring-jpa/springboot-jpa-shop-optimize.md)
	* [3. Spring-Data-Jpa](spring-jpa/springboot-jpa-data.md)
	* [4. Query DSL](spring-jpa/springboot-jpa-querydsl.md)
* [Spring Security](https://github.com/SeokRae/TIL/tree/de1295942811b3db0b575e366f53c4d5736bb991/spring-security.md)
* [Spring Batch](batch-study/)


* 월요일

| 주제 | Tags | 참여인원 | Github | 남은기간 | 요약 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 스프링 부트 웹 기본 | 완료 | SR 외 7인 | [Link](https://github.com/spring-org/springboot_board) | 2020/06/08 ~ 2020/08/20 | - SpringBoot 기반 웹 프로젝트 개발 시 필요한 내용이 무엇인지 파악하게 된 스터디 - SpringBoot, JPA, Swagger를 이용한 backend API Server 개발 - Spring JPA, Security, Batch |
| 스프링 부트 시큐리티 | 완료 | SR 외 7인 | [Link](https://github.com/spring-org/spring-security) | 2020/09/18 ~ 2020/12/16 | - Spring Security 학습 스터디 - 현업에서 커스텀되어 개발된 프로젝트에서 시큐리티 부분이 어떤 부분인지 알게된 스터디 - 스프링 시큐리티 기본 API & Filter - Security 주요 아키텍처 이해 - Authentication 프로세스 구현 \(Form인증, Ajax 인증\) - Authorization 프로세스 구현 \(DB 연동\) |
| 알고리즘 | 완료 | SR 외 7인 | [Link](https://github.com/SeokRae/java_sample/tree/master/programmers) | 2020/10/13 ~ 2021/01/13 | - 프로그래머스 코딩테스트 고득점 Kit - 알고리즘 학습하는 방법을 배우는 스터디 - 해시, 스택/큐, 힙, 정렬, 완전탐색, 탐욕법 까지 진행하고 마무리 |
| 코딩 인터뷰 완전 분석 | 완료 | SR 외 7인 | [Link](https://github.com/SeokRae/java-in-action/tree/master/java-in-interview) | 2021/01/19 ~ 2021/04/12 | - 자료구조 학습 - 알고리즘에서 사용할 수 있는, 자바에서 제공하는 자료구조를 구현하기 - leetCode를 참조 |
| 디자인 패턴 | 진행중 | SR 외 7인 | [Link](https://seokrae.gitbook.io/sr/design) | 2021/04/19 ~ 2021/06/07 | - 디자인 패턴 - 창조 패턴 - 구조 패턴 - 행동 패턴 |

* 토요일

| 주제 | Tags | 참여인원 | Github | 남은기간 | 요약 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 스프링 배치 | 완료 | SR 외 4인 | [Link](https://github.com/spring-org/springbatch_summary) | 2020/08/20 ~ 2020/12/12 | - Spring Batch 기본 개념 학습 - 기본 프로세스 학습할 수 있는 구조 설계\(File, DB\) - 메타 스키마 분석, 스케쥴링 방식 - 기본 프로세스에서 최적화 방식 학습 |
| 엘라스틱 서치 | 완료 | SR 외 4인 | - | 2020/12/10 ~ 2021/02/02 | - ElasticSearch 기본 개념과 사용하는 방식을 공부하기 위한 스터디 - Single Cluster 구축, 인덱스 구축 방식, client 기반 crud 코드 작성 - 6.x, 7.x 버전 RestHighLevelClient 기반 API 코드 테스트 |
| 기술 스택 정리 | 진행중 | SR 외 1인 | - | 2021/02/25 ~ 2021/03/31 | - ORM\(JPA\) - RDB\(MySQL\) - 오브젝트 - Spring MVC |
