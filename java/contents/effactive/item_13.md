# clone 재정의는 주의하여 진행하라

- clone 사용 방법
	- 검사 예외
	- 비검사 예외
	- 가변 필드 or 불변 필드를 갖는 클래스
	- Cloneable 을 구현하는 모든 클래스는 clone을 재정의 해야한다.
	- 이때, 접근 제한자는 public, 반환 타입은 클래스 자신으로 변경
	- 메서드는 super.clone()을 호출한 뒤 필요한 필드를 전부 적절히 수정
		- 객체 내부 '깊은 구조'에 숨어 있는 모든 가변 객체를 복사, 복제본이 가진 객체 참조 모두가 복사된 객체들을 가리키게 하는 것

- clone을 사용하기 위한 시점
	- 원본과 복제된 객체가 그 가변 객체를 공유해도 안전한 경우에만 사용

- clone을 대체하는 방식
	- 복사 생성자
	- 복사 팩토리

## intro

- **Cloneable** 인터페이스는 **복제해도 되는 클래스임을 명시**하는 용도의 **믹스인 인터페이스(mixin interface)** 이다.
- clone 메서드는 Object 클래스에 protected 접근제한자로 제공되고 있다.
- 따라서 Cloneable을 구현하는 것만으로는 외부 객체에서 clone 메서드를 호출할 수 없다.

> Cloneable이 하는 일?

- Object의 protected 메서드인 clone의 동작 방식을 결정한다.
- Cloneable 을 구현하지 않고 clone 메서드를 호출하는 경우 CloneNotSupportedException 예외를 발생시킨다.
- Cloneable 을 구현한 클래스의 인스턴스에서 clone을 호출하게 되면 그 객체의 필드들을 하나하나 복사한 객체를 반환한다.
