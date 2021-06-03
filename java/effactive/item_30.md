---
description: 이왕이면 제네릭 타입으로 만들라
---

# Item 30

## Intro

- 메서드도 제네릭으로 만들 수 있다.
- **매개변수화 타입**을 받는 정적 유틸리티 메서드는 보통 제네릭이다.
- **Collections**의 알고리즘 메서드(binarySearch, sort 등)는 모두 제네릭이다.

## 문제가 있는 제네릭 메서드 작성법

- 컴파일러가 되지만 경고가 두 개 발생한다.

```java
class Example {
    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
}
```

- 발생하는 두 개의 경고 확인

```shell
Unchecked call to 'HashSet(Collection<? extends E>)' as a member of raw type 'java.util.HashSet'
Unchecked call to 'addAll(Collection<? extends E>)' as a member of raw type 'java.util.Set'
```

## 경고를 없애기 위해 타입 안전하게 만드는 방법

- 메서드 선언에서의 세 집합 (입력 2개, 반환 1개)의 원소 타입을 타입 매개변수로 명시하고, 메서드 안에서도 이 타입 매개변수만 사용하게 수정하면 된다.
  (타입 매개변수들을 선언하는) 타입 매개변수 목록은 메서드의 제한자와 반환 타입 사이에 온다.

- 다음 코드에서 타입 매개변수 목록은 \<E\>이고 반환 타입은 Set\<E\>이다.
- 타입 매개 변수의 명명 규칙은 제네릭 메서드나 제네릭 타입이나 똑같다. [아이템 29, 68]()

```java
class Example {
    public static <E> Set<E> union(Set<E> si, Set<E> s2) {
        Set<E> result = new HashSet<>(sl);
        result.addAll(s2);
        return result;
    }
}
```

- 이 메서드는 경고 없이 컴파일 되며, 타입 안전하고, 쓰기도 쉽다.
- 이 메서드를 사용하는 간단한 프로그램
- 직접 형변환하지 않아도 어떤 오류나 경고 없이 컴파일된다.

```java
class Example {
    public static void main(String[] args) {
        Set<String> guys = Set.of("톰", "딕", "해리");
        Set<String> stooges = Set.of("래리", "모에닉", "컬리");
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);
    }
}
```

```shell
"[모에, 톰, 해리, 래리, 컬리, 딕]"이 출력 된다.
```

- union 메서드는 집합 3개 (입력 2개, 반환 1개)의 타입이 모두 같아야 한다.
- 이를 한정적 와일드카드 타입[아이템 31]()을 사용하여 더 유연하게 개선할 수 있다.

- 불변 객체를 여러 타입으로 활용할 수 있게 만들어야 할 때가 있다.
- 제네릭은 런타임에 타입 정보가 소거[아이템 28]()되므로 하나의 객체를 어떤 타입으로든 매개변수화 할 수 있다.
- 하지만 이렇게 하려면 요청한 타입 매개 변수에 맞게 매번 그 객체의 타입을 바꿔주는 정적 팩터리를 만들어야 한다.
- 이 패턴을 제네릭 싱글턴 팩터리라 한다.
	- Collections.reverseOrder 같은 함수 객체 [아이템 42](), Collections.emptySet 같은 컬렉션 용으로 사용한다.

## 항등 함수(identity function)를 담은 클래스를 만드는 경우

- 자바 라이브러리의 Function.identity를 사용하면 되지만 [아이템 59](), 공부를 위해서 직접 한번 작성
- 항등함수 객체는 상태가 없으니 요청할 때마다 새로 생성하는 것은 낭비이다.
- 자바의 제네릭이 실체화된다면 항등함수를 타입별로 하나씩 만들어야 했겠지만, 소거 방식을 사용한 덕에 제네릭 싱글턴 하나면 충분하다.

- 제네릭 싱글턴 팩터리 패턴
	- UnaryOperator<Object> 는 UnaryOperator<T>가 아니기 때문에 비검사 형변환 경고가 발생한다.
	- 하지만 항등함수란 입력 값을 수정 없이 그대로 반환하는 특별한 함수이므로, T가 어떤 타입이든 UnaryOperator<T>를 사용해도 타입 안전하다.
	- 직접 개발하는 개발자는 이 사실을 알고 있기 때문에, 비검사 형변환 경고를 숨겨도 안심할 수 있다.
	- @SuppressWarnings("unchecked") 어노테이션을 추가하면 오류나 경고 없이 컴파일 된다.

```java
import java.util.function.UnaryOperator;

class Example {
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        // Object는 T 가 아니기 때문에 비검사 형변환 경고가 발생한다.
        return (UnaryOperator<T>) IDENTITY_FN;
    }
}
```

- 제네릭 싱글턴 팩터리 패턴을 사용하는 방법
	- 제네릭 싱글턴을 UnaryOperator<String>, UnaryOperator<Number>로 사용

```java
class Example {
    public static void main(String[] args) {
        String[] strings = {"삼베", "대마", "나일론"};
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings)
            System.out.println(sameString.apply(s));
        Number[] numbers = {1, 2.0, 3L};
        UnaryOperator<Number> sameNumber = identityFunction();

        for (Number n : numbers)
            System.out.println(sameNumber.apply(n));
    }
}
```

- 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정하는 방법
	- 이를 재귀적 타입 한정(recursive type bound)이라 한다.
	- 재귀적 타입 한정은 주로 타입의 자연적 순서를 정하는 Comparable 인터페이스 [아이템 14]()와 함께 쓰인다.
	- 여기서 타입 매개변수 T는 Comparable<T>를 구현한 타입이 비교할 수 있는 원소의 타입을 정의한다.
	- 실제로 거의 모든 타입은 자신과 같은 타입의 원소와만 비교할 수 있다.
	- 따라서 String은 Comparable<String>을 구현하고 Integer는 Comparable<Integer>를 구현하는 식이다.
	- Comparable을 구현한 원소의 컬렉션을 입력받는 메서드들은 주로 그 원소들을 정렬 혹은 검색하거나, 회솟값이나 최댓값을 구하는 식으로 사용된다.
	- 이 기능을 수행하려면 컬렉션에 담긴 모든 원소가 상호 비교될 수 있어야 한다.
	- 타입 한정인 <E extends Comparable<E>> 는 "모든 타입 E는 자신과 비교할 수 있다"라고 읽을 수 있다.
	- 상호 비교 가능하다는 뜻

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

```java
class Example {
    public static <E extends Comparable<E>> E max(Collection<E> c);
}
```

- 메서드의 구현부
	- 컬렉션에 담긴 원소의 자연적 순서를 기준으로 최댓값을 계산하며, 컴파일 오류나 경고는 발생하지 않는다.
	- 이 메서드에 빈 컬렉션을 건네면 IllegalArgumentException을 던지기 때문에 Optional<E>를 반환하도록 수정하는 편이 좋다. [아이템 55]()

```java
import java.util.Objects;

class Example {
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }
}
```

## 마무리

- 관용구, 여기에 와일드카드를 사용한 변형 [아이템 31]()
- 시뮬레이트한 셀프 타입 관용구 [아이템 2]()를 이해하고 나면 실전에서 마주치는 대부분의 재귀적 타입 한정을 무리 없이 다룰 수 있다.

## 정리

- 제네릭 타입과 마찬가지로, 클라이언트에서 입력 매개변수와 반환값을 명시적으로 형변환 해야 하는 메서드보다 제네릭 메서드가 더 안전하며 사용하기에 쉽다.
- 타입과 마찬가지로, 메서드도 형변환 없이 사용할 수 있는 편이 좋으며, 많은 경우 그렇게 하려면 제네릭 메서드가 되어야 한다.
- 형변환을 해줘야 하는 기존 메서드는 제네릭하게 만들어 편의성을 제공하는 것이 좋다. [아이템 26]()
