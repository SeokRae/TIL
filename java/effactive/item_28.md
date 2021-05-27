---
description: 배열보다는 리스틀 사용하라
---

# Item 28

- 제네릭은 자바 5부터 사용할 수 있다.
- 제네릭을 지원하기 전에는 컴렉션에서 객체를 꺼낼 때마다 형변환을 해야 했다.
- 제네릭을 사용하면 컬렉션이 담을 수 있는 타입을 컴파일러에 알려주게 된다.
- 컴파일러는 알아서 형변환 코드를 추가할 수 있게 되고, 엉뚱한 타입의 객체를 넣으려는 시도를 컴파일 과정에서 차단하여 더 안전하고 명확한 프로그램을 만들어 준다.

- 제네릭의 `이점을 최대로 살리고` `단점을 최소화하는 방법`을 이야기 한다.

## Intro

- 용어 정리
	- 클래스와 인터페이스 선언에 타입 매개변수 (type parameter)가 쓰이면, 이를 **제네릭 클래스** 혹은 **제네릭 인터페이스**라 한다.
	- 제네릭 클래스와 제네릭 인터페이스를 통틀어 제네릭 타입(generic type)이라 한다.

## 제네릭 타입

- 각각의 제네릭 타입은 일련의 **매개변수화 타입(parameterized type)** 을 정의한다.

```java
class Example {
    // String이 정규(formal) 타입 매개변수 E에 해당하는 실체(actual) 타입 매개변수이다.
    List<String> list;
}
```

- 제네릭 타입을 하나 정의하면 그에 딸린 **로 타입(raw type)** 도 함께 정의된다.
	- 로 타입이란 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 때를 말한다.

- 제네릭 타입의 동작
	- 로 타입은 타입 선언에서 제네릭 타입 정보가 전부 지워진 것처럼 동작한다.
	- 제네릭이 도래하기 전 코드와 호환되도록 하기 위한 궁여지책이라 할 수 있다.

> 제네릭 지원하기 전 컬렉션 선언 방식

- 아래 코드를 사용하는 경우 실수로 Stamp 대신 Coin을 넣어도 오류 없이 컴파일 되고 실행된다.

```java
class Temp {
    private final Collection stamps; // = ...;
}
```

- 컴파일러가 모호한 경고 메시지를 보여준다.

```java
stamps.add(new Coin(...)); // "unchecked call" 경고를 내뱉는다.
```

- 컬렉션에서 Coin을 꺼내기 전까지 오류를 알아차리지 못한다.

```java
// 반복자의 로 타입은 사용하지 말 것
for(Iterator i=stamps.iterator();i.hasNext();){
        Stamp stamp=(Stamp)i.next(); // ClassCastException을 던진다.
        stamp.cancel();
        }
```

- 오류는 컴파일 때 발견하는 것이 좋다.
	- 위 코드는 오류가 발생하고 한참 뒤 런타임에야 확인할 수 있다.
	- 런타임에 문제를 겪는 코드와 원인을 제공한 코드가 물리적으로 상당히 떨어져 있을 가능성이 커진다.
	- ClassCastException()이 발생하는 경우 stamps 에 Coin을 넣는 지점을 찾기 위해 코드 전체를 훑어봐야 할 수도 있다.


- 제네릭을 활용하는 경우 **이 정보**가 주석이 아닌 타입 선언 자체에 녹아든다.
	- 컴파일러는 stamps 에는 Stamp의 인스턴스만 넣어야 함을 컴파일러가 인지하게 된다.
	- 아무런 경고 없이 컴파일 된다면 의도대로 동작할 것임을 보장한다.
	- 물론 컴파일러 경고를 숨기지 않았어야 한다.

  ```java
  class Temp {
	  // 매개변수화된 컬렉션 타입을 통해 타입 안정성을 확보
	  private final Collection<Stamp> stamps; // = ...;
  }
  ```

- 엉뚱한 타입의 인스턴스를 넣으려 하는 경우
	- 컴파일 오류 발생 및 에러 내용

  ```java
  error: incompatible types: Coin cannot be converted to Stamp
		  stamps.add(new Coin());
					 ^
  ```

- 컴파일러
	- 컬렉션에서 원소를 꺼내는 모든 곳에 보이지 않는 형변환을 추가하여 절대 실패하지 않음을 보장한다.
	- 컴파일러 경고가 나지 않았고 경고를 숨기지도 않았다고 가정하는 경우

- 로 타입(타입 매개변수가 없는 제네릭 타입)을 쓰는 걸 언어 차원에서 막아 놓지는 않았지만 절대로 써서는 안된다.
	- 로 타입을 쓰면 제네릭이 안겨주는 안전성과 표현력을 모두 읽게 된다.

## 로 타입(Raw Type)의 존재 이유

- 호환성
- 기존 코드를 수용하면서 제네릭을 사용하는 새로운 코드와도 맞물려 돌아가기 위함
- 로 타입을 사용하는 메서드에 매개변수화 타입의 인스턴스를 넘겨도 동작해야한다.

- 마이그레이션 호환성을 위해 로 타입을 지원하고 제네릭 구현에는 소거(erasure) 방식을 사용하기로 한다.
	- List 같은 로 타입은 사용해서는 안된다.
	- List<Object>처럼 임의 객체를 허용하는 매개변수화 타입은 괜찮다.

- 로 타입(List)과 매개변수화 타입(List<Object>)의 차이
	- List는 제네릭 타입을 사용하지 않았다.
	- List<Object>는 모든 타입을 허용한다는 의사를 컴파일러에 명확히 전달한 것이다.

> 매개변수

- List를 받는 메서드에 List<String>을 넘길 수 있다.
- List를 받는 메서드에 List<Object>를 넘길 수 없다.
- 이는 제네릭 하위 타입 규칙 때문이다.
- 즉 List<String>은 로 타입인 List의 하위 타입이지만, List<Object>의 하위 타입은 아니다. [아이템 28]()
- 그 결과 List<Object> 같은 매개변수화 타입을 사용할 때와 달리 List 같은 로 타입을 사용하면 타입 안전성을 읽게 된다.

- 구체적인 예시
	- 아래 코드는 컴파일은 되지만 로 타입인 List를 사용하여 형변환하려 할 때 예외가 발생한다.
	- Integer를 String으로 변환하려 시도한 것이다.
	- 이 형변환은 컴파일러가 자동으로 만들어 준 것이라 보통은 실패하지 않는다.
	- 하지만 이 경우 컴파일러의 경고를 무시하여 그 대가를 치르는 것이다.

  ```java
  class Example {
	  public static void main(String[] args) {
		  List<String> strings = new ArrayList<>();
		  unsafeAdd(strings, Integer.valueOf(42));
		  String s = strings.get(0); // 컴파일러가 자동으로 형변환 코드를 넣어준다.
	  }
	  
	  public static void unsafeAdd(List list, Object o) {
		  list.add(o);
	  }
  }
  ```

	- 로 타입인 List를 매개 변수화 타입인 List<Object>로 바꾼 다음 다시 컴파일하는 경우
		- 오류 메시지 출력되어 컴파일 조차 되지 않는다.

  ```java
  error: incompatible types: List<String> cannot be converted to List<Object>
	  unsafeAdd(strings, Integer.valueOf(42));
  ```

> 원소 타입을 몰라도 되는 로 타입을 사용하려는 경우

- 비한정적 와일드카드 타입(unbounded wildcard type)을 대신 사용하는 것이 좋다.
- 제네릭 타입을 쓰고 싶지만 실제 타입 매개변수가 무엇인지 신경쓰고 싶지 않은 경우 물음표(?)를 사용
- 제네릭 타입인 Set<E>의 비한정적 와일드카드 타입은 Set<?>이다.
- 어떤 타입이라도 담을 수 있는 가장 범용적인 매개변수화 Set 타입

```java
static int numElementsInCommon(Set<?> s1,Set<?> s2){...}
```

> 비 한정적 와일드 타입인 Set<?>와 로 타입인 Set의 차이

- 와일드 카드 타입은 안전하고 로 타입은 안전하지 않다.
- 로 타입 컬렉션은 아무 원소나 넣을 수 있으니 타입 불변식을 훼손하기 쉽다.
- 반면, Collection<?>에는 null외에 어떤 원소도 넣을 수 없다.
- 다른 원소를 넣으려 하는 경우 오류 메시지를 출력한다.
- 결국 컴파일러는 제 역할을 한 것이다.
	- 구체적으로 어떤 원소도 Collection<?>에 넣지 못하게 했으며 컬렉션에서 꺼낼 수 있는 객체의 타입도 전혀 알 수 없게 했다.
- 이러한 제약을 받아들일 수 없다면 제네릭 메서드(아이템 30)나 한정적 와일드 카드 타입(아이템 31)을 사용하면된다.

> 로 타입을 쓰지 말라는 규칙의 소소한 예외

- class 리터럴에는 로 타입을 써야 한다.
	- 자바 명세는 class 리터럴에 매개변수화 타입을 사용하지 못하게 했다.
	- List.class, String[].class, int.class는 허용하고 List<String>.class와 List<?>.class는 허용하지 않는다.
- instanceof 연산자와 관련이 있다.
	- 런타임에는 제네릭 타입 정보가 지워지므로 instanceof 연산자는 비한정적 와일드카드 타입 이외의 매개변후화 타입에는 적용할 수 없다.
	- 로 타입이든 비한정적 와일드 타입이든 instanceof 는 완전히 똑같이 동작한다.
	- 비한정적 와일드카드 타입의 꺾쇠괄호와 물음표는 아무런 역할 없이 코드만 지저분하게 만드므로, 차라리 로 타입을 쓰는 편이 깔끔하다.

- 제네릭 타입에 instanceof 를 사용하는 올바른 예시
	- o 타입이 Set임을 확인한 다음 와일드 카드 타입인 Set<?>로 형변환해야 한다.
	- 이는 검사 형변환(checked cast)이므로 컴파일러 경고가 뜨지 않는다.

```java
class Example {
    public void goodExample() {
        if (o instanceof Set) {        // 로 타입
            Set<?> s = (Set<?>) o; // 와일드 카드 타입
        }
    }
}
```

## 정리

- 로 타입을 사용하면 런타임에 예외가 일어날 수 있으니 사용하면 안된다.
- 로 타입은 제네릭이 도입되기 이전 코드와 호환성을 위해 제공될 뿐이다.
- Set<Object>는 어떤 타입의 객체도 저장할 수 있는 매개변수화 타입이고, Set<?>는 모종의 타입 객체만 저장할 수 있는 와일드카드 타입이다.
- 그리고 이들의 로 타입인 Set은 제네릭 타입 시스템에 속하지 않는다.
- Set<Object>와 Set<?>는 안전하지만, 로 타입인 Set은 안전하지 않다.

## 용어 정리

|한글용어|영문용어|예시|아이템|
|:---:|:---:|:---:|:---:|
|매개변수화 타입|parameterized type|List<String>|아이템26|
|실제 타입 매개변수|actual type parameter|String|아이템26|
|제네릭 타입|generic type|List<E>|아이템26,29|
|정규 타입 매개변수|formal type parameter|E|아이템26|
|비한정적 와일드카드 타입|unbounded wildcard type|List<?>|아이템26|
|로 타입|raw type|List|아이템26|
|한정적 타입 매개변수|bounded type parameter|<E extends Number>|아이템29|
|재귀적 타입 한정|recursive type bound|<T extends Comparable<T>>|아이템30|
|한정적 와일드카드 타입|bounded wildcard type|List<? extends Number>|아이템31|
|제네릭 메서드|generic method|static <E> List<E> asList(E[] a)|아이템30|
|타입 토큰|type token|String.class|아이템33|
