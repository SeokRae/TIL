## API 개발 기본

- 페이지 랜더링 Controller와 API Controller를 구분하기

> 미리정리

- Entity를 Presentation으로 노출하는 것은 위험한 일이다.
- Presentation 노출용 API 명세를 정의할 수 있는 DTO를 작성한다.

### 회원 등록 API

> 회원 등록 개발 시 요청 값으로 엔티티를 반환 [saveMemberV1()](/src/main/java/kr/seok/shop/api/MemberApiController.java)

- **위 회원 등록 로직의 문제점**
	- 회원 등록에 대한 간단한 로직을 작성하고 생성된 값을 반환
	- 위 경우의 문제점은 `Presentation 계층`에 대한 로직이 `Entity`에 추가가 된다는 점
	- 실무에서 회원 엔티티를 위한 API가 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위한 모든 요구사항을 담기는 어렵다.
	- Presentation 계층의 로직이 엔티티에 추가되어 엔티티가 변경되면 API 스펙이 변경되는 불상사가 발생할 가능성이 있다.

**결론: API 요청 스펙이 맞는 별도의 DTO 를 파라미터로 받아야 한다.**

> 회원등록 개발 시 요청 값으로 DTO를 RequestBody에 매핑 [saveMemberV2()](/src/main/java/kr/seok/shop/api/MemberApiController.java)

- `CreateMemberRequest`를 `Member`엔티티 대신에 RequestBody와 매핑시 장점
	- `Entity`와 `Presentation 계층`을 위한 로직을 분리할 수 있다.
	- `Entity`와 API 스펙을 명확하게 분리할 수 있다.
	- `Entity`가 변해도 API 스펙이 변하지 않는다.

**결론: 실무에서 Entity를 API 스펙에 노출하면 안된다.**

### 회원 수정 API

> 회원 수정 개발 시 요청 값으로 DTO를 RequestBody에 매핑 [updateMemberV2()](/src/main/java/kr/seok/shop/api/MemberApiController.java)

- 위 회원 등록과 마찬가지로 Entity 대신 DTO를 사용하여 구현
	- 요청 값에 대한 파라미터를 DTO로 정의
	- 응답 값은 엔티티 자체를 보내는 것이 아니라 수정된 회원엔티티의 `회원ID`와 `회원명`을 반환

**[팁]** RESTful API의 경우 수정 요청 Method는 PATCH 또는 POST를 사용해야한다.

### 회원 조회 API

> 회원 조회 개발 시 응답 값으로 `Entity`를 직접 외부에 노출 [membersV1()](/src/main/java/kr/seok/shop/api/MemberApiController.java)

- **위 회원 조회 로직의 문제점**
	- Entity에 Presentation 계층을 위한 로직이 추가된다.
	- 기본적으로 엔티티의 모든 값이 노출된다.
	- 응답 스펙을 맞추기 위해 로직이 추가된다. (@JsonIgnore, 별도의 뷰 로직 등등)
	- 실무에서는 같은 엔티티에 대해 API가 용도에 따라 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위한 프레젠테이션 응답 로직을 담기는 어렵다.
	- 엔티티가 변경되면 API 스펙이 변한다.
	- 추가로 컬렉션을 직접 반환하면 향후 API 스펙을 변경하기 어렵다.
	  (별도의 Result 클래스 생성으로 해결하는 것이 필요)

**결론: API 응답 스펙에 맞추어 별도의 DTO를 반환한다.**

> 회원 조회 개발 시 응답 값으로 `Entity`를 별도의 `DTO`로 사용하는 경우 [membersV2()](/src/main/java/kr/seok/shop/api/MemberApiController.java)

- **회원 조회 로직의 장점**
	- `Entity`를 DTO로 변환하여 반환하는 경우 Entity가 변해도 API 스펙이 변하지 않는다.
	- 공통 응답 값을 반환하는 `Wrapper Class`를 작성하여 요청 데이터 외에 추가 필드를 포함하는 것이 가능하다.

    ```java
    /* 제네릭 형태로 어떤 타입의 형태든 담을 수 있다. */
    class Result<T> {
        private int count;
        private T data;
    }
    ```
