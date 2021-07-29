---
description: 다른 타입이 적절하다면 문자열 사용을 피하라
---

# Item 62

- [Presentation](/java/effective/item62/item_62_ppt.md)

## Intro

- 다양한 데이터 타입을 문자열로 표현 할 수 있다.
- 하지만 데이터 타입을 문자열로 관리하는 것은 좋지 않다.

## 문자열은 다른 값 타입을 대신하기에 적합하지 않다.

- 파일, 네트워크, 키보드의 입력으로부터 데이터를 받을 때 문자열을 사용할 수 있다.
- 입력받을 데이터가 진짜 문자열일 때만 사용하는 것이 좋다.

- 입력 받은 데이터의 타입
	- 수치형인 경우 int, float, BigInteger 등 적당한 수치 타입으로 변환해야 한다.
	- 예 / 아니오 질문의 답인 경우 열거 타입이나 boolean 타입으로 변환해야 한다.

> 기본 타입이든, 참조 타입이든 적절한 값 타입이 있는 경우 그것을 사용하고, 없다면 새로 하나 작성해야 한다.

## 문자열은 열거 타입을 대신하기에 적합하지 않다.

- 상수를 열거 하는 경우 문자열보다는 열거 타입이 월등히 낫다.

## 문자열은 혼합 타입을 대신하기에 적합하지 않다.

- 여러 요소가 혼합된 데이터를 하나의 문자열로 표현하는 것은 좋지 않다.

- 데이터베이스에서 `복합 속성`과 같은 사례
- ex) 전화번호
	- `지역번호-국번호-가입자개별번호`

- 두 요소를 구분해주기 위한 구분자(delimiter or separator)가 요소 안에서 사용되는 경우 혼란스러운 결과를 초래한다.
	- 이를 해결 하기 위해서 각 요소에 접근하여 문자열을 확인해야 하는 부가적인 기능이 생긴다.
	- 결국에는 데이터 타입을 관리하기 위한 클래스가 생기는 문제점이 생긴다.

## 문자열은 권한을 표현하기에 적합하지 않다.

- 권한(capacity)을 문자열로 표현하는 경우 보안이 취약해지며 의도적으로 같은 키를 사용하여 값을 탈취하는 문제점이 생길 수 있다.

### 권한을 문자열로 관리하는 ThreadLocal 예시

```java
public class ThreadLocal {
    private ThreadLocal() {
    }

    // 현 스레드의 값을 키로 구분해 저장한다.
    public static void set(String key, Object value);

    // (키가 가리키는) 현 스레드의 값을 반환한다.
    public static Object get(String key);
}
```

- 스레드 구분용 문자열 키가 전역 이름 공간에서 공유된다는 문제점
- 두 클라이언트가 서로 소통하지 못해 같은 키를 쓰기로 결정했다면, 의도하지 않게 같은 변수를 공유하게 된다.

> 이를 해결하기 위해서 API는 문자열 대신 위조할 수 없는 키를 사용하면 된다.

- 이 키를 권한(capacity)라 한다.

```java
public class ThreadLocal {
    private ThreadLocal() {
    }

    public static class Key { // (권한)
        Key() {
        }
    }

    public staatic getKey() {
        return Key;
    }

    public static void set(Key key, Object value);

    public static Object get(Key key);
}
```

- 위 방법은 문자열 기반 API의 문제 두 가지를 모두 해결해주지만, 개선의 여지가 존재한다.
- set, get메서드는 정적 메서드일 이유가 없으니 Key 클래스의 인스턴스 메서드로 옮긴다.
- Key는 더 이상 스레드 지역변수를 구분하기 위한 키가 아니라, 그 자체가 스레드 지역변수가 된다.

- 결과적으로 지금 톱레벨 클래스인 ThreadLocal은 별달리 하는 일이 없어지므로 치워버리고, 중첩 클래스 Key의 이름을 ThreadLocal로 바꾼다.

```java
// Key -> ThreadLocal
public final class ThreadLocal {
    public ThreadLocal() {
    }

    public void set(Object value);

    public Object get();
}
```

- API에서는 get으로 얻은 Object를 실제 타입으로 형변환해 써야 해서 타입 안전하지 않다.
- ThreadLocal을 매개변수화 타입으로 선언하여 문제를 해결한다.
	- java.lang.ThreadLocal처럼 구성하여 문자열 기반 API의 문제를 해결

```java
public final class ThreadLocal<T> {
    public ThreadLocal();

    public void set(T value);

    public T get();
}
```
