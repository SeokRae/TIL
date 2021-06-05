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
