# 4. Query DSL

### 프로젝트 환경 설정

* QueryDSL 설정
	* build.gradle 설정
	* compileQuerydsl
	* `gitignore`에 추가
* QueryDSL 검증
	* sample `entity` 생성 및 테스트 코드 작성
	* `compileQuerydsl` 을 통해 `build`된 Q`Entity Class명`.java 파일 확인
		* gradle console 사용방법

		  ```text
		  ./gradlew clean compileQuerydsl
		  ```
	* Querydsl QType이 정상 동작하는지 확인

### 기본 문법

> [JPQL vs Querydsl](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/JpaVsQuerydsl1문법Test.java)

* JPA 코드 기반 조회쿼리 작성
* Querydsl 기반 조회쿼리 작성
	* `EntityManager`로 `JPAQueryFactory` 클래스를 생성
	* queryFactory를 통해 작성한 쿼리에 QType 클래스를 파라미터로 조회
	* Querydsl은 JPQL Builder의 역할
* JPA vs Querydsl 차이

  | 방식 | JPQL | Querydsl |
      | :--- | :--- | :--- |
  | 작성방식 | 문자 | 코드 |
  | 오류체크시점 | 실행 시점 오류 | 컴파일 시점 오류 |
  | 파라미터 바인딩 방식 | 파라미터 바인딩 | 파라미터 바인딩 자동 처리 |

> 기본 Q-Type 활용

* Q 클래스 인스턴스를 사용하는 2가지 방법
	* alias 직접 설정
	* 기본 인스턴스 사용
		* 같은 테이블을 조인해야 하는 경우가 아니면 기본 인스턴스를 사용
* 개발 시 JPQL 쿼리 로그 확인 방법

  ```text
  spring.jpa.properties.hibernate.use_sql_comments: true
  ```

> [검색 조건 쿼리](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl2검색Test.java)

* `queryFactory`의 `where`메서드에 검색 조건 `.and()`, `.or()`을 사용할 수 있다.
* `.select()`, `.from()` -&gt; `selectFrom()` 으로 축약 가능
* where\(\) 메서드의 경우 predicate ... 를 파라미터로 받아 AND 로 처리하는 것을 기본값으로 한다.
	* 이 경우 null 값은 무시하게 되고 이러한 방식을 이용하여 메서드 추출을 활용하여 동적쿼리를 깔끔하게 만들 수 있다.
* [참고](http://www.querydsl.com/static/querydsl/4.4.0/apidocs/)

> 결과 조회 메서드 종류

* 결과 조회 메서드

  | 메서드명 | 설명 | 예외 |
      | :---: | :--- | :--- |
  | `fetch()` | 리스트 조회 | 데이터가 없는 경우 빈 리스트 반환 |
  | `fetchOne()` | 단 건 조회 | 결과가 없는경우: `null`, 결과가 둘 이상인 경우: `com.querydsl.core.NonUniqueResultException` 예외 |
  | `fetchFirst()` | 가장 상위 데이터를 조회, `limit(1).fetchOne()` 과 동일 |  |
  | `fetchResults()` | 페이징 정보 포함, total count 쿼리 추가 실행 |  |
  | `fetchCount()` | count 쿼리로 변경해서 count 조회 |  |

* 주의사항
	* `fetchResults()` 메서드가 페이징 정보까지 포함하지만, 페이징 쿼리에 부하가 걸리는 경우 `fetchCount()`를 따로 날리는 것이 효율적

> [정렬](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl4정렬Test.java)

* `orderBy()`
	* `desc()`, `asc()`: 일반정렬
	* `nullsLast()`, `nullsFirst()`: null 데이터 순서 부여

> [페이징](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl5페이징Test.java)

* `offset()`, `limit()`
	* `offset()`: 기본 0부터 시작
	* `limit()`: 조회할 데이터 건수
	* count 쿼리가 실행되므로 성능상 주의
* 주의사항
	* 데이터를 조회해야하는 쿼리는 여러 테이블을 조인해야하지만 count 쿼리는 조인이 필요없는 경우가 있다.
	* 자동화된 count 쿼리는 원본 쿼리와 같이 모두 조인하기 때문에 성능이 안나올 수 있다.
	* count 쿼리에 조인이 필요없는 성능 최적화가 필요한 경우 count 전용 쿼리를 별도로 작성할 필요성이 있다.

> [집합](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl6집합Test.java)

* aggregation
	* sum, avg, max, min과 같은 집계성 쿼리
	* [참고](http://www.querydsl.com/static/querydsl/4.4.0/apidocs/)
* Tuple
	* querydsl이 제공하는 Tuple 클래스
	* 데이터의 타입에 상관없이 저장 및 조회 가능
	* 실무에서는 Tuple 대신 DTO로 바로 저장할 수 있게끔 사용하는 방식을 선호한다.
* `groupBy()`, `having()`
	* 데이터 셋에 따른 테스트를 많이 해봐야 할 듯

> [조인 - 기본 조인](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl7조인Test.java)

* 첫 번쨰 파라미터에 조인 대상을 지정, 두 번째 파라미터에 별칭으로 사용할 Q 타입을 지정
* 조인의 종류
	* `join()`, `innerJoin()`: `inner join` 기능
	* `leftJoin()`: `left outer join` 기능
	* `rightJoin()`: `right outer join` 기능
	* `theta join`: from 절에 여러 엔티티를 선택하는 방법
		* 이 방법만으로는 outer join이 불가능
		* `join().on()`을 통해 해결 가능

> 조인 - on 절

* on\(\)을 활용한 조인
	* join 대상 필터링
	* 연관관계 없는 엔티티 외부 조인
* Join 대상 필터링
	* `leftJoin(member.team, team).on(team.name.eq("teamA"))`
	* on 절을 활용해 조인 대상을 필터링 할 때, 외부조인이 아니라 내부조인을 사용하면 where절에서 필터링하는 것과 기능이 동일하다.
	* 따라서 on절을 활용한 조인 대상 필터링을 사용할 때, 내부조인이면 익숙한 where 절로 해결하고, `정말 외부조인이 필요한 경우에만 이 기능을 사용`
* 연관관계 없는 엔티티 외부 조인
	* `.leftJoin(team).on(member.username.eq(team.name))`
		* `leftJoin()` 부분에 일반 조인과 다르게 엔티티가 하나만 들어가는 것을 주의
	* hibernate 5.1 부터 on을 사용한 서로 관계가 없는 필드로 내\(외\)부 조인하는 기능이 추가됨
* on\(\) 절 쿼리 및 연관관계 없는 엔티티 외부조인 정리
	* inner join 활용 시: `join().where(condition)` 권장
	* outer join 사용 시: `join().on()` 권장
	* `leftJoin()`에 엔티티 하나만 들어가는 것을 확인
* 조인 비교
	* 일반 조인: `leftJoin(member.team, team)`
	* on 조인: `from(member).leftJoin(team).on(..)`

> 조인 - Fetch Join

* 성능 최적화를 위해 사용하는 방법
* Fetch Join 유무 비교
	* Fetch Join 미적용
		* 지연로딩을 이용하여 연관관계 엔티티를 각각 조회
	* Fetch Join 적용
		* 즉시로딩으로 연관관계 엔티티를 한 번에 조회
		* `join()`, `leftJoin()` 등 조인 기능 뒤에 `fetchJoin()` 이라고 추가
* 정리
	* 활용2편의 내용을 참고
	* 자주 사용되는 내용

> [서브쿼리](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl8서브쿼리Test.java)

* `com.querydsl.jpa.JPAExpressions` 사용
	* 서브 쿼리 eq
	* 서브 쿼리 goe
	* 서브 쿼리 여러건 처리 in
	* select 절 subquery
* from 절의 서브쿼리 한계
	* JPA JPQL 서브쿼리의 한계점으로 from 절의 서브쿼리\(`인라인 뷰`\)는 **지원하지 않는다.**
	* 당연히 `Querydsl`도 **지원하지 않는다.**
	* `하이버네이트` 구현체를 사용하면 select 절의 서브쿼리는 지원한다.
	* `Querydsl`도 하이버네이트 구현체를 사용하면 select 절의 서브쿼리를 지원한다.
* **\[중요\] from 절의 서브쿼리 해결방안**
	* 서브쿼리를 join으로 변경한다.

	  \(가능한 상황도 있고, 불가능한 상황도 있다.\)

	* 애플리케이션에서 쿼리를 2번 분리해서 실행한다.
	* `NativeSQL`을 사용한다.
* 인라인 뷰의 문제점
	* 데이터를 화면에 맞추려는 쿼리가 인라인 뷰를 복잡하게 하는 경우가 있다.
	* 데이터에 집중하면 인라인 뷰를 많이 줄일 수 있다.
	* 한번에 조회되는 쿼리가 정말 중요한지? 생각해보기

> [Case 문](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl9조건문Test.java)

* select, 조건절\(where\), order by에서 사용 가능
	* 단순한 조건
		* `when().then()`
	* 복잡한 조건
		* `new CaseBuilder().when().then().otherwise()`
* 정리
	* case 같은건 application에서 처리하는게 좋지 않을까 생각하기

> [상수, 문자 더하기](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl10상수및문자더하기Test.java)

* 상수 더하기
	* `Expressions.constant(xxx)`
	* 쿼리는 기본 쿼리가 날아가고 조회된 결과 데이터에 추가 됨
* 문자 더하기
	* `concat()`
	* 문자가 아닌 다른 타입들은 `stringValue()`로 문자로 변환가능
	* enum 타입 같은경우에 활용할 수 있음

### 중급 문법

> [프로젝션과 결과 반환](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl11프로젝션기본Test.java)

* **기본**
	* select 대상 지정: **프로젝션 대상이 하나**
		* 프로젝션 대상이 하나면 타입을 명확하게 지정할 수 있음
	* 튜플 조회: **프로젝션 대상이 둘 이상**
		* `com.querydsl.core.Tuple`
		* 프로젝션 대상이 둘 이상이면 튜플이나 DTO로 조회
* 정리
	* tuple을 사용하는 경우 repository에서 사용한 뒤 dto로 변환해서 넘기는 것을 권장
* **DTO 조회**
	* `순수 JPA에서 DTO 조회`
		* 순수 JPA에서 DTO를 조회할 때는 `new` 명령어를 사용해야한다.
		* DTO의 `package` 이름을 다 적어줘야 한다.
		* 생성자 방식만 지원한다.
		* 사용하기에 불편, 밑에 제시하는 방식을 사용하기
	* `Querydsl 빈 생성`
		* 결과를 DTO 반환 시 사용
		* 프로퍼티 접근
			* `Projections.bean()`
		* 필드 직접 접근
			* `Projections.fields()`
		* 별칭이 다를 때
			* `Projections.fields()` 를 사용하되 아래에서 제시하는 문법을 활용
			* 프로퍼티나, 필드 접근 생성 방식에서 이름이 다를 때 해결 방안
			* `ExpressionUtils.as(source,alias)`: 필드나, 서브 쿼리에 별칭 적용
			* username.as\("memberName"\): 필드에 별칭 적용
		* 생성자 사용
			* `Projections.constructor()`
* `@QueryProjection`
	* 생성자에 `@QueryProjection` 설정 후 compileQuerydsl 실행하여 Q파일 생성
	* `@QueryProjection` 활용
		* 컴파일러로 타입을 체크할 수 있으므로 가장 안전한 방법
	* 단점
		* DTO에 QueryDSL 어노테이션을 유지해야 하는 점과 DTO까지 Q 파일을 생성해야 하는 단점
		* DTO에 QueryDSL에 대한 의존성을 갖게 되는 문제
* 정리
	* 결과 1개 projection, 결과 2개 tuple
	* `tuple`은 repository에서 `dto`로 변환
	* `Projection.constructor()` vs `@QueryProjection`
		* `constructor()`로 사용하는 경우 `RuntimeException` 실행 시점에 오류를 확인가능
		* `@QueryProjection`로 사용하는 경우 `Compile 시점`에 오류를 발생하여 개발 시점에 오류 확인 가능
		* 필드가 많아지면 `Projection`을 사용할 것인지 `QType`을 사용할 것인지 고민을 하게 됨
		* `@QueryProjection`로 사용하는 경우 여러 Layer에 걸쳐서 사용되는 DTO가 Querydsl에 의존되는 문제
			* 하지만 프로젝트에 Querydsl를 의존하여 사용하도록 설계가 되어있는 경우 DTO를 Querydsl에 의존되도록 사용
		* DTO를 순수하게 사용하려는경우 `Projection.constructor()` 를 사용
	* `Projection.bean()`, `Projection.fields()`를 사용하기도 함

> [동적쿼리](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl12동적쿼리Test.java)

* 동적 쿼리를 해결하는 두 가지 방법
* BooleanBuilder 사용
	* 필수값이 존재하는 경우 생성자에 해당 필드를 추가
* **\[중요\] Where 다중 파라미터 사용**
	* where 조건에 `null` 값은 무시된다.
	* 메서드를 다른 쿼리에서도 `재활용`할 수 있다.
	* 쿼리 자체의 `가독성`이 높아진다.
* 정리
	* 두 방법 모두 활용에 따라 다름
	* 메서드별로 재사용할 수 있도록 `설계`하는 것이 중요
* 참고
	* `Predicate` vs `BooleanExpression` 차이 알아보기

> [수정, 삭제 벌크 연산](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl13벌크연산Test.java)

* `execute()` 메서드 사용 시 bulk 쿼리 호출
* **주의사항**
	* 영속성 컨텍스트에 있는 엔티티를 무시하고 실행되기 때문에

	  배치 쿼리를 실행하고 나면 영속성 컨텍스트를 초기화 하는 것이 안전하다.

> [SQL function 호출](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl14SQLFunctionTest.java)

* SQL function은 JPA와 같이 Dialect에 등록된 내용만 호출할 수 있다.
	* `Expressions.stringTemplate()` 사용 함수
		* `function('lower', member1.username)`
	* ANSI 표준 함수
		* `lower(member1.username)`
* dialect 경로
	* `org.hibernate.dialect.{DB}`
* [참고 docs](http://www.querydsl.com/static/querydsl/4.0.8/apidocs/com/querydsl/core/types/dsl/package-summary.html)

### 실무 활용 - 순수 JPA와 Querydsl

> 순수 JPA 리포지토리와 Querydsl

* JPAQueryFactory 스프링 빈 등록
	* JPAQueryFactory 를 스프링 빈으로 등록해서 주입받아 사용가능
	* 스프링이 주입해주는 엔티티 매니저는 실제 동작 시점에 진짜 엔티티 매니저를 찾아주는

	  프록시용 가짜 엔티티 매니저이므로 동시성 문제는 걱정할 필요 없다.

	* 가짜 엔티티 매니저는 실제 사용 시점에 트랜잭션 단위로 실제 엔티티 매니저\(영속성 컨텍스트\)를 할당해준다.
* 정리
	* JPAQueryFactory를 빈으로 생성해서 쓸 수도 있지만 취향차이 일듯..

> [동적 쿼리와 성능 최적화 조회](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl17QuerydslOptimizeTest.java)

* Builder 사용
	* `@QueryProjection`을 추가, QMemberTeamDto 를 생성

	  ```text
	  ./gradlew compileQuerydsl
	  ```

	* QueryProjection 을 사용하면 해당 DTO가 Querydsl을 의존
	* 의존이 싫으면, 해당 에노테이션을 제거, `Projection.bean()`, `fields()`, `constructor()` 을 사용
* Where 절 파라미터 사용
	* 파라미터 방식을 사용하면 조건 재사용
* 정리
	* `builder`를 사용하여 쿼리의 `조건`을 하나의 메서드에 작성
	* `조건 별 메서드`를 각각 작성하여 재사용성을 높임
	* **조건이 모두 없게 조회되는 경우 동적쿼리에서 전체 데이터를 조회하는 문제가 생길 수도 있으니 `최소한의 필수 조건`\(페이징 또는 limit\)을 넣는 것이 좋음**

> 조회 API 컨트롤러 개발

* API 호출 시 데이터 테스트를 위해 일반 폴더와 테스트 폴더의 프로파일 설정 분리
* 샘플 데이터 추가를
  위한 [InitMember](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/main/java/kr/seok/querydsl/InitMember.java)
  클래스 작성
* Postman을 사용하여 Controller를 REST API 로 호출 테스트

### 실무 활용 - 스프링 데이터 JPA와 Querydsl

> 스프링 데이터 JPA 리포지토리로 변경

* 스프링 데이터 JPA에서 제공하는 정적 쿼리를 사용할 수 있으나 Querydsl에서 제공하는 동적쿼리를 사용할 순 없다.

> [사용자 정의 리포지토리](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl20사용자정의리포지토리Test.java)

* 스프링 데이터 JPA에서 제공하는 것 이상의 쿼리를 사용하기 위해 Querydsl 라이브러리를 추가
* 해당 라이브러리를 추가하여 동적 쿼리를 작성하고 사용하기 위해서는 사용자 정의 인터페이스를 작성하여 상속 받아야 한다.
* 사용자 정의 리포지토리 사용법
	* 순서1: 사용자 정의 인터페이스 작성
	* 순서2: 사용자 정의 인터페이스 구현
	* 순서3: 스프링 데이터 리포지토리에 사용자 정의 인터페이스 상속
* 정리
	* 사용자 정의 인터페이스 및 구현체를 작성하는 경우
		* 스프링 데이터 JPA가 `Impl` 이름을 가진 클래스를 스캔하기 때문에 해당 규칙을 지키도록 한다.
	* 복잡하고 특화된 조회쿼리인 경우 별도의 클래스를 작성하여 유연한 아키텍처를 갖도록 한다.

> 스프링 데이터 페이징 활용

* [Querydsl 페이징 연동](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl21스프링데이터페이징Test.java)
	* 스프링 데이터의 `Page`, `Pageable`을 활용
	* 방법1: `전체 카운트 및 데이터를 한번에 조회`하는 단순한 방법
		* `fetchResults()`내용과 전체 카운트를 한번에 조회
		* 카운트 쿼리 실행시 필요없는 order by 는 제거
	* 방법2: `데이터 내용`과 `전체 카운트`를 별도로 조회하는 방법
		* 전체 카운트를 조회 하는 방법을 최적화 할 수 있을 때 분리
* [CountQuery `최적화`](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl22스프링데이터페이징최적화Test.java)
	* 스프링 데이터 라이브러리에서 제공하는 `PageableExecutionUtils.getPage()`를 활용
	* count 쿼리가 생략 가능한 경우 생략해서 처리
		* 페이지 시작이면서 컨텐츠 사이즈가 페이지 사이즈보다 작을 때
		* 마지막 페이지 일 때 \(offset + 컨텐츠 사이즈를 더해서 전체 사이즈 구함\)
* [정렬\(Sort\)](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl23스프링데이터최적화정렬Test.java)
	* 스프링 데이터 JPA는 자신의 `Sort`를 Querydsl의 `OrderSpecifier`로 편리하게 변경하는 기능을 제공
	* 정렬\( Sort \)은 조건이 조금만 복잡해져도 Pageable 의 Sort 기능을 사용하기 어렵다.
	* 루트 `엔티티 범위를 넘어가는 동적 정렬 기능`이 필요하면 스프링 데이터 페이징이 제공하는 Sort 를 사용하기 보다는 `파라미터를 받아서 직접 처리하는 것을 권장`
* 뒤에서 정렬에 대해서 다시 정리 해줄거임
	* 일단 만들어서 테스트

	  \`\`\`http request

	  **team id 순으로 정렬 뒤 member id 순으로 정렬 테스트**

	  [http://localhost:8080/v5/members?page=0&size=105&sort=team.id,desc&sort=id,desc](http://localhost:8080/v5/members?page=0&size=105&sort=team.id,desc&sort=id,desc)

	  \`\`\`

### 스프링 데이터 JPA가 제공하는 Querydsl 기능

* 여기서부터 설명하는 내용은 부족함이 많은 기능들.. 결국 커스텀 필요

> 인터페이스 지원

* QuerydslPredicateExecutor
	* 문제점
		* `조인X` \(묵시적 조인은 가능하지만 left join이 불가능하다.\)
		* 클라이언트, 서비스 클래스에서 Querydsl의 의존성을 갖게 된다.
		* 복잡한 실무환경에서 사용하기에는 한계가 명확하다.
		* 탈락 !
	* 장점
		* QuerydslPredicateExecutor 는 `Pagable`, `Sort`를 모두 지원하고 정상 동작

> [Querydsl Web 지원](https://docs.spring.io/spring-data/jpa/docs/2.2.3.RELEASE/reference/html/#core.web.type-safe)

* 문제점
	* 단순한 조건만 가능
	* 조건을 커스텀하는 기능이 복잡하고 명시적이지 않음
	* 컨트롤러가 Querydsl에 의존
	* 복잡한 실무환경에서 사용하기에는 한계가 명확
	* 탈락 !!

> 리포지토리 지원

* QuerydslRepositorySupport
	* 장점
		* `getQuerydsl().applyPagination()` 스프링 데이터가 제공하는 페이징을 Querydsl로 편리하게 변환 가능

		  \(단! Sort는 오류발생\)

		* `from()` 으로 시작 가능\(최근에는 QueryFactory를 사용해서 `select()` 로 시작하는 것이 더 명시적\)
		* EntityManager 제공
	* 치명적인 문제점
		* Querydsl 3.x 버전을 대상으로 만듬
		* Querydsl 4.x에 나온 JPAQueryFactory로 시작할 수 없음
			* select로 시작할 수 없음 \(from으로 시작해야함\)
		* QueryFactory 를 제공하지 않음
		* 스프링 데이터 `Sort 기능`이 `정상 동작하지 않음`

> [Querydsl 지원 클래스 직접 만들기](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl/src/test/java/kr/seok/querydsl/domain/Querydsl25사용자정의SupportTest.java)

* QuerydslRepositorySupport의 한계점을 지원하는 클래스 작성
	* 스프링 데이터가 제공하는 페이징을 편리하게 변환
	* 페이징과 카운트 쿼리 분리 가능
	* 스프링 데이터 Sort 지원
	* select\(\), selectFrom\(\) 으로 시작 가능
	* EntityManager, QueryFactory 제공
