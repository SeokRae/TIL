# 1. 데이터 모델링 및 연관관계 설정

## 기본 설정

* gradle 의존관계 확인하기

  ```text
  ./gradlew dependencies —configuration compileClasspath
  ```

* spring-boot-starter-web
	* tomcat
	* **spring-webmvc**
* spring-boot-starter-thymeleaf
* spring-boot-starter-data-jpa
	* aop
	* jdbc
		* **HikariCP 커넥션 풀**
	* **hibernate + JPA**
	* **spring-data-jpa**
* spring-boot-starter
	* spring-boot
		* spring-core
	* spring-boot-starter-logging
		* logback, slf4j
* spring-boot-starter-test
	* junit
		* 테스트 프레임워크
	* mockito
		* 목 라이브러리
	* assertj
		* 테스트 코드 지원 라이브러리
	* spring-test
		* 통합 테스트

## View 설정

* thymeleaf 템플릿 엔진
	* [thymeleaf 공식 사이트](https://www.thymeleaf.org/)
	* [스프링 공식 튜토리얼](https://spring.io/guides/gs/serving-web-content/)
	* [스프링부트 메뉴얼](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-template-engines)

## h2 설정

* [h2 홈페이지에서 다운 및 설치](https://www.h2database.com)
* sh 파일 내용 확인 \(자바로 실행됨을 확인\)
* 파일 모드로 실행
* 주의: H2 데이터베이스의 MVCC 옵션은 H2 1.4.198 버전부터 제거되었습니다.

## JPA

* `스프링부트를 통해 복잡한 설정이 모두 자동화`
* 스프링 프레임워크에서 사용하는 `persistence.xml`도 없고, `LocalContainerEntityManagerFactoryBean`도 없다.
* 스프링 부트를 통한 추가 설정은 스프링 부트 메뉴얼을 참고하고, 스프링 부트를 사용하지 않고 순수 스프링과 JPA 설정 방법은 자바 ORM 표준 JPA 프 로그래밍 책을 참고하자.

> 로그 확인 시 쿼리의 파라미터 값을 출력해주는 라이브러리 [파라미터를 출력 라이브러리](https://github.com/gavlyukovskiy/spring-boot-data-source-decorator)

## 도메인 분석

* 기능 목록
	* 회원 기능
		* 회원 등록
		* 회원 조회
	* 상품 기능
		* 상품 등록
		* 상품 수정
		* 상품 조회
	* 주문 기능
		* 상품 주문
		* 주문 내역 조회
		* 주문 취소
	* 기타 요구사항
		* 상품은 제고 관리가 필요하다.
		* 상품의 종류는 도서, 음반, 영화가 있다. 상품을 카테고리로 구분할 수 있다.
			* 상품 주문시 배송 정보를 입력할 수 있다

### 회원\(Member\)

* 실무에서는 회원이 주문 리스트를 갖지 않는다.
* Address의 임베디드 타입 정보가 추가 되었다.

| 필드명 | 변수명 | 연관관계 | 테이블 외래키 여부 |
| :---: | :---: | :---: | :---: |
| 회원명 | name |  |  |
| 회원주소 | address | @Embedded |  |
| 주문정보 | orders | @OneToMany | x |

### 주문\(Order\)

* 실무에서는 주문이 회원을 참조하는 것으로 충분하다.

| 필드명 | 변수명 | 연관관계 | 테이블 외래키 여부 |
| :---: | :---: | :---: | :---: |
| 주문회원 | member | @ManyToOne | o |
| 주문상품 | orderItems | @OneToMany | x |
| 배송정보 | delivery | @OneToOne | o |
| 주문시간 | orderDate |  |  |
| 주문상태 | orderStatus |  |  |

### 주문상품\(OrderItem\)

| 필드명 | 변수명 | 연관관계 | 테이블 외래키 여부 |
| :---: | :---: | :---: | :---: |
| 주문상품정보 | item | @ManyToMany | o |
| 주문정보 | order | @ManyToMany | o |
| 주문가격 | orderPrice |  |  |
| 주문수량 | count |  |  |

### 상품\(Item\)

* 앨범, 도서, 영화 타입을 통합해서 하나의 테이블로 설계
* DType 컬럼으로 타입을 구분

| 필드명 | 변수명 | 연관관계 | 테이블 외래키 여부 |
| :---: | :---: | :---: | :---: |
| 상품명 | name |  |  |
| 상품가격 | price |  |  |
| 재고수량 | stockQuantity |  |  |
| 카테고리 | categories | @ManyToMany | x |

### 배송\(Delivery\)

* Address의 임베디드 타입 정보가 추가 되었다.

| 필드명 | 변수명 | 연관관계 | 테이블 외래키 여부 |
| :---: | :---: | :---: | :---: |
| 주문정보 | order | @OneToOne | x |
| 배송정보 | address | @Embedded |  |
| 주문상태 | status | @Enumerated |  |

### 주소\(Address\)

| 필드명 | 변수명 | 연관관계 | 테이블 외래키 여부 |
| :---: | :---: | :---: | :---: |
| 도시명 | city |  |  |
| 도로명 | street |  |  |
| 우편번호 | street |  |  |

### 연관관계 매핑 분석

* 회원과 주문
	* 일대다 다대일 연관관계는 항상 연관관계의 주인을 정해야 한다.
	* 외래 키가 있는 주문을 연관관계의 주인으로 정하도록 한다.
	* `Order.member`를 `ORDERS.MEMBER_ID` 외래키와 매핑
* 주문상품과 주문
	* 다대일 양방향 관계
	* 외래 키가 주문 상품에 있으므로 주문상품이 연관관계의 주인이다.
	* `OrderItem.order`를 `ORDER_ITEM.ORDER_ID` 외래 키와 매핑
* 주문상품과 상품
	* 다대일 단방향 관계
	* `OrderItem.item`을 `ORDER_ITEM.ITEM_ID` 외래 키와 매핑
* 주문과 배송
	* 일대일 양방향 관계
	* `Order.delivery`를 `ORDERS.DELIVERY_ID`외래 키와 매핑
* 카테고리와 상품
	* @ManyToMany 사용하여 매핑

> 외래 키가 있는 곳을 연관관계의 주인으로 정하기
>
> * `일대다` 관계에서 항상 `다`쪽에 외래키가 있으므로 외래키가 있는 바퀴를 연관관계의 주인으로 정하면 된다.
> * 반대로 되는 경우에는 외래키 값이 업데이트 되므로 관리와 유지보수가 어렵고, 추가적으로 별도의 업데이트 쿼리가 발생하는 성능 문제도 있다.

### 엔티티 클래스 개발

* 실무에서는 가급적 Getter는 열어두고, Setter는 꼭 필요한 경우게만 사용하도록 한다.
	* Getter를 호출하는 것으로는 정보가 바뀌지 않으나, Setter를 호출하는 경우 데이터가 변경된다.
	* 또한 Setter의 역할을 하는 별도의 메서드를 제공하여 데이터 변경의 용도를 구분할 수 있도록 하는 것이 좋다.
* 실무에서는 @ManyToMany 를 사용하지 말자
	* `@ManyToMany`는 편리한 것 같지만, 중간 테이블\(CATEGORY\_ITEM\)에 컬럼을 추가할 수 없고,

	  세밀하게 쿼리를 실행하기 어렵기 때문에 실무에서 사용하기에는 한계가 있다.

	* 중간 엔티티 \(CategoryItem 를 만들고 @ManyToOne, @OneToMany 로 매핑해서 사용하자.\)
	* 정리하면 다대다 매핑을 일대다, 다대일 매핑으로 풀어 내서 사용하자.
		* @JoinTable 어노테이션을 사용하여 중간 테이블을 생성
* @Embedded 클래스
	* 값 타입은 변경 불가능하게 설계해야 한다.
	* @Setter 를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들자.
	* JPA 스펙상 `엔티티`나 `임베디드 타입( @Embeddable )`은 `자바 기본 생성자(default constructor)`를

	  public 또는 `protected` 로 설정해야 한다.

	* public 으로 두는 것 보다는 protected 로 설정하는 것이 그나마 더 안전 하다.
	* JPA가 이런 제약을 두는 이유는 `JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원`해야 하기 때문이다.

> 엔티티 설계시 주의할 점

* 엔티티에는 가급적 `Setter를 사용하지 말자`
	* Setter가 모두 열려있다. 변경 포인트가 너무 많아서, 유지보수가 어렵다. 나중에 리펙토링으로 Setter 제거
* 모든 연관관계는 `지연로딩`으로 설정!
	* 즉시로딩\( EAGER \)은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다.
	* 특히 `JPQL을 실행할 때 N+1 문제가 자주 발생`한다.
	* `실무에서 모든 연관관계는 지연로딩( LAZY )으로 설정해야 한다.`
	* 연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용한다.
	* `@XToOne(OneToOne, ManyToOne)` 관계는 기본이 즉시로딩이므로 `직접` 지연로딩으로 설정해야 한다.
* **컬렉션은 필드에서 초기화 하자.**
	* 하이버네이트는 엔티티를 영속화 할 때, 컬랙션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한다.
	* 만약 getOrders\(\) 처럼 임의의 메서드에서 컬력션을 잘못 생성하면 하이버네이트 내부 메커니즘에 문제가 발생할 수 있다.
	* 따라서 필드레벨에서 생성하는 것이 가장 안전하고, 코드도 간결하다.

	  ```java
	  class A {
		void method() {
			Member member = new Member();
			System.out.println(member.getOrders().getClass()); // class java.util.ArrayList
			em.persist(team);
			System.out.println(member.getOrders().getClass()); // class org.hibernate.collection.internal.PersistentBag
		}
	  }
	  ```
* 테이블, 컬럼명 생성 전략
	* 하이버네이트 기존 구현 방식: 엔티티의 필드명을 그대로 테이블 명으로 사용 \(SpringPhysicalNamingStrategy\)
	* 스프링 부트에서 하이버네이트 기본 매핑 전략을 변경해서 실제 테이블 필드명은 다름
	* [하이버네이트 네이밍 전략](https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/htmlsingle/#howto-configure-hibernate-naming-strategy)
	* [하이버네이트 가이드](http://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#naming)
* 스프링 부트 신규 설정

  | 필드/컬럼 | 엔티티 | 테이블 |
              | :---: | :---: | :---: |
  | - | 카멜케이스 | 언더스코어 |
  | - | .\(dot\) | \_\(언더스코어\) |
  | - | 대문자 | 소문자 |

* 논리명으로 사용 시
	* 명시적으로 컬럼, 테이블명을 직접 적지 않으면 ImplicitNamingStrategy 사용
	* 테이블이나, 컬럼명을 명시하지 않을 때 논리명 적용

	  ```yaml
	  # spring.jpa.hibernate.naming.implicit-strategy
	  spring.jpa.hibernate.naming.implicit-strategy: 
	  org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
	  ```
* 물리명 적용 방법
	* 모든 논리명에 적용됨, 실제 테이블에 적용 \(username usernm 등으로 회사 룰로 바꿀 수 있음\)

	  ```yaml
	  # spring.jpa.hibernate.naming.physical-strategy
	  spring.jpa.hibernate.naming.physical-strategy:
	  org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
	  ```

## 애플리케이션 구현

* 개요
	* 계층형 구조를 사용하여 구현
	* 엔티티간 연관구조를 중심으로 이해하기 위한 기능 구현

### 회원 도메인, 레포지토리, 서비스 개발 및 테스트

* 구현 기능
	* 회원 등록 회원 목록 조회
* 순서
	* 회원 엔티티 코드 다시 보기
	* 회원 리포지토리 개발
	* 회원 서비스 개발
	* 회원 기능 테스트
* **\[팁\] @Transactional\(readOnly=true\)**
	* 데이터의 변경이 없는 읽기 전용 메서드에 사용
	* 영속성 컨텍스트를 플러시 하지 않으므로 약간의 성능 향상
	* 데이터베이스 드라이버가 지원하면 DB에서 성능 향상
* **\[팁\] 회원명 필드 관리시 유니크 제약조건 추가**
	* 실무에서 검증 로직이 있어도 멀티 쓰레드 상황을 고려하여 회원 테이블의 회원명 컬럼에 유니크 제약 조건을 추가하는 것이 안전하다.
* **\[팁\] 생성자 주입 방식을 권장**
	* 변경 불가능한 안전한 객체 생성 가능
	* 생성자가 하나면, @Autowired 를 생략할 수 있다.
	* final 키워드를 추가하면 **컴파일 시점에 memberRepository 를 설정하지 않는 오류를 체크**할 수 있다.

	  \(보통 기본 생성자를 추가할 때 발견\)

> [Test Code 작성 방법](https://martinfowler.com/bliki/GivenWhenThen.html)

* 스프링 부트는 datasource 설정이 없으면, 기본적을 메모리 DB를 사용하고, driver-class도 현재 등록된 라이브러를 보고 찾아준다.

  추가로 ddl-auto 도 create-drop 모드로 동작한다. 따라서 데이터소스나, JPA 관련된 별도의 추가 설정을 하지 않아도 된다.

### 상품 도메인, 레포지토리, 서비스 개발 및 테스트

* 구현 기능
	* 상품 등록
	* 상품 목록 조회
	* 상품 수정
* 순서
	* 상품 엔티티 개발\(비즈니스 로직 추가\)
	* 상품 리포지토리 개발
	* 상품 서비스 개발
	* 상품 기능 테스트

### 주문 도메인, 레포지토리, 서비스 개발 및 테스트

* 구현 기능
	* 상품 주문
	* 주문 내역 조회
	* 주문 취소
* 순서
	* 주문 엔티티, 주문상품 엔티티 개발
	* 주문 리포지토리 개발
	* 주문 서비스 개발
	* 주문 검색 기능 개발
	* 주문 기능 테스트
* `CascadeType.ALL`의 사용 범위
	* 엔티티의 라이프 사이클이 단순한 경우에만 사용
	* 다른 엔티티도 참조하는 경우 데이터의 정합성에 문제가 생길 수 있다.
* 서비스 계층은 단순히 엔티티에 필요한 요청을 위임하는 역할을 한다.
	* 엔티티가 비즈니스 로직을 가지고 객체지향의 특성을 적극 활용하는 것을 [도메인 모델 패턴](https://martinfowler.com/eaaCatalog/domainModel.html)
	* 엔티티에는 비즈니스 로직이 거의 없고 서비스 계층에서 대부분 의 비즈니스 로직을 처리하는 것을 [트랜잭션 스크립트 패턴](https://martinfowler.com/eaaCatalog/transactionScript.html)

> **JPA의 동적쿼리 해결 방법**
>
> * JPQL로 처리시 쿼리를 문자로 생성하기는 번거롭고, 실수로 인한 버그가 충분히 발생할 수 있다.
> * Criteria는 JPA 표준 스펙이지만 실무에서 사용하기에는 너무 복잡하다.
> * 결국 해결책은 QueryDSL

## 웹 계층 개발

* 개요
	* 홈 화면
		* 회원 기능
			* 회원 등록
			* 회원 조회
		* 상품 기능
			* 상품 등록
			* 상품 수정
			* 상품 조회
		* 주문 기능
			* 상품 주문
			* 주문 내역 조회
			* 주문 취소

### 회원 등록 & 목록

* 폼 객체를 사용하여 Presentation 계층과 Service 계층을 분리해서 사용
* **\[팁\] 폼 객체 vs 엔티티 직접 사용**
	* 참고: 요구사항이 정말 단순할 때는 폼 객체\( MemberForm \) 없이 엔티티\( Member \)를 직접 등록과 수정 화면에서 사용해도 된다.
	* 하지만 화면 요구사항이 복잡해지기 시작하면, 엔티티에 화면을 처리하기 위한 기능이 점점 증가한다.
	* 결과적으로 엔티티는 점점 화면에 종속적으로 변하고, 이렇게 화면 기능 때문에 지저분해진 엔티티는 결국 유지보수하기 어려워진다.
	* 실무에서 엔티티는 핵심 비즈니스 로직만 가지고 있고, 화면을 위한 로직은 없어야 한다.
	* 화면이나 API에 맞는 폼 객체나 DTO를 사용하자.
	* 그래서 화면이나 API 요구사항을 이것들로 처리하고, 엔티티는 최대한 순수 하게 유지하자.

### 상품 등록 & 수정

* **\[중요\] 변경 감지와 병합\(merge\)**
	* **`준영속 엔티티`란 ?**
		* 영속성 컨텍스트가 더는 관리하지 않는 엔티티를 말한다.
		* 여기서는 itemService.saveItem\(book\) 에서 수정을 시도하는 Book 객체를 뜻한다.
		* Book 객체는 이미 DB 에 한번 저장되어서 식별자가 존재한다.
		* 이렇게 임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면 준영속 엔티티로 볼 수 있다.
	* **\[개념\] 준영속 엔티티를 수정하는 2가지 방법**
		* **변경 감지 기능 사용**
			* 영속성 컨텍스트에서 엔티티를 다시 조회한 후에 데이터를 수정하는 방법
			* 트랜잭션 안에서 엔티티를 다시 조회, 변경할 값 선택
			* 트랜잭션 커밋 시점에 변경 감지\(Dirty Checking\)가 동작해서 데이터베이스에 UPDATE SQL 실행

			  ```java
			  class A {
				@Transactional
				void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
					Item findItem = em.find(Item.class, itemParam.getId()); //같은 엔티티를 조회한 다.
					findItem.setPrice(itemParam.getPrice()); //데이터를 수정한다. 
				}
			  }
			  ```
		* **병합\(merge\) 사용**
			* 준영속 상태의 엔티티를 영속 상태로 변경할 때 사용하는 기능

			  ```java
			  class A {
				@Transactional
				void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티 
					Item mergeItem = em.merge(item);
				}
			  }
			  ```
	* **병합 동작 방식**

	  ```text
		1. merge()를 실행
		2. 파라미터로 넘어온 준영속 엔티티의 식별자 값으로 1차 캐시에서 엔티티를 조회한다.
			2.1 만약 1차 캐시에 엔티티가 없으면 데이터베이스에서 엔티티를 조회하고, 1차 캐시에 저장한다.
		3. 조회한 영속 엔티티(mergeMember)에 member 엔티티의 값을 채워넣는다.
		(member 엔티티의 모든 값을 mergeMember에 밀어넣는다. 이때 mergeMember의 회원1)
		4. 영속 상태인 mergeMember를 반환한다.
	  ```

	* **병합 동작 방식을 요약**

	  ```text
		1. 준영속 엔티티의 식별자 값으로 영속 엔티티를 조회한다.
		2. 영속 엔티티의 값을 준영속 엔티티의 값으로 모두 교체한다.(병합)
		3. 트랜잭션 커밋 시점에 변경 감지 기능이 동작해서 데이터베이스에 UPDATE SQL이 실행
	  ```
* **새로운 엔티티 저장과 준영속 엔티티 병합을 편리하게 한번에 처리**
	* save\(\) 메서드를 보면, 저장과 수정을 모두 처리하고 있다.
	* 식별자 값이 없으면 새로운 엔티티로 판단해서 persist\(\)를 통해 영속화하고 만약 식별자 값이 있으면 이미 한번 영속화 되었던 엔티티로 판단하여 merge\(\)를 수행하여 엔티티 내에 값을 수정하게 된다.
	* 여기서 수정은 준영속 상태의 엔티티를 수정할 때 사용한다.
	* 영속 상태의 엔티티는 변경 감지\(dirty checking\) 기능이 동작해서 트랜잭션을 커밋할 때 자동으로 수정되므로 별도의 수정 메서드를 호출할 필요가 없다.
* **\[팁\] Entity에 식별자가 존재하는 이유**
	* `save()` 메서드는 식별자를 자동생성해야 정상 동작한다.
	* 여기서 사용한 `Item` 엔티티의 식별자는 자동생성되도록 `@GeneratedValue`를 선언했다.
	* 따라서 식별자 없이 `save()` 메서드를 호출하면 `persist()`가 호출되면서 식별자 값이 자동으로 할당된다.
	* 반면, 식별자를 직접 할당하도록 `@Id` 만 선언 했다고 가정한 경우
		* 식별자를 직접 할당하지 않고, `save()`메서드를 호출하면 식별자가 없는 상태로 `persist()`를 호출하게되고 식별자가 없다는 예외가 발생한다.

> * 실무에서 보통 업데이트 기능이 매우 제한적이다.
> * 그런데 병합은 모든 필드를 변경해버리고, 데이터가 없으면 `null`로 업데이트를 해버린다.
> * 병합을 사용하면서 이 문제를 해결하려면, 변경 폼 화면에서 모든 데이터를 항상 유지해야 한다.
> * 실무에서는 보통 변경 가능한 데이터만 노출하기 때문에 병합을 사용하는 것이 오히려 번거롭다.

* 해결책
	* **\[팁\] 엔티티를 변경할 때는 항상 변경 감지를 사용하라**
		* 컨트롤러에서 어설프게 엔티티를 생성하지 말 것
		* 트랜잭션이 있는 서비스 계층에 식별자\(id\)와 변경할 데이터를 명확하게 전달하라
		* 트랜잭션이 있는 서비스 계층에서 영속 상태의 엔티티를 조회하고, 엔티티의 데이터를 직접 변경하라
		* 트랜잭션 커밋 시점에 변경 감지가 실행된다.

> `데이터 수정 코드 다시 한번 위 조건에 부합한지 확인하기`

* 강의 질문 내용 정리
	* [준영속 엔티티](https://www.inflearn.com/questions/116627)
	* [cascade](https://www.inflearn.com/questions/99175)
	* [JPA @id 전략](https://www.inflearn.com/questions/89067)
	* [개발 진행 순서](https://www.inflearn.com/questions/79544)
	* [Junit5 사용시](https://www.inflearn.com/questions/42746)
	* [엔티티 생성 메서드를 사용하는 기준](https://www.inflearn.com/questions/38376)
