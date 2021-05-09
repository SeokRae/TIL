# 2. 최적화 내용

* 실무 성능 문제를 해결하기 위한 내용

> [API 개발 기본](https://github.com/SeokRae/spring/tree/56149903317680783ac882190a44854c0832e336/spring-jpa/springboot-jpa-shop-optimize/doc/01_api_dev_basic.md)

* 페이지 랜더링 Controller와 API Controller를 구분하기
* 회원 등록 API
* 회원 수정 API
* 회원 조회 API

> [API 개발 고급 - 준비](https://github.com/SeokRae/spring/tree/56149903317680783ac882190a44854c0832e336/spring-jpa/springboot-jpa-shop-optimize/doc/02_api_dev_advanced.md)

* 어떤 기능을 개발할 때, 문제가 될 수 있는 기능은 `Create`, `Update`, `Delete`의 문제보다 `Select`에서 문제가 발생할 가능성이 높다.
* 조회용 샘플 데이터 입력
* API 개발 고급 - 지연 로딩과 조회 성능 최적화 \(엄청엄청 중요\)

> [API 개발 고급 - 실무 필수 최적화](https://github.com/SeokRae/spring/tree/56149903317680783ac882190a44854c0832e336/spring-jpa/springboot-jpa-shop-optimize/doc/03_api_dev_optimize.md)

* 알아야 하는 중요 설정 관리
* OSIV와 성능 최적화

> 다음으로

1. Spring-Data-JPA
2. QueryDSL
	* [build.gradle](https://github.com/SeokRae/spring/tree/56149903317680783ac882190a44854c0832e336/spring-jpa/build.gradle)에 의존성 추가
	* gradle &gt; springboot-jpa-shop-optimize &gt; other &gt; compileQuerydsl 더블클릭하여

	  springboot-jpa-shop-optimize/build/generated/querydsl 경로에 라이브러리를 받음

	* 그 뒤 코드 작성

> 참고

* [Transaction 범위에서 벗어난 준영속상태](https://www.inflearn.com/questions/98643)
* [fetch vs EAGER](https://www.inflearn.com/questions/39516)
* [fetch vs EAGER2](https://www.inflearn.com/questions/30446)
* [JPA Proxy](https://www.inflearn.com/course/ORM-JPA-Basic/lecture/21708?tab=curriculum)
* [default\_batch\_fetch\_size관련 질문](https://www.inflearn.com/questions/34469)
