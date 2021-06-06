---
description: 한정적 와일드 카드를 사용해 API 유연성을 높이라
---

# Item 31

## Intro

- 매개변수화 타입은 불공변(invariant)이다.
- type 1과 type 2가 있을 때 List<Type1>은 List<Type2>의 하위 타입도 상위 타입도 아니다.
- List<String>은 List<Object>가 하는 일을 제대로 수행하지 못하니 하위 타입이 될 수 없다.
  (리스코프 치환 원칙에 어긋난다.) [아이템 10]()

- 때로는 불공변 방식보다 유연한 방식이 필요하다.

## Stack 클래스의 API

```java
public class Stack<E> {
    public Stack();

    public void push(E e);

    public E pop();

    public boolean isEmpty();
}
```

- 일련의 원소를 스택에 넣는 메서드를 추가해야 하는 경우

```java
public class Stack<E> {
    public void pushAll(Iterable<E> src) {
        for (E e : src) {
            push(e);
        }
    }
}
```

- 이 메서드는 깨끗이 컴파일되지만 완벽하지 않다.
- Iterable src의 원소 타입이 스택의 원소 타입과 일치하면 잘 작동한다.

### 하지만 Stack<Number>로 선언한 후 pushAll(intVal)을 호출하는 경우

- 여기서 intVal은 Integer 타입이다.
- Integer 는 Number 의 하위 타입이니 잘 동작할 것 같다.

```java
import java.util.Stack;

class Example {
    public static void main(String[] args) {
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integers;
        numberStack.pushAll(integers);
    }
}
```

- 실제 매개 변수화 타입이 불공변이기 때문에 오류 메시지를 내뱉는다.
- 자바는 이런 상황에 대처할 수 있는 한정적 와일드카드 타입이라는 특별한 매개변수화 타입을 지원한다.

```shell
StackTest. java:7: error: incompatible types: IterabLe<Integer> 
cannot be converted to Iterable<Number>
        numberStack.pushAll(integers) ;
```

- 위 문제의 해결책
	- 자바는 이런 상황에 대처하기 위해 한정적 와일드 카드 타입이라는 특별한 매개변수화 타입을 지원한다.
	- pushAll의 입력 매개변수 타입은 'E의 Iterable'이 아니라 'E의 하위 타입의 Iterable' 이어야 하며, 와일드 카드 타입 Iterable<? extends E>가 정확히 이런 뜻이다.
	  (사실 extends 라는 키워드는 이 상황에 딱 어울리지 않는다. 하위 타입이란 자기 자신도 포함하지만, 그렇다고 자신을 확장(extends)한 것은 아니기 때문이다.) [아이템 29]()

### E 생산자(producer) 매개변수에 와일드 카드 타입을 적용한 pushAll 메서드

- 아래와 같은 코드로 수정하여 Stack을 물론 이를 사용하는 클라이언트 코드도 말끔하게 컴파일 된다.
- Stack과 클라이언트 모두 깔끔히 컴파일 되었다는 것은 타입 안전하다는 뜻이다.

```java
class Example {
    public void pushAll(Itrable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }
}
```

### 와일드 카드 타입을 사용하지 않은 popAll 메서드

- 주어진 컬렉션의 원소 타입이 스택의 원소 타입과 일치한다면 말금히 컴파일되고 문제없이 동작된다.

```java
public class Example {
    public void popAll(Collection<E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}
```

- 클라이언트 코드에서 Stack<Number>의 원소를 Object용 컬렉션으로 옮기는 시도를 하는 경우 문제가 발생한다.
	- "Collection<Object>는 Collection<Number>의 하위 타입이 아니다." 라는 오류를 발생시킨다.
	- 이와 같은 경우도 와일드 카드를 통해 해결할 수 있다.
	
```java
public class Client {
    public static void main(String[] args) {
        Stack<Number> numberStack = new Stack<>();
		Collection<Object> objects; //= ...;
		numberStack.popAll(objects);
    }
}
```

### E 소비자(consumer) 매개 변수에 와일드 카드 타입 적용한 popAll 메서드

- popAll의 입력 매개변수의 타입이 'E의 Collection'이 아니라 'E의 상위 타입의 Collection'이어야 한다.
  (모든 타입은 자기 자신의 상위 타입이다.)
  
- 와일드 카드 타입을 사용한 Collection<? super E>가 정확히 이런 의미이다.

```java
public class Example {
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}
```

> 중간 정리

- 유연성을 극대화하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입을 사용해야 한다.
- 입력 매개변수가 생산자와 소비자 역할을 동시에 한다면 와일드 카드 타입을 써도 좋을 게 없다.
- 타입을 정확히 지정해야 하는 상황으로, 이때는 와일드 카드 타입을 쓰지 않아야 한다.

> 펙스(PECS): producer-extends, consumer-super

- 와일드카드 타입을 써야하는지에 대한 상황을 분별할 수 있는 기준
- 매개변수화 타입 T가 생상자인경우 <? extends T>를 사용하고, 소비자인 경우 <? super T>를 사용해야 한다.
- Stack 예
    - pushAll의 src 매개변수는 Stack이 사용할 E 인스턴스를 생산하므로 src의 적절한 타입은 Iterable<? extends E>이다.
	- popAll의 dst 매개변수는 Stack으로부터 E 인스턴스를 소비하므로 dst의 적절한 타입은 Collection<? super E> 이다.
	
- PECS 공식은 와일드카드 타입을 사용하는 기본원칙

## Chooser 클래스에 와일드 카드 적용 예시

- 코드 상황
	- 생성자로 넘겨지는 choices 컬렉션은 T 타입의 값을 생산하기만 하나, T를 확장하는 와일드카드 타입을 사용해 선언해야 한다.
	
```java
public class Chooser<T> {
    private final List<T> choiceList;

    public Chooser(Collection<? extends T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size));
    }
}
```

- 변경 후 차이점
	- Chooser<Number>의 생성자에 List<Integer>를 넘기는 상황에서 수정 전에는 컴파일 조차 되지 않는다.
	- 한정적 와일드카드 타입으로 선언하여 수정한 뒤의 생성자에서는 문제가 사라진다.
	

> item 30 의 union 코드 수정

- PECS 공식에 따라 수정
- 반환 타입은 여전히 Set<E> 이다.
	- 반환 타입에는 한정적 와일드카드 타입을 사용하면 안된다.
	- 유연성을 높여주기는 커녕 클라이언트 코드에서도 와일드카드 타입을 써야 한다.
	
```java
class Example {
    public static <E> Set<E> union(Set<? extends E> si, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(sl);
        result.addAll(s2);
        return result;
    }
}
```

- 수정된 코드를 사용하는 클라이언트 코드는 말끔하게 컴파일 된다.

```java
class Client {
    public static void main(String[] args) {
        Set<Integer> integers = Set.of(1, 3, 5);
        Set<Double> doubles = Set.of(2.0, 4.0, 6.0);
        Set<Number> numbers = union(integers, doubles);
    }
}
```

> 정리

- 클라이언트 코드는 와일드카드 타입이 쓰였다는 사실조차 의식하지 못한다.
- 받아들여야 할 매개변수를 받고 거절해야 할 매개변수는 거절하는 작업이 알아서 이루어진다.
- 클라이언트에서 와일드카드 타입을 신경써야 한다면 그 API에 무슨 문제가 있을 가능성이 크다.
- 7버전 전가지는 컴파일러가 올바른 타입을 추론하지 못할 때 언제든 명시적 타입 인수(explicit type argument)를 사용하여 타입을 알려주면 된다.

```java
class Example {
    Set<Number> numbers = Union.<Number>union(integers, doubles);
}
```

> 매개변수(parameter)와 인수(argument)의 차이

- 매개 변수는 메서드 선언에 정의한 변수
- 인수는 메서드 호출 시 넘기는 '실제값'


## item 30의 max 메서드 수정

- PECS 공식에 따른 수정
	- 입력 매개변수에서는 E 인스턴스를 생산하므로 원래의 List<E>를 List<? extends E>로 수정
	- 기존 설명에서 E가 Comparable<E>를 확장한다고 정의, 이때 Comparable<E>는 E 인스턴스를 소비한다.
	- 그래서 매개변수화 타입 Comparable<E> 한정적 와일드카드 타입인 Comparable<? super E>로 대체한다.
	- Comparable은 언제나 소비자이므로, 일반적으로 Comparable<E> 보다는 Comparable<? super E>를 사용하는 편이 좋다.
	- Comparator 도 마찬가지로 Comparator<E> 보다는 Comparator<? super E>를 사용하는 편이 좋다.
	
```java
class Example {
    // 수정 전 메서드
    public static <E extends Comparable<E>> E max(List<E> c);
    
    // 수정 후
    public static <E extends Comparable<? super E>> E max(List<? extends E> list);
}
```

- 수정된 메서드를 호출하는 클라이언트
	- 수정 전의 max 메서드는 java.util.concurrent 패키지의 ScheduledFuture 클래스가 Comparable<ScheduledFuture>를 구현하지 않았기 때문이다.
	- ScheduledFuture는 Delayed의 하위 인터페이스이고, Delayed는 Comparable<Delayed>를 확장했다.
	- 결국, ScheduledFuture의 인스턴스는 다른 ScheduledFuture 인스턴스 뿐 아니라 Delayed 인스턴스와도 비교할 수 있어서 수정 전 max가 이 리스트를 거부하는 것이다.
	- 일반화하여 정리해보면, Comparable 혹은 Comparator 를 직접 구현하지 않고, 직접 구현한 다른 타입을 확장한 타입을 지원하기 위해 와일드카드가 필요하다.
	
```java
class Client {
    public static void main(String[] args) {
        List<ScheduledFuture<?>> scheduledFutures; // = ...;
    }
}
```

## 메서드 정의 시 비한정적 타입 매개변수와 비한정적 와일드 카드 중 어떤 방법이 좋을까?

- 타입 매개변수와 와일드 카드에는 공통되는 부분이 있다.
- 메서드를 정의할 때 둘 중 어느 것을 사용해도 된다.

```java
class Example {
    public static <E> void swap(List<E> list, int i, int j); // 비한정적 타입 매개변수 사용
    public static void swap(List<?> list, int i, int j); // 비한정적 와일드 카드 사용
}
```

- public API를 정의하는 경우 두 번째 방법을 사용하는 것이 좋다.
	- 어떤 리스트든 이 메서드에 넘기면 명시한 인덱스의 원소들을 교환해 줄 것이다.
	- 신경 써야 할 타입 매개변수도 없다.
	

- 기본 규칙
	- 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드 카드로 대체하라
	- 이때 비한정적 타입 매개변수인 경우 비한정적 와일드카드로 바꾸고, 한정적 타입 매개변수라면 한정적 와일드카드로 바꾸면 된다.

- 두 번째 방법을 사용하는 경우 주의사항
	- 방금 꺼낸 원소를 리스트에 다시 넣을 수 없는 오류를 발생시키면서 컴파일 되지 않는다.
	- 원인은 리스트의 타입이 List<?>인데, List<?>에는 null 외에 어떤 값도 넣을 수 없다는데에 있다.
	
```java
class Example {
    public static void swap(List<?> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
```

- 위 문제는 형변환이나 리스트의 로 타입을 사용하지 않고도 해결할 수 있는 방법이 있다.
	- 와일드 카드 타입의 실제 타입을 알려주는 메서드를 private 도우미 메서드로 따로 작성하여 활용하는 방법이다.
	- 실제 타입을 알아내려면 이 도우미 메서드는 제네릭 메서드여야 한다.
	
```java
class Example {
    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }
    // 와일드카드 타입을 실제 타입으로 바꿔주는 private 도우미 메서드
	public static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
	}
}
```

- swapHelper 메서드는 리스트가 List<E>임을 알고 있다.
	- 즉, 이 리스트에서 꺼낸 값의 타입은 항상 E이고, E 타입의 값이라면 이 리스트에 넣어도 안전함을 알고있다.
	- swap 메서드를 호출하는 클라이언트는 복잡한 swapHelper의 존재를 모른 채 그 혜택을 누리는 것이다.
	

## 핵심 정리

- 조금 복잡하더라도 와일드 카드 타입을 적용하면 API가 훨씬 유연해진다.
- 널리 쓰일 라이브러리를 작성한다면 반드시 와일드카드 타입을 적절히 사용해야 한다.
- PECS 공식을 기억하자
	- 생산자(producer)는 extends를 소비자(consumer)는 super를 사용한다.
	- Comparable과 Comparator는 모두 소비자라는 사실을 잊지 않아야 한다.
