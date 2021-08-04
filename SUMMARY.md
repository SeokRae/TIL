# Table of contents

* [Contents](README.md)

## Java <a id="java-1"></a>

---

* [Design Pattern](design/README.md)
	* [Intro](design/_intro.md)
	* [Types of Design Patterns](design/_types_of_design_patterns.md)
	* Creational
		* [Builder Pattern](design/creational/_builder.md)
		* [Singleton Pattern](design/creational/_singleton.md)
		* [Prototype Pattern](design/creational/_prototype.md)
		* [Factory Pattern](design/creational/_factory.md)
		* [Abstract Factory Pattern](design/creational/_abstract_factory.md)
	* Structural
		* [Adapter Pattern](design/structural/_adapter.md)
		* [Bridge Pattern](design/structural/_bridge.md)
		* [Composite Pattern](design/structural/_composite.md)
		* [**`Decorator Pattern`**](design/structural/_decorator.md)
		* [Facade Pattern](design/structural/_facade.md)
		* [Flyweight Pattern](design/structural/_flyweight.md)
		* [Proxy Pattern](design/structural/_proxy.md)
	* Behavioural
		* [Chain of Responsibility Pattern](design/behavioral/_chain_of_responsibility.md)
		* [Command Pattern](design/behavioral/_command.md)
		* [Interpreter Pattern](design/behavioral/_interpreter.md)
		* [Iterator Pattern](design/behavioral/_iterator.md)
		* [Mediator Pattern](design/behavioral/_mediator.md)
		* [Memento Pattern](design/behavioral/_memento.md)
		* [Observer Pattern](design/behavioral/_observer.md)
		* [State Pattern](design/behavioral/_state.md)
		* [Strategy Pattern](/design/behavioral/_strategy.md)
		* [Template Method Pattern](/design/behavioral/_template_method.md)
		* [**`Visitor Pattern`**](/design/behavioral/_visitor.md)

* [오브젝트](object/README.md)
	* [데이터 중심 설계](object/_1.md)
	* [책임 중심 설계](object/_2.md)
	* [책임 할당을 위한 GRASP 패턴](object/_3.md)
	* [메시지와 인터페이스](object/_4.md)

* [Java](java/README.md)
	* [Cracking the Coding Interview](java/cracking_the_coding_interview.md)
	* [TDD, Clean Code with Java 11기](java/tdd/README.md)
		* [자동차 레이싱](java/tdd/racing.md)
		* [로또](java/tdd/lotto.md)
		* [사다리 타기](java/tdd/ladder.md)
		* [볼링 게임 점수판](java/tdd/bowling.md)

* [Effective Java](java/effective/README.md)
	* [Item 7 - 다 쓴 객체 참조를 해제하라](java/effective/item_7.md)
	* [Item 7 발표 내용](java/effective/item7/item_7_ppt.md)
	* [Item 13 - clone 재정의는 주의해서 진행하라](java/effective/item_13.md)
	* [Item 13 발표 내용](java/effective/item13/item_13_mystyle.md)
	* [Item 16 - public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라](java/effective/item_16.md)
	* [Item 16 발표 내용](java/effective/item16/item_16_ppt.md)
	* [Item 26 - 로 타입은 사용하지 말라](java/effective/item_26.md)
	* [Item 28 - 배열보다는 리스트를 사용하라](java/effective/item_28.md)
	* [Item 28 발표 내용](java/effective/item28/item_28_ppt.md)
	* [Item 29 - 이왕이면 제네릭 타입으로 만들라](java/effective/item_29.md)
	* [Item 30 - 이왕이면 제네릭 메서드로 만들라](java/effective/item_30.md)
	* [Item 31 - 한정적 와일드 카드를 사용해 API 유연성을 높이라](java/effective/item_31.md)
	* [Item 35 - ordinal 메서드 대신 인스턴스 필드를 사용하라](java/effective/item_35.md)
	* [Item 37 - ordinal 인덱싱 대신 EnumMap을 사용하라](java/effective/item_37.md)
	* [Item 37 발표 내용](java/effective/item37/item_37_ppt.md)
	* [Item 43 - 람다보다는 메서드 참조를 사용하라](java/effective/item_43.md)
	* [Item 43 발표 정리](java/effective/item43/item43_ppt.md)
	* [Item 56 - 공개된 API 요소에는 항상 문서화 주석을 작성하라](java/effective/item_56.md)
	* [Item 56 발표 정리](java/effective/item56/item_56_ppt.md)
	* [Item 62 - 다른 타입이 적절하다면 문자열 사용을 피하라](java/effective/item_62.md)
	* [Item 62 발표 정리](java/effective/item62/item_62_ppt.md)
	* [Item 73 - 추상화 수준에 맞는 예외를 던지라](java/effective/item_73.md)
	* [Item 83 - 지연 초기화는 신중히 사용하라](java/effective/item_83.md)
	* [Item 83 발표 내용](java/effective/item83/item_83_ppt.md)
	* [Item 89 - 인스턴스 수를 통제해야 한다면 readResolve보다는 열거 타입을 사용하라](java/effective/item_89.md)
	* [Item 89 발표 내용](java/effective/item89/item_89_ppt.md)

* 궁금증
	* [자바 8 버전의 인터페이스와 추상클래스](/java/interview/interface_vs_abstract.md)
	* [자바의 제네릭은 어떻게 이전 버전과 호환되는 걸까?](/java/interview/generics.md)
	* [스프링 MVC 기본 구조](/java/interview/dispatcher_servlet.md)
	* [마샬링과 직렬화](/java/interview/marshalling_n_serialization.md)

## Database

---

* [Database](database/README.md)
	* [SQL 레벨업](database/sql_levelup/README.md)
		* [1강 DBMS 아키텍처 개요](database/sql_levelup/_1.md)
		* [2강 DBMS와 버퍼](database/sql_levelup/_2.md)
		* [3강 DBMS와 실행 계획](database/sql_levelup/_3.md)
		* [4강 실행 계획이 SQL 구문의 성능을 결정](database/sql_levelup/_4.md)
		* [5강 실행 계획의 중요성](database/sql_levelup/_5.md)
		* [6강 SELECT 구문](database/sql_levelup/_6.md)
		* [7강 조건 분기, 집합 연산, 윈도우 함수, 갱신](database/sql_levelup/_7.md)
		* [8강 UNION을 사용한 쓸데없이 긴 표현](database/sql_levelup/_8.md)

	* [개발자를 위한 SQL 튜닝](database/tune/README.md)
		* [SQL 쿼리 실습을 위한 DB 서버 구축](database/tune/_1.md)
		* [**인덱스 튜닝**](database/tune/_1_1.md)
		* [인덱스 스캔 튜닝](database/tune/_2.md)
		* [인덱스 스캔 튜닝 실습](database/tune/_3.md)
		* [인덱스 패스트 풀 스캔](database/tune/_4.md)
		* [테이블 풀 스캔 튜닝](database/tune/_5.md)
		* [**조인 튜닝**](database/tune/_6.md)
		* [중첩 루프 조인 튜닝](database/tune/_7.md)
		* [중첩 루프 조인 튜닝 실습](database/tune/_8.md)
		* [**해시 조인 튜닝**](database/tune/_9.md)
		* [해시 조인 튜닝 실습](database/tune/_9_1.md)
		* [**세미 조인 튜닝**](database/tune/_10.md)
		* [세미 조인 튜닝 실습](database/tune/_10_1.md)
		* [아우터 조인](database/tune/_11.md)

	* [DB 스터디](database/easy_db/2021-07-17-db-toc.md)
        * [DBMS](database/easy_db/contents/2021-07-17-db-dbms.md)
            * [MySQL](database/easy_db/contents/2021-07-24-db-mysql-architecture.md)
        * [INDEX](database/easy_db/contents/2021-07-23-db-index.md)
        * [JOIN](database/easy_db/contents/2021-08-07-db-join.md)

## Network

---

* [Network]()
	* [Web Security](network/contents/web_security.md)

## Infra

---

* [Docker]()
	* [Oracle with Docker](/docker/database/oracle_container.md)
	* [MySQL with Docker](/docker/database/mysql_container.md)

## Spring

---

* [Spring JPA](spring-jpa/README.md)
	* [1. 데이터 모델링 및 연관관계 설정](spring-jpa/springboot-jpa-shop.md)
	* [2. 최적화 내용](spring-jpa/springboot-jpa-shop-optimize.md)
	* [3. Spring-Data-Jpa](spring-jpa/springboot-jpa-data.md)
	* [4. Query DSL](spring-jpa/springboot-jpa-querydsl.md)

* [Spring Security](spring-security/README.md)
	* [Intro](spring-security/contents/_1.md)

* [Spring Batch](batch-study/README.md)
	- [배치용 디비 설치](/batch-study/contents/_1.md)
	- [배치 데이터 분석하기](/batch-study/contents/_2.md)
	- [배치 프로세스 구상하기 및 성능 차이 확인하기](/batch-study/contents/_3.md)

## Tistory

---

* [Tistory Blog](https://seokr.tistory.com/)
