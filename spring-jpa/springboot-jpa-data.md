# 3. Spring-Data-Jpa

## 프로젝트 환경설정

> H2 데이터베이스 설치

* 라이브러리에 맞는 버전을 설치
* db 파일로 접근
  * url: jdbc:h2:~/datajpa
  * 이 방법은 직접적으로 파일에 접근했기 떄문에 lock이 걸려 한 번에 여러 곳에서 접근할 수 없음
* 원격으로 접근
  * url: jdbc:h2:tcp://localhost/~/datajpa

> 스프링 데이터 JPA와 DB 설정, 동작확인

* application.yml 설정
* 기본 엔티티 생성 및 테스트 코드 작성
* jpa vs spring-data-jpa 맛보기 코드비교

## 예제 도메인 모델

* Member, Team 생성 및 연관관계 설정
* 생성자 레벨 Protected로 설정
* ToString은 연관관계가 설정되어 있지 않은 필드만 출력

## 공통 인터페이스 기능

> 순수 JPA 기반 리포지토리 만들기

* 기본 CRUD
  * 저장
  * 변경 &gt; 변경감지 사용
  * 삭제
  * 전체 조회
  * 단건 조회
  * 카운트
* 참고
  * JPA에서 수정은 변경감지 기능을 사용
  * 트랜잭션 안에서 엔티티를 조회한 다음에 데이터를 변경하면, 트랜잭션 종료 시점에 변경감지 기능이 작동해서 변경된 엔티티를 감지하고 UPDATE SQL을 실행
* 확인사항
  * JPA 코드로 기본 CRUD 작성 및 테스트
  * 지연로딩을 이용한 수정 테스트

> 스프링 데이터 JPA 공통 인터페이스 설정

* 스프링부트 사용시 `@SpringBootApplication` 위치를 지정\(해당패키지와 하위 패키지 인식\)
* 만약 위치가 달라지면 `@EnableJpaRepositories` 필요
* `org.springframework.data.repository.Repository` 를 구현한 클래스는 스캔 대상
  * `MemberRepository` 인터페이스가 동작한 이유
  * 실제 출력해보기\(Proxy\)
  * `memberRepository.getClass()` class `com.sun.proxy.$ProxyXXX`
* `@Repository` 애노테이션 생략 가능
  * 컴포넌트 스캔을 스프링 데이터 JPA가 자동으로 처리
  * JPA 예외를 스프링 예외로 변환하는 과정도 자동으로 처리

> 스프링 데이터 JPA 공통 인터페이스 적용

* 위 JPA 코드로 작성한 내용을 Spring-data-jpa에서 제공하는 `JpaRepository`를 사용하여 구현
* `JpaRepository<T, ID>`
  * `T`: 엔티티 타입
  * `ID`: 식별자 타입\(PK\)

> 스프링 데이터 JPA 공통 인터페이스 분석

* JpaRepository
  * data.jpa 패키지 하위에 존재하는 인터페이스
  * 대부분의 공통 메서드를 제공
* PagingAndSortingRepository
  * data 패키지 하위에 존재하는 인터페이스
* 수정 사항 확인
  * `T findOne(ID)` -&gt; `Optional<T> findById(ID)` 변경
* 제네릭 타입 확인
  * `T`: 엔티티
  * `ID`: 엔티티의 식별자 타입
  * `S`: 엔티티와 그 자식 타입
* 주요 메서드

  | 메서드명 | 설명 |
  | :--- | :--- |
  | `save(S)` | 새로운 엔티티는 저장하고 이미 있는 엔티티는 병합한다. |
  | `delete(T)` | 엔티티 하나를 삭제한다. 내부에서 `EntityManager.remove()` 호출 |
  | `findById(ID)` | 엔티티 하나를 조회한다. 내부에서 `EntityManager.find()` 호출 |
  | `getOne(ID)` | 엔티티를 프록시로 조회한다. 내부에서 `EntityManager.getReference()` 호출 |
  | `findAll(...)` | 모든 엔티티를 조회한다. 정렬\(`Sort`\)이나 페이징\(`Pageable`\) 조건을 파라미터로 제공할수 있다. |

## 쿼리 메서드 기능

* 여러 기능
  * 메소드 이름으로 쿼리 생성
  * NamedQuery
  * @Query - 리파지토리 메소드에 쿼리 정의 파라미터 바인딩
  * 반환 타입
  * 페이징과 정렬
  * 벌크성 수정 쿼리
  * @EntityGraph
* 쿼리 메소드 기능 3가지
  * 메소드 이름으로 쿼리 생성
  * 메소드 이름으로 JPA NamedQuery 호출
  * @Query 어노테이션을 사용해서 리파지토리 인터페이스에 쿼리 직접 정의

> 메소드 이름으로 쿼리 생성

* 메서드 이름을 분석하여 JPQL 쿼리를 생성하고 실행
* [쿼리 메소드 필터 조건](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)
* [스프링 데이터 JPA가 제공하는 쿼리 메소드 기능](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details)
  * [조회](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation)
  * [limit](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.limit-query-result)
* 주의
  * 엔티티의 필드명이 변경되면 인터페이스에 정의한 메서드 이름도 꼭 함께 변경해야 한다.
  * 그렇지 않으면 애플리케이션을 시작하는 시점에 오류가 발생한다.
  * 애플리케이션 로딩 시점에 오류를 인지할 수 있는 것이 스프링 데이터 JPA의 매우 큰 장점이다.

> JPA NamedQuery

* `@NamedQuery` 어노테이션으로 Named 쿼리 정의
* JPA기반 쿼리 테스트
* Spring Data JPA 기반 쿼리 테스트
  * @Query를 생략하고 메서드 이름만으로 Named쿼리를 호출할 수 있다.
* 스프링 데이터 JPA는 선언한 `도메인 클래스 + .(점) + 메서드 이름`으로 Named 쿼리를 찾아서 실행
* 만약 실행할 Named 쿼리가 없으면 메서드 이름으로 쿼리 생성 전략을 사용한다.
* 필요하면 전략을 변경할 수 있지만 권장하지 않는다.
  * [참고](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-lookup-strategies)
* 주의
  * 스프링 데이터 JPA를 사용하면 실무에서 `NamedQuery`를 직접 등록해서 사용하는 일은 드물다.
  * 대신 `@Query`를 사용해서 리파지토리 메소드에 쿼리를 직접 정의한다.

> @Query, 리포지토리 메소드에 쿼리 정의하기

* @org.springframework.data.jpa.repository.Query 어노테이션을 사용
* 실행할 메서드에 정적 쿼리를 직접 작성하므로 이름 없는 Named 쿼리라 할 수 있음
* JPA Named 쿼리처럼 애플리케이션 실행 시점에 문법 오류를 발견할 수 있음\(매우 큰 장점!\)
* 실무 환경
  * 메서드 이름으로 쿼리 생성 기능은 파라미터가 증가하면 메서드 이름이 지저분해진다.
  * 따라서 @Query 기능을 더 선호한다.
* 단순 값 하나 조회
  * JPA 값 타입 `@Embedded`도 이 방식으로 조회가 가능
* DTO로 직접 조회
  * DTO로 직접 조회 하려면 JPA의 new 명령어를 사용
  * 생성자가 맞는 DTO가 필요
* 파라미터 바인딩
  * 코드 가독성과 유지보수를 위해 이름 기반 파라미터 바인딩을 사용
* 컬렉션 파라미터 바인딩
  * Collection 타입으로 in절 지원

> [반환타입](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-return-types)

* 조회 결과 값에 따른 방식
  * 컬렉션
    * 결과 없음 -&gt; 빈 컬렉션
  * 단건 조회
    * 결과 없음 -&gt; null
    * 결과 2건 이상: `javax.persistence.NonUniqueResultException` 예외 발생
* 참고
  * `단건`으로 지정한 메서드를 호출하면 스프링 데이터 JPA는 내부에서 JPQL의 `Query.getSingleResult()` 메서드를 호출한다.
  * 이 메서드를 호출했을 때 조회 결과가 없으면 `javax.persistence.NoResultException` 예외가 발생하는데 개발자 입장에서 다루기가 상당히 불편하다.
  * 스프링 데이터 JPA는 단건을 조회할 때 이 예외가 발생하면 예외를 무시하고 대신에 `null` 을 반환한다.

> 순수 JPA 페이징과 정렬

* JPA 페이징
  * 페이징에 대한 개념을 가지고 설계 해야하는 부담이 있다.
* 스프링 데이터 JPA
  * `Page, Slice`와 같은 인터페이스를 제공
    * 페이징과 정렬 파라미터
      * `Sort`: 정렬기능
      * `Pageable`: 페이징 기능 \(내부에 Sort 포함\)
    * 특별한 반환 타입
      * `Page`: 추가 count 쿼리 결과를 포함하는 페이징
      * `Slice`: 추가 count 쿼리 없이 다음 페이지만 확인 가능\(내부적으로 `limit + 1` 조회\)
      * `List`: 추가 count쿼리 없이 결과만 반환
  * `PageRequest` 클래스를 활용하여 조건을 만들어 `Repository`로 전달하여 사용
    * Jpa가 두 번째 파라미터로 `Pageable` 인터페이스를 제공
    * `Page`는 0부터 시작하기때문에 주의
  * 페이징에 성능상 문제가 되는경우 `@Query(value = "{...}", countQuery = "{...}")`를 사용하여 분리가 가능하다.
    * 페이징에는 Join이 필요없음에도 기본 쿼리가 Join을 하여 페이징에 부하가 있는 경우
    * 복잡한 sql에서 사용, 데이터는 join으로 가져와야하나, count는 join이 필요없는 경우
  * 페이징에서도 Entity는 API 로 바로 노출하면 문제가 발생하기 때문에 DTO로 변환하는 작업은 필수
* 정리
  * 사용자의 요청 쿼리를 PageRequest로 만들어 JPA로 전달
  * 기본은 Page를 사용, 더보기와 같은 버튼이 있는 페이지의 경우 Slice를 사용하도록한다.
  * Page를 사용하던 Slice를 사용하던 dto로 변환하는 것을 잊지 말 것
  * JPA의 페이징 인터페이스를 사용하여 domain 개발에 집중

> 벌크성 수정 쿼리

* Jpa 기반 bulk 수정 쿼리
  * `executeUpdate()`으로 호출
  * 영속성 컨텍스트를 초기화 `EntityManager.clear()`
* 스프링 데이터 JPA 기반 bulk 수정 쿼리
  * `bulk`성 쿼리 설정
    * `@Modifying`을 사용하여 bulk 성 수정 및 삭제 쿼리를 실행하게 하는 어노테이션
      * 해당 어노테이션이 설정되어야 `executeUpdate()` 메서드로 실행이 됨
      * 해당 어노테이션이 없으면 예외를 발생시킴
    * `@Modifying(clearAutomatically = true)` 벌크성 쿼리를 실행한 뒤 영속성 컨텍스트 초기화를 하도록 한다.
  * `bulk`성 쿼리의 주의사항
    * `bulk` 연산은 영속성 컨텍스트를 무시하고 DB에 직접 실행하기 때문에, **영속성 컨텍스트에 있는 엔티티의 상태와 DB 엔티티 상태가 달라질 수 있다.**
    * `@Modifying`를 사용하지 않는 경우 `QueryExecutionRequestException` 예외 발생
    * 또한 영속성 컨텍스트를 초기화하지 않는 경우 해당 데이터를 다시 조회하면 영속성 컨텍스트에 값이 남아 있어 문제가 될 수 있다.
    * 조회가 필요한 경우 영속성 컨텍스트를 필히 초기화 해야한다.
  * `bulk` 연산 권장 방법
    * 영속성 컨텍스트에 엔티티가 없는 상태에서 `bulk` 연산을 먼저 실행
    * 영속성 컨텍스트에 엔티티가 있는 경우 `bulk` 연산 직후 영속성 컨텍스트를 초기화

> @EntityGraph \(`Fetch Join`의 간소화 버전\)

* 연관된 엔티티들을 SQL 한 번에 조회하는 방법
  * Fetch Join과 같은 방식?
* 기존 방법
  * Hibernate5Module에 기반하여 Proxy객체를 초기화하여 값을 세팅
  * 1:N 쿼리 조회 시 1을 먼저 조회 후 N에 대해서 지연로딩을 수행
* 가장 기본적인 방식 hibernate 모듈을 통한 지연로딩 대처 방법 \(강의에 없는 내용\)
  * 지연로딩 여부를 확인
    * hibernate 메서드 사용
      * `Hibernate.isInitialized(member.getTeam())`
    * JPA 표준으로 확인하는 방법
      * `em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member.getTeam())`
  * 결국 엔티티를 한번에 조회하기 위해서는 `Fetch Join`이 필요함
* `Fetch Join`으로 지연로딩에 대처하는방법
  * `@Query("select ... from ... join fetch ...")`
  * 연관된 엔티티를 한 번에 조회할 수 있도록 `Fetch Join`을 사용
  * 하지만 이는 outer join으로 조회되기 때문에 중복 데이터가 존재
* `@EntityGraph`를 사용하여 지연로딩에 대처하는 방법
  * `Fetch Join`의 `Annotation` 버전
  * left outer join 실행
* `@NamedEntityGraph` 설정을 통한 지연로딩 대처방법
  * Entity 설정
    * `@NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team"))`
  * Repository 설정
    * `@EntityGraph("Member.all")`
* 정리
  * 간단한 fetch join이 필요한 부분에서는 `@EntityGraph`
  * 조금 복잡해지면 JPQL `fetch join`을 활용

> JPA Hint & Lock \(사용하려는 경우 깊은 이해가 필요\)

* database의 hint가 아님
* 데이터를 오직 조회하는 기능에 dirty check 기능을 비활성화 하도록 하는 설정
* 기본적으로 데이터를 저장하고 영속성 컨텍스트를 비운 뒤, 데이터를 새로 조회했을 때 db의 데이터를 조회 해 1차 캐시에 데이터를 저장하고 그 상태를 감지하게 되는데

  단순히 조회만 하는 기능에서는 해당 작업이 비효율적이게 된다. 해당 기능을 통해 dirty checking을 하지 않도록하여 효율적으로 관리 하는 방법

* 반환 타입을 Page 인터페이스로 사용하는 경우에도 페이징을 위한 count 쿼리에도 힌트 적용 가능

## 확장 기능

> 사용자 정의 인터페이스 \(실무에서 많이 씀\)

* 기본 프로세스
  * 스프링 데이터 JPA 리포지토리는 인터페이스만 정의하고 구현체는 스프링이 자동 생성
* Custom 인터페이스 구현 선택 사항
  * JPA 직접 사용\( EntityManager \)
  * 스프링 JDBC Template 사용
  * MyBatis 사용
  * 데이터베이스 커넥션 직접 사용 등등...
  * Querydsl 사용
* 실무에서는 주로 QueryDSL이나 SpringJdbcTemplate을 함께 사용할 때 사용자 정의 리포지토리 기능을 사용한다.
* [MemberQueryRepository](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-data/src/main/java/kr/seok/data/repository/datajpa/MemberQueryRepository.java)

  를 인터페이스가 아닌 클래스로 만들고 스프링 빈으로 등록하여 직접 사용해도 된다. \(이때 스프링 데이터 JPA와는 관계 없이 별도로 동작한다.\)

* 스프링 데이터 2.x 버전 사용방식
  * 사용자 정의 구현 클래스에 `리포지토리 인터페이스 이름 + Impl` 을 적용하는 대신 `사용자 정의 인터페이스 명 + Impl` 방식도 지원
  * `MemberRepository`Impl -&gt; `MemberRepositoryCustom`Impl으로도 적용 가능

    ```java
    /* 이전 방식 */
    class MemberRepositoryImpl implements MemberRepositoryCustom {
    // ...
    }
    /* spring data 2.x 이상 방식 */
    class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    // ...
    }
    ```
* 정리
  * Custom으로 추가되는 기능들이 핵심 비즈니스인지 화면에 출력되는 내용인지 확실히 분류하여 클래스별로 설계할 수 있는 능력이 필요

> Auditing

* 엔티티 생성, 변경의 작성자 및 시간을 관리하는 방식
  * JPA 기반 관리 방법
    * `@MappedSuperclass`
    * `@PrePersist`, `@PostPersist`
    * `@PreUpdate`, `@PostUpdate`
  * 스프링 데이터 JPA 기반 관리 방법
    * `@EnableJpaAuditing` [스프링 부트 설정 클래스](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-data/src/test/java/kr/seok/JpaApplicationTest.java)에

      적용

    * `@EntityListeners(AuditingEntityListener.class)` [엔티티](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-data/src/main/java/kr/seok/data/domain/base/JpaBaseEntity.java)에

      적용

    * `@CreatedDate` 엔티티 최초 등록일
    * `@LastModifiedDate` 엔티티 최종 수정일
    * `@CreatedBy` 엔티티 최초 등록자
    * `@LastModifiedBy` 엔티티 최종 수정자
* 실무에서 사용하는 방법
  * 기본적으로 Entity의 이력 정보를 확인하기 위해서는 무조건 필요한 필드
  * 다만 등록일, 수정일은 어떤 엔티티든 필수인데 등록자, 수정자의 경우 필요하지 않은 경우가 존재하기 때문에 분리하여 사용
  * [`BaseEntity`](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-data/src/main/java/kr/seok/data/domain/base/BaseEntity.java)

    , [`BaseTimeEntity`](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-data/src/main/java/kr/seok/data/domain/base/BaseTimeEntity.java)

    클래스로 구분하여 필요한 엔티티에서 사용

  * JPA의 이벤트를 스프링 데이터가 제공해주기 때문에 도메인 코드를 줄일 수 있다.
* `@EntityListeners(AuditingEntityListener.class)`를 설정하지 않고 글로벌로 설정하는 방법
  * META-INF/orm.xml

    ```markup
    <?xml version=“1.0” encoding="UTF-8”?>
      <entity-mappings xmlns=“http://xmlns.jcp.org/xml/ns/persistence/orm”
                       xmlns:xsi=“http://www.w3.org/2001/XMLSchema-instance”
                       xsi:schemaLocation=“http://xmlns.jcp.org/xml/ns/persistence/
      orm http://xmlns.jcp.org/xml/ns/persistence/orm_2_2.xsd”
                       version=“2.2">
          <persistence-unit-metadata>
              <persistence-unit-defaults>
                  <entity-listeners>
                      <entity-listener
      class="org.springframework.data.jpa.domain.support.AuditingEntityListener”/>
                  </entity-listeners>
              </persistence-unit-defaults>
          </persistence-unit-metadata>
      </entity-mappings>
    ```

> Web 확장 - 도메인 클래스 컨버터 \(잘 안씀\)

* HTTP 파라미터로 넘어온 엔티티의 아이디로 엔티티 객체를 찾아서 바인딩
* Http 기본 동작
  * view에서 controller로 넘어온 파라미터 값을 통해 repository에 파라미터 값을 넘겨 데이터를 조회
* 도메인 클래스 컨버트를 사용하는 경우 동작
  * 파라미터로 엔티티를 받되 PathVariable로 설정된 파라미터를 해당 엔티티에서 가져와 엔티티의 Repository를 통해 데이터를 조회
  * Repository 로직을 skip할 수 있으나 트랜잭션이 없는 범위에서 엔티티를 조회 하므로 엔티티를 변경했을 때 DB에 반영이 되지 않는다.
* 두 방식의 차이점
  * HTTP 요청은 회원 id를 받지만 도메인 클래스 컨버터가 중간에 동작해서 회원 엔티티 객체를 반환
  * 도메인 클래스 컨버터도 리파지토리를 사용해서 엔티티를 찾음
* 주의사항
  * 도메인 클래스 컨버터로 엔티티를 파라미터로 받으면, 이 엔티티는 단순 조회용으로만 사용해야 한다.
  * \(트랜잭션이 없는 범위에서 엔티티를 조회했으므로, 엔티티를 변경해도 DB에 반영되지 않는다.\)

> Web 확장 - 페이징과 정렬

* 페이징관련 인터페이스 & 클래스
  * `Pageable` 인터페이스
  * `PageRequest` 실제 사용 객체
* 페이징 사용법 \`\`\`http request /members?page=0&size=3&sort=id,desc&sort=username,desc

  \`\`\`

  * page: 현재 페이지, `0`부터 시작한다.
  * size: 한 페이지에 노출할 데이터 건수
  * sort: 정렬 조건을 정의한다.
    * 예\) 정렬 속성, 정렬 속성...\(ASC \| DESC\), 정렬 방향을 변경하고 싶으면 sort 파라미터 추가 \( asc 생략 가능\)

* 페이징 설정
  * 글로벌 설정

    ```text
    spring.data.web.pageable.default-page-size=20 /# 기본 페이지 사이즈/ 
    spring.data.web.pageable.max-page-size=2000 /# 최대 페이지 사이즈/
    ```

  * 개별 설정
    * `@PageableDefault` 어노테이션을 사용

      ```java
        @PageableDefault(size = 12, sort = “username”, direction = Sort.Direction.DESC) Pageable pageable
      ```
  * 페이징 정보가 둘 이상인 경우 설정 방법
    * 페이징 정보가 둘 이상이면 접두사로 구분
    * `@Qualifier` 에 접두사명 추가 "{접두사명}\_xxx”
    * 요청 쿼리스트링

      \`\`\`http request

      /members?member\_page=0&order\_page=1

      \`\`\`

    * 컨트롤러 파라미터 설정

      ```java
      class MemberController {
          public String list(
                  @Qualifier("member") Pageable memberPageable,
                  @Qualifier("order") Pageable orderPageable, ...) {
                  // ...
          }
      }
      ```
  * Paging 커스텀
    * Page의 기본 값이 0인 것을 1부터 시작하는 방법
      * `Pageable`, `Page`를 파리미터와 응답 값으로 사용히자 않고, 직접 클래스를 만들어서 처리
      * spring.data.web.pageable.one-indexed-parameters 를 true 로 설정
        * 이 방법은 web에서 page 파라미터를 -1 처리 할 뿐, 응답 값 `Page`에는 0으로 되어 있음
        * 반환하는 pageable 객체의 응답값이 의도한 값으로 반환되지 않음
* 페이징 결과값 반환
  * 엔티티를 API로 노출하면 다양한 문제가 발생
  * 엔티티를 꼭 DTO로 변환해서 반환해야 한다.
  * Page는 map\(\) 을 지원해서 내부 데이터를 다른 것으로 변경할 수 있다.

## 스프링 데이터 JPA 분석

* 기본 개념
  * `@Repository`
    * JPA 예외를 스프링이 추상화한 예외로 변환
  * `@Transactional`
    * JPA의 `모든 변경`은 `트랜잭션 안`에서 동작
    * 스프링 데이터 JPA는 `변경(등록, 수정, 삭제)` 메서드를 `트랜잭션 처리`
    * 서비스 계층에서 트랜잭션을 시작하지 않으면 리파지토리에서 트랜잭션 시작 -&gt; 기본적으로 `Repository`에 `@Transactional`이 걸려있음
    * 서비스 계층에서 트랜잭션을 시작하면 리파지토리는 해당 트랜잭션을 전파 받아서 사용
    * 그래서 스프링 데이터 JPA를 사용할 때 트랜잭션이 없어도 데이터 등록, 변경이 가능했음

      \(사실은 트랜 잭션이 리포지토리 계층에 걸려있는 것임\)
  * `@Transactional(readOnly = true)`
    * 데이터를 단순히 조회만하고 변경하지 않는 트랜잭션에서 readOnly = true 옵션을 사용하면 플러 시를 생략해서 약간의 성능 향상을 얻을 수 있음
* **JPA를 사용하여 데이터를 저장할 때 주의할 점**
  * persist를 사용해야함
  * merge는 db에 데이터가 존재하는 지 select를 한번 조회하기 때문에 비효율적임
* 임의의 Id를 사용하려는 경우
  * 기본적으로 `식별자(@Id)`가 `객체`인경우 `null`로 판단, `기본 타입`인 경우 `0`으로 판단
  * 임의로 식별자를 채번하여 임의로 생성해야 하는 경우 -&gt; `Persistable` 인터페이스를 사용하여 엔티티가 신규인지 여부를 판단
  * 실무에서는 사용하는 방법
    * 생성일자\(createDate\)가 존재하는 경우를 기준으로 신규 데이터인지 여부를 판단
* **`Persistable`**
  * JPA 식별자 생성 전략이 @GenerateValue 면 save\(\) 호출 시점에 식별자가 없으므로 새로운 엔티 티로 인식해서 정상 동작
  * JPA 식별자 생성 전략이 @Id 만 사용해서 직접 할당이면 이미 식별자 값이 있는 상태로 save\(\) 를 호출
  * 이 경우 `merge()` 가 호출된다. `merge()`는 우선 DB를 호 출해서 값을 확인하고, DB에 값이 없으면 새로운 엔티티로 인지하므로 매우 비효율적임
  * `Persistable` 를 사용해서 새로운 엔티티 확인 여부를 직접 구현하게는 효과적이다.
  * 참고
    * 등록시간\(`@CreatedDate`\)을 조합해서 사용하면 이 필드로 새로운 엔티티 여부를 편리하게 확인할 수 있다.

      \(`@CreatedDate`에 값이 없으면 새로운 엔티티로 판단\)

## 나머지 기능들

> Specifications \(명세\) - 유지보수가 힘든 코드

* 실무에서는 JPA Criteria를 거의 안쓴다! 대신에 QueryDSL을 사용하자.
* 스프링 데이터 JPA는 JPA Criteria를 활용하여 해당 개념을 사용할 수 있도록 `Specification`클래스를 지원
* 동적 쿼리를 대처하기 위한 방법으로 사용
* 술어\(predicate\)
  * 참 또는 거짓으로 평가
  * AND OR 같은 연산자로 조합해서 다양한 검색조건을 쉽게 생성\(컴포지트 패턴\)
  * 예\) 검색 조건 하나하나
  * 스프링 데이터 JPA는 `org.springframework.data.jpa.domain.Specification` 클래스로 정의
* 명시 기능 사용방법
  * `Repository`에 `JpaSpecificationExecutor<Member>` 상속
  * 조회 쿼리 메서드에 `Specification` 파라미터로 받아 검색 조건으로 사용
  * 검색 조건에 대한 Spec을 미리 정의 해두어야 함

> [QueryBy](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example)

* 사용 방식
  * Probe: 필드에 데이터가 있는 실제 도메인 객체
  * ExampleMatcher: 특정 필드를 일치시키는 상세한 정보 제공, 재사용 가능
  * Example: `Probe`와 `ExampleMatcher`로 구성, 쿼리를 생성하는데 사용
* 장점
  * 동적 쿼리를 편하게 처리하는 방법
  * 도메인 객체를 그대로 사용하는 것이 큰 장점
  * 데이터 저장소를 RDB에서 NOSQL로 변경해도 코드 변경이 없게 추상화 되어 있음 \(spring data 패키지 내에 존재하기 때문\)
  * 스프링 데이터 JPA `JpaRepository` 인터페이스에 이미 포함
* 단점
  * 조인은 가능하나 내부 조인만 가능 \( outer join에서 제약 \)
  * 중첩 제약조건 쿼리에 제한
  * 매칭 조건이 단순한 것만 가능 \(정확하게 매칭되는 것만 가능\)
* 정리
  * 좋은 기술처럼 보이지만 쿼리의 중요한 기능 들을 제공하지 않아 사용을 해야하는 선택을 해야할지에 대해서 생각해야 한다.

> [Projections](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections)

* 복잡한 쿼리를 해결하기에는 한계가 있다. 실무에서는 단순할 때만 사용하고, 조금만 복잡해지면 QueryDSL을 사용하자
* 사용 용도
  * 엔티티 대신에 DTO를 편리하게 조회할 때 사용
  * 전체 엔티티가 아니라 만약 회원 이름만 딱 조회하고 싶은경우
* 주의사항
  * 프로젝션 대상이 root 엔티티인 경우, JPQL SELECT 절 최적화가 가능하다.
  * 프로젝션 대상이 root가 아닌경우
    * left outer join 처리하기 때문에 모든 필드를 select 해서 엔티티로 조회한 다음에 계산
    * 최적화가 하기 힘듬
* 정리
  * 프로젝션 대상이 ROOT 엔티티인 경우 유용
  * 프로젝션 대상이 ROOT 엔티티를 넘어가면 JPQL SELECT 최적화기 되지 않는다.
  * 실무의 복잡한 쿼리를 해결하기에 한계가 있다.

> Native Query
>
> * 최후의 수단으로 사용하는 수단중 하나

* Native Query 또는 Projections 이라고는 하나 QueryDSL로 못할 수가 없다.

> * 한번에 못가져올거면 차라리 두 세번에 걸쳐서 가져올것..

* 스프링 데이터 JPA Native Query
  * 페이징 지원
  * 반환 타입
    * Object\[\]
    * Tuple
    * DTO \(스프링 데이터 인터페이스 Projections 지원\)
  * 제약 사항
    * sort 파라미터를 통한 정렬이 정상 동작하지 않을 수도 있다.
    * JPQL처럼 애플리케이션 로딩 시점에 문법 확인 불가
    * 동적 쿼리 불가
* 위치기반 파라미터
  * JPQL의 경우 1부터 시작, Native Query의 경우 0부터 시작
* DTO 변환 시
  * DTO 대신 JPA TUPLE 조회
  * DTO 대신 MAP 조회
  * @SqlResultSetMapping 복잡
  * Hibernate ResultTransformer를 사용해야함 복잡
  * [프로젝션으로 DTO 쿼리 만드는 방법](https://vladmihalcea.com/the-best-way-to-map-a-projection-query-to-a-dto-with-jpa-and-hibernate/)
  * 네이티브 SQL을 DTO로 조회할 때는 `JdbcTemplate` or `MyBatis` 권장

## 참고

* [리포지토리 분리](https://www.inflearn.com/questions/101103)

