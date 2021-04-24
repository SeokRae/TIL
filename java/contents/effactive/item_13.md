# clone 재정의는 주의하여 진행하라

- clone 사용 방법
- clone을 사용하기 위한 시점
- clone을 대체하는 방식

## intro

- **Cloneable** 인터페이스는 **복제해도 되는 클래스임을 명시**하는 용도의 **믹스인 인터페이스(mixin interface)** 이다.
- clone 메서드는 Object 클래스에 protected 접근제한자로 제공되고 있다.
- 따라서 Cloneable을 구현하는 것만으로는 외부 객체에서 clone 메서드를 호출할 수 없다.

> Cloneable이 하는 일?

- Object의 protected 메서드인 clone의 동작 방식을 결정한다.
- Cloneable 을 구현하지 않고 clone 메서드를 호출하는 경우 CloneNotSupportedException 예외를 발생시킨다.
- Cloneable 을 구현한 클래스의 인스턴스에서 clone을 호출하게 되면 그 객체의 필드들을 하나하나 복사한 객체를 반환한다.
