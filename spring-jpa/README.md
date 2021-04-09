# Spring JPA

## [데이터 모델링 및 연관관계 설정](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-shop/README.md)

* 기능 목록
  * 회원 기능
    * [x] 회원 등록
    * [x] 회원 조회
  * 상품 기능
    * [x] 상품 등록
    * [x] 상품 수정 \(영속성 관리 개념\)
    * [x] 상품 조회
  * 주문 기능
    * [x] 상품 주문
    * [x] 주문 내역 조회
    * [x] 주문 취소
  * 기타 요구사항
    * [x] 상품은 재고 관리가 필요하다.
    * [x] 상품의 종류는 도서, 음반, 영화가 있다. 상품을 카테고리로 구분할 수 있다.
    * [x] 상품 주문시 배송 정보를 입력할 수 있다.

## [최적화 내용](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-shop-optimize/README.md)

* 최적화 목록
  * [x] 회원 등록
    * `Entity` 사용하여 발생하는 문제를 확인
  * [x] 회원 등록
    * API 응답 값으로 `Entity`를 노출하지 않도록 로직을 구분
    * DTO 사용하여 Presentation 계층과 Repository 계층을 구분
  * [x] 회원 수정
    * `RequestBody`용 `Request DTO`를 작성
    * `Response`값으로 어떤 값을 넘겨 줄지는 사이트 마다 다름
  * [x] 회원 조회
    * `RequestBody`에 `Entity`를 직접 사용하여 웹 계층에 모든 값이 노출되는 문제 확인
    * 연관 관계가 걸려있는 부분이 서로 조회하여 무한루프에 빠지는 문제 확인
  * [x] 회원 조회
    * `Response`를 위한 `Wrapper Class`를 작성하여 API 스펙을 정의하는 방법 확인
  * [x] 주문 조회
    * xToOne 관계일 때 지연로딩에 대한 최적화 방법
    * 방법 1
      * `Hibernate5Module`를 사용하여 proxy 객체를 초기화 했을때 객체를 조회
      * 또는 `@JsonIgnore`를 사용하여 연관관계가 있는 엔티티 한 부분에 적용하여 무한루프에 빠지지 않도록 설정
    * 방법 2
      * `Entity` 대신 `DTO`를 작성하여 지연로딩 사용
    * 방법 3
      * `Fetch Join`으로 연관관계를 조회하는 방법, 성능 최적화는 대부분 `Fetch Join`으로 해결 가능 확인
    * 방법 4
      * `JPA`에서 `DTO`로 바로 조회하는 방법
    * 방법 5
      * `JPA`에서 `DTO`를 직접 조회하되 컬렉션 조회를 최적화하기 위해 `xToOne` 연관관계에 `Fetch Join`활용
    * 방법 6
      * `JPA`에서 `DTO`를 직접 조회하되 `SQL` 쿼리 한 번에 전체 조회를 할 수 있도록 처리
* 정리
  * 각 방식마다 장단점이 분명하게 차이가 있기때문에 현재 상황이 어떤지 확인하고 맞춰서 사용할 것

## [Spring-Data-Jpa](springboot-jpa-data.md)

* JPA 기반 & 스프링 데이터 JPA 기반
  * 쿼리 메서드 기능 확인
    * [x] 메서드 이름으로 쿼리 생성
      * 정말 간단한 조회시 사용
    * [x] NamedQuery
      * 잘 안씀
    * [x] @Query
      * JPQL 사용하기 위한 방법
    * [x] Paging
      * `Pageable`, `PageRequest`
    * [x] Bulk
      * `@Modifying(clearAutomatically = true)`
    * [x] `@EntityGraph`
      * 간단한 fetch join 사용시 `@EntityGraph`
      * 복잡한 쿼리 사용 시 JPQL의 `Fetch Join` 사용
  * [x] 커스텀 인터페이스
    * 쿼리의 용도에 따라 인터페이스를 구분 \(코어 비즈니스 쿼리, view 조회 쿼리\)
    * QueryDSL, JdbcTemplate구분
  * [x] Auditing
    * time, by를 구분하여 클래스관리
    * time은 어떤 곳에서든지 필수일 수 있지만 by는 선택사항일 수 있음
  * [x] 도메인 클래스 컨버터 권장하지 않음
  * [x] 페이징
    * `Pageable`로 response의 규격을 고정시킬 수 있다.
    * 사용자 정의 `PageRequest`를 작성하여 page 시작 인덱스를 1로 할 수 있도록 하는 것이 필요할 듯
    * `글로벌` 설정으로 기본 값을 잡고 필요한 곳에서 `@PageableDefault` 사용
    * 페이징도 결국 Entity에서 DTO로 변환하는 것이 필요
* [x] 스프링 데이터 JPA 분석
  * `@Transactional`에 대한 차이 구분하기
    * JPA: 모든 변경이 트랜잭션 안에서 수행
    * 스프링 데이터 JPA: 변경\(등록, 수정 삭제\) 메서드가 트랜잭션안에서 수행
  * `save`의 차이
    * 신규 데이터 `persist()`
    * 기존 데이터가 존재하는 경우 `merge()`
      * `merge()`는 select 이후에 값을 변경하는 기본 프로세스를 인지 할 것
  * Entity를 사용할 때 Id 관리를 어떻게 할 것인가에 따라 `Persistable` 인터페이스 사용
* [x] 그 외 기능
  * `Specifications`: 복잡함
  * `Query By`: outer join에 대해서 제약이 있음
  * `projections`: Native Query의 대체가 될 수 있으나 데이터 조회 최적화에서 신경써야할 부분이 있음
  * `Native Qeury`: 이거 쓸바엔 JdbcTemplate 사용, 아니면 MyBatis
* 결론
  * QueryDSL 공부해라

## [Query DSL](springboot-jpa-querydsl.md)

* 프로젝트 설정
  * [x] QueryDSL 설정 및 검증
    * compileQuerydsl 후 검증용 Q 타입 생성 \(build/generated/querydsl/`패키지명.엔티티클래스.java`\) 확인
  * [x] JPQL과 Querydsl의 차이점
  * [x] Querydsl에서 제공하는 메서드 `검색 조건`, `정렬`, `페이징`, `집합`, `조건문`
  * [x] Querydsl 조인 활용
  * [x] Querydsl 서브쿼리
  * [x] `Projection`과 `@QueryProjection`
  * [x] 동적쿼리
  * [x] Querydsl bulk 연산
  * [x] Querydsl 사용자 정의 레포지토리
  * [x] Querydsl 페이징 최적화
  * [x] Querydsl 실무 활용가능 커스텀 클래스

## [Querydsl Query](https://github.com/SeokRae/spring/tree/047b5512ff2fa980ecb8a103fa5d4035b2b444ae/spring-jpa/springboot-jpa-querydsl-basic/README.md)

## 단축키 꿀팁 모음

* 단축키 shift + command + c
  * 해당 폴더 경로 복사: 터미널에 복사해서 빠르게 이동
* reCompile shift + command + F9
  * Thymeleaf 사용시 recompile로 refresh \(devtools dependency 필요\)

