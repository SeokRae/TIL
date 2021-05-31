# Erasure of Generic Types

## Intro

- `Type Erasure`란 원소 타입을 컴파일 타입에만 검사하고 런타임에는 해당 타입 정보를 알 수 없다.
- 컴파일 타입에만 타입 제약 조건을 정의하고, 런타임에는 타입을 제거한다.

- [Erasure of Generic Types](https://docs.oracle.com/javase/tutorial/java/generics/genTypes.html)

## Java Compiler 의 Type Erasure

- 컴파일 시간에 보다 엄격한 Type 검사를 제공한다.

> **Java Compiler** 가 **Type Erasure** 를 수행하는 사례

- 제네릭 형식의 모든 매개 변수를 범위 또는 형식 매개변수가 제한되지 않은 경우 Object로 바꾼다.
- 따라서 생성된 바이트 코드에는 일반 클래스, 인터페이스 및 메서드만 포함된다.

- Type 안전성을 유지하기 위해 필요한 경우 Type Casting을 추가 한다.
- 확장된 제네릭 타입에서 다형성을 보존하기 위한 브릿지 메서드를 생성한다.

- Type Erasure 는 매개 변수화된 타입에 대해 새 클래스가 생성되지 않도록 한다.
- 결과적으로 제네릭은 런타임 오버 헤드를 발생시키지 않는다.

## Erasure of Generic Types

- Type Erasure Process 동안 Java Compiler는 모든 타입 매개변수를 지우고 타입 매개변수가 바인딩 된 경우, 첫 번째 바인드로 대체되고 타입 매개변수가 바인드 되지 않은 경우 Object로 대체 된다.

> 단일 LinkedList 에서 Node를 나타낸 다음 일반 클래스를 고려하라

```java
public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() { return data; }
    // ...
}
```

> 타입 파라미터 T는 제한되지 않으므로 자바 컴파일러는 이를 Object로 대체한다.

```java
public class Node {

    private Object data;
    private Node next;

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() { return data; }
    // ...
}
```

> 일반 Node 클래스는 제한된 타입 매개변수를 사용한다.

```java
public class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() { return data; }
    // ...
}
```

> 자바 컴파일러는 바인딩된 타입 매개변수 T를 첫 번째 바인딩 된 클래스인 Comparable로 대체한다.

```java
public class Node { 

    private Comparable data; 
    private Node next;

    public Node (Comparable data, Node next) { 
        this.data = data; 
        this.next = 다음; 
    } 

    public Comparable getData () {return data; } 
    // ... 
}
```
