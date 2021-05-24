---
description: Creational Pattern
---

# Singleton Pattern

## Intro

Singleton 패턴이 클래스의 단일 인스턴스 만 생성하여 애플리케이션 수명 내내 존재하도록하는 방법에 대한 이해

## Singleton 이란?

`싱글턴 패턴`은 클래스의 `유일한 인스턴스`를 만드는 데에 사용된다.

싱글턴의 전형적인 예시로 **`무상태(stateless) 객체`**나 설계상 **`유일해야 하는 시스템 컴포넌트`**를 들 수 있다.

* 캐시, 스레드 풀, 레지스트리와 같은 구성은 단일 인스턴스로 존재해야 한다.

![Singleton](../../.gitbook/assets/diagram_singleton%20(3).png)

## Singleton 생성 방법

* 싱글 톤으로 정의하려는 클래스의 **`생성자를 비공개`**로 만드는 것이다.
* 유일한 인스턴스에 접근할 수 있는 수단으로 **`public static 멤버`**를 하나 마련해두는 것이다.

### 1. public static 멤버가 final 필드인 방식

* private 생성자는 public static final 필드인 Elvis.INSTANCE 를 **초기화할 때 딱 한번 호출**된다.
* public static 필드가 **final**이기 때문에 **절대 다른 객체를 참조**할 수 **없다.**
	* public 으로 필드가 제공되고 있기 때문에 **`간결함이라는 장점`**이 있다.
* public 또는 protected 생성자가 없으므로 클래스에 대해 **`유일성이 보장`**된다.
	* 는 **AccessibleObject.setAccessible** 을 사용해 private 생성자를 호출 할 수 있다.
	* 이러한 공격을 방어하기 위해서는 생성자를 수정하여 두 번째 객체가 생성되려 할 때 예외를 던지는 로직이 추가되어야 한다.

```java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public void leaveTheBuilding() {
    }
}
```

### 2. 정적 팩토리 메서드를 public static 멤버로 제공하는 방식

* getInstance\(\)는 항상 같은 객체의 참조를 반환하므로 새로운 인스턴스가 생성될 수 없다.
	* 리플렉션을 통한 예외는 존재한다.
* **정적 팩토리 방식**의 **장점**
	* 첫 번째 장점은 API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다는 점이다.
	* 두 번째 장점은 정적 팩토리를 제네릭 싱글턴 팩토리로 만들 수 있다는 점이다.
	* 세 번째 장점은 정적 팩토리의 메서드 참조를 Supplier 로 사용할 수 있다는 점이다.

```java
public class Elvis {
    private static final Elvis INSTANCE = new Eivis();

    private Elvis() {
    }

    public static Elvis getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
    }
}
```

* 싱글턴 클래스의 **직렬화**
	* Serializable을 구현한다고 선언하는 것으로는 부족하다.
	* 모든 인스턴스 필드를 일시적\(transient\)이라고 선언하고 readResolve 메서드를 제공해야 한다.
	* 이렇게 하지 않으면 직렬화된 인스턴스를 역직렬화할 때 마다 새로운 인스턴스가 만들어진다.

```java
public class Elvis {
    private static final Elvis INSTANCE = new Eivis();

    // ...
    // 싱글턴임을 보장하는 readResolve 메서드
    private Object readResolve() {
        return INSTANCE;
    }
}
```

### 3. 원소가 하나인 열거 타입\(enum\)을 선언하는 방식

* public 필드 방식과 비슷하지만, 더 간결하고, 추가 노력없이 직렬화할 수 있다.
* 복잡한 직렬화 상황이나 **리플렉션** 공격에서도 제2의 인스턴스가 생기는 일을 막아준다.
* 대부분의 상황에서 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.
* 단, 만들려는 싱글턴이 **상속**이 필요한 경우 사용할 수 없다.

```java
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
    }
}
```

## Singleton 예시

```java
public class AirforceOne {

    // The sole instance of the class
    private static AirforceOne onlyInstance;

    // Make the constructor private so its only accessible to
    // members of the class.
    private AirforceOne() {
    }

    public void fly() {
        System.out.println("Airforce one is flying...");
    }

    // Create a static method for object creation
    public static AirforceOne getInstance() {

        // Only instantiate the object when needed.
        if (onlyInstance == null) {
            onlyInstance = new AirforceOne();
        }

        return onlyInstance;
    }
}

public class Client {

    public void main() {
        AirforceOne airforceOne = AirforceOne.getInstance();
        airforceOne.fly();
    }
}
```

위 코드는 **단일 스레드**에서는 잘 작동되나 **멀티 스레드** 환경에서는 여러 객체가 생성될 가능성이 있다.

* Thread A calls the method getInstance and finds the onlyInstance to be null but before it can actually new-up the instance it gets context switched

  out.

* Now thread B comes along and calls the getInstance method and goes on to new-up the instance and returns the AirforceOne object.
* When thread A is scheduled again, is when the mischief begins.

  The thread was already past the if null condition check and will proceed to new-up another object of AirforceOne and assign it to onlyInstance.

  Now there are two different AirforceOne objects out in the wild, one with thread A and one with thread B.

> **경합 상태\(Race Condition\)**를 수정하는 방법을 해결하기 위한 두 가지 해결방법이 있다.

* 첫 번째는 getInstance\(\)에 synchronized를 통해 동기화 처리하는 것 \(Thread-Safe Initialization\)

```java
public class AirforceOne {
    synchronized public static AirforceOne getInstance();
}
```

* 두 번째는 Tread-safe 한 인스턴스의 정적 초기화를 수행하는 것 \(Eager Initialization\)

```java
public class AirforceOne {
    // The sole instance of the class
    private static final AirforceOne onlyInstance = new AirforceOne();
}
```

> 위 접근 방식의 문제점

* 동기화가 비싸고 정적 초기화가 특정 애플리케이션 실행에서 사용되지 않더라도 객체를 생성한다는 것이다.
* 객체 생성 비용이 비싸면 정적 초기화로 인해 성능이 저하될 수 있다.

### Double-checked Locking

* 싱글톤 패턴보다 발전된 방식은 객체를 `처음 생성될 때만 동기화`하고 `이미 생성된 경우 엑세스하는 스레드를 동기화 하지 않는 것`이다.
* `Double-checked locking` Pattern

```java
public class AirforceOneWithDoubleCheckedLocking {

    // The sole instance of the class. Note its marked volatile
    private volatile static AirforceOneWithDoubleCheckedLocking onlyInstance;

    // Make the constructor private so its only accessible to
    // members of the class.
    private AirforceOneWithDoubleCheckedLocking() {
    }

    public void fly() {
        System.out.println("Airforce one is flying...");
    }

    // Create a static method for object creation
    synchronized public static AirforceOneWithDoubleCheckedLocking getInstance() {

        // Only instantiate the object when needed.
        if (onlyInstance == null) {
            // Note how we are synchronizing on the class object
            synchronized (AirforceOneWithDoubleCheckedLocking.class) {
                if (onlyInstance == null) {
                    onlyInstance = new AirforceOneWithDoubleCheckedLocking();
                }
            }
        }

        return onlyInstance;
    }
}
```

### 자바 API 예시

* java.lang.Runtime
* java.awt.Desktop

## 싱글톤 패턴의 장점

* **다른 모든 클래스**에서 **접근** 할 수 있다.
* **인스턴스**가 하나만 생성됨이 보장된다.
* **Lazy initialization\(게으르게 생성\)** 하여 구현 될 수 있다.
* 인스턴스의 개수를 **변경**하기가 자유롭다.

## 싱글톤 패턴의 단점

* **단일 책임의 원칙\(SRP\)**을 어긴다.
* Singleton 클래스에 대한 **의존도**가 높아진다.
* 싱글톤 클래스에 대한 서브클래스를 만들기 어려워진다.
* **멀티 스레드** 적용 시 **동기화** 문제가 생길 수 있다.
