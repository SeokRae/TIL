---
description: 추상화 수준에 맞는 예외를 던지라
---

# Item 73

## Intro

- 메서드가 저수준 예외를 처리하지 않고 바깥으로 **전파(throws)** 하는 경우에 대한 문제점
	- 수행하려는 일과 **관련없는 예외**가 발생할 수 있다.
	- 구현 방식을 바꾸면 또 다른 예외를 발생시켜 기존 클라이언트 프로그램을 깨뜨릴 수 있다.

## 예외 번역

- 위 문제의 **해결책**
	- 상위 계층에서는 **저수준 예외**를 잡아 자신의 **추상화 수준에 맞는 예외**로 바꿔 던져야 한다.
	- 이를 **예외 번역(Exception Translation)** 이라 한다.

```java
public abstract class AbstractSequentialList<E> extends AbstractList<E> {


    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     */
    public E get(int index) {
        try {
            return listIterator(index).next();
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
}
```

- **예외 번역**시 저수준 예외가 디버깅에 도움이 된다면 **예외 연쇄(exception chaining)** 를 사용하는 게 좋다.

## 예외 연쇄

- **예외 연쇄란?**
	- 문제의 **근본 원인(cause)** 인 **저수준 예외**를 **고수준 예외**에 실어 보내는 방식이다.
	- 이를 통해 별도의 **접근자 메서드(Throwable의 getCause 메서드)** 를 통해 필요하면 언제든 **저수준 예외**를 꺼내 볼 수 있다.

```java
class HigherLevelException extends Exception {
    HigherLevelException(Throwable cause) {
        super(cause);
    }
}

class ChildClass {
    public void publicAPIMethod() {
        try {

        } catch (LowerLevelException cause) {
            throw new HigherLevelException(cause);
        }
    }
}
```

- 대부분의 **표준 예외**는 **예외 연쇄용 생성자**를 갖고 있다.
- **예외 연쇄**는 문제의 원인을 (getCause 메서드로) 프로그램에서 접근할 수 있게 해주며, 원인과 고수준 예외의 스택 추적 정보 통합해준다.

- **예외를 전파**하는 것보다 **예외 번역하는 것**이 **우수한 방법**이지만 **남용해서는 안된다.**
- 가능하다면 저수준 메서드가 반드시 성공하도록하여 아래 계층에서는 예외가 발생하지 않도록 하는 것이 최선이다.

- 때로는 **상위 계층 메서드**의 **매개변수 값**을 **아래 계층 메서드**로 건내기 전에 **미리 검사**하는 방법으로 **목적**을 달성할 수 있다.

- 차선책
	- 아래 계층에서의 예외를 피할 수 없다면, 상위 계층에서 그 예외를 조용히 처리하여 문제를 API 호출자에까지 전파하지 않는 방법이 있다.
	- 이러한 경우 java.util.logging 같은 적절한 **로깅 기능**을 활용하여 기록하는 것이 좋다.
	- 클라이언트 코드와 사용자에게 문제를 전파하지 않으면서 프로그래머가 로그를 분석해 추가 조치를 취할 수 있도록 해주기 때문이다.

> Java Exceptions Hierarchy

![Java Exceptions Hierarchy](https://content.cdntwrk.com/files/aHViPTExODQ3OCZjbWQ9aXRlbWVkaXRvcmltYWdlJmZpbGVuYW1lPWl0ZW1lZGl0b3JpbWFnZV82MGE1MGM4NDE2MTU2LnBuZyZ2ZXJzaW9uPTAwMDAmc2lnPTUyN2ViNGI4YWY5NWE2OGI3ZDgxNmU2MGZmMTkwMTZk "https://rollbar.com/blog/blog/java-exceptions-hierarchy-explained")

## 정리

- 아래 계층의 예외를 예방하거나 스스로 처리할 수 없고, 그 예외를 상위 계층을 그대로 노출하기 곤란하다면 예외 번역을 사용하라
- 예외 연쇄를 이용하면 상위 계층에는 맥락에 어울리는 고수준 예외를 던지면서 근본 원인도 함께 알려주어 오류를 분석하기에 좋다.
