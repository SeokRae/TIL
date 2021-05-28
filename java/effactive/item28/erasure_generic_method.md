# Type Erasure

## Intro

- [참고 - Erasure of Generic Methods](https://docs.oracle.com/javase/tutorial/java/generics/genMethods.html)

## Erasure of Generic Methods

> 자바 컴파일러는 일반 메서드 인수의 타입 매개변수도 지운다.

```java
class Example {
    // Counts the number of occurrences of elem in anArray.
    public static <T> int count(T[] anArray, T elem) {
        int cnt = 0;
        for (T e : anArray)
            if (e.equals(elem))
                ++cnt;
        return cnt;
    }
}
```

> T 는 제한되어 있지 않기 때문에, 자바 컴파일러는 Object로 대체된다.

```java
class Example {
    public static int count(Object[] anArray, Object elem) {
        int cnt = 0;
        for (Object e : anArray)
            if (e.equals(elem))
                ++cnt;
        return cnt;
    }
}
```

> 클래스 예시

```java
class Shape { /* ... */ }
class Circle extends Shape { /* ... */ }
class Rectangle extends Shape { /* ... */ }
```

> 일반적인 방법을 작성하여 다양한 구현이 가능하다.

```java
class Example {
    public static <T extends Shape> void draw (T shape) {/* ... */}
}
```

> 자바 컴파일러는 T를 Shape 타입으로 대체된다.

```java
class Example {
    public static void draw (Shape shape) {/* ... */}

}
```
