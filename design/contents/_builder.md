---
description: Creational Pattern
---

# Builder Pattern

## Intro

`빌더 패턴`은 객체를 만들기 위해 사용된다.
`객체`는 `여러 하위 객체로 구성`되거나 `복잡한 구성의 프로세스`가 필요할 수도 있다.
`빌더 패턴`을 사용하여 복잡한 생생 작업을 `단순화`할 수 있다.

`빌더 패턴`은 복잡한 객체를 `빌드하는 과정`을 `캡슐화`하거나, 숨기고 객체의 `표현`과 `구성`을 `분리`한다.

분리를 통해 동일한 구성 프로세스를 사용하여 ***`다른 표현`***을 구성할 수 있다.
- Java에서 서로 ***`다른 표현`***은 `동일한 구성 프로세스`를 공유할 수 있는 `서로 다른 클래스의 객체`를 만드는 것을 의미한다.

## Builder Pattern 의 생성 배경

- 클래스를 생성하기 위해 선택적 매개변수가 많을 떄 적절하게 대응하기 어렵다.
- 이러한 환경에서 [점층적 생성자 패턴(telescoping constructor pattern)](https://github.com/SeokRae/java-in-action/blob/master/java-in-design/src/main/java/com/example/builder/TelescopingNutritionFacts.java) 을 사용해왔다.
- 점층적 생성자 패턴도 쓸 수는 있지만, 매개변수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.
- 그래서 Setter를 통해 값을 설정하는 [`JavaBeans`](https://github.com/SeokRae/java-in-action/blob/master/java-in-design/src/main/java/com/example/builder/JavaBeansNutritionFacts.java) 패턴을 통해 사용
	- 객체 하나를 만들기 위해서는 메서드를 여러 개 호출해야 하고, 객체가 완전히 생성되기 전까지 `일관성(consistency)`이 무너진 상태에 놓이게 된다.
	- 클래스를 `불변(immutable)` 상태로 만들 수 없다.
- [빌더 패턴](https://github.com/SeokRae/java-in-action/blob/master/java-in-design/src/main/java/com/example/builder/BuilderNutritionFacts.java) 은 `명명된 선택적 매개변(named optional parameters)`를 흉내낸 것이다.
- 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.
- 매개 변수 중 다수가 필수가 아니거나 같은 타입이면 특히 더 그렇다.
- 빌더는 점층적 생성자 보다 클라이언트 코드를 읽고 쓰기가 훨씬 간결하고 자바빈즈보다 훨씬 안전하다.

### 빌터 패턴의 단점

- 빌더 생성 비용이 크지는 않지만 성능에 민감한 상황에서는 문제가 될 수 있다.
- 점층적 생성자 패턴보다는 코드가 장활에서 매개 변수 4개 이상은 되어야 값어치를 한다.

### Builder 패턴의 예시

![예시 엔티티 구성.png](../../.gitbook/assets/diagram_builder.png)

가상 세계에서 모든 항공기는 적어도 아래 세 단계를 필요로 한다.

1. 조종실
2. 엔진
3. 날개

추가적으로 여객기에 `화장실`을 만드는 단계를 추가할 수도 있다.

`단계`는 공식적인 정의에 따른 `구성 프로세스`를 나타낸다.

이 `제품`은 항공기 지만 F-16 또는 Boeing -747과 같은 `다른 표현`을 가질 수 있다.

동일한 공정을 통해 F-16과 Boeing을 모두 생산 할 수 있어야 한다.


> 코드 살펴보기

```java
public abstract class AircraftBuilder {

    public void buildEngine() {

    }

    public void buildWings() {

    }

    public void buildCockpit() {

    }

    public void buildBathrooms() {

    }

    abstract public IAircraft getResult();
}
```

- AircraftBuilder 클래스의 `추상 인터페이스`부터 시작할 수 있다.
- 빌더는 `최종 제품의 일부`가 될 수 있는 각 구성 요소에 대한 메서드가 포함되어 있다.
- 이러한 방법은 빌더가 빌드를 담당하는 최종 제품 변경에 해당 부품을 포함할 지 여부에 따라 Concrete Builder에 선택적으로 재정의 된다.

> 재정의된 Concrete Builder 클래스

- Boeing 항공기는 Aircraft 구성을 모두 필요로 하기 때문에 모든 메서드를 재정의 한다.

```java
public class Boeing747Builder extends AircraftBuilder {

    Boeing747 boeing747;

    @Override
    public void buildCockpit() { }

    @Override
    public void buildEngine() { }
	
    @Override
    public void buildBathrooms() { }
	
    @Override
    public void buildWings() { }
	
    public IAircraft getResult() {
        return boeing747;
    }
}
```

- F16은 화장실이라는 구성이 필요로하지 않기 때문에 `buildBathrooms()` 메서드를 재정의 하지 않는다.

```java
public class F16Builder extends AircraftBuilder {

    F16 f16;

    @Override
    public void buildEngine() {
        // get F-16 an engine
        // f16.engine = new F16Engine();
    }

    @Override
    public void buildWings() {
        // get F-16 wings
        // f16.wings = new F16Wings();
    }

    @Override
    public void buildCockpit() {
        f16 = new F16();
        // get F-16 a cockpit
        // f16.cockpit = new F16Cockpit();
    }

    public IAircraft getResult() {
        return f16;
    }
}
```

- 항공기에 대한 제작사를 담당하는 Director 클래스는 Builder에 대한 로직을 담당하고, 최종 객체는 Builder를 통해 반환된다.

```java
public class Director {

    AircraftBuilder aircraftBuilder;

    public Director(AircraftBuilder aircraftBuilder) {
        this.aircraftBuilder = aircraftBuilder;
    }

    public void construct(boolean isPassenger) {
        aircraftBuilder.buildCockpit();
        aircraftBuilder.buildEngine();
        aircraftBuilder.buildWings();

        if (isPassenger)
            aircraftBuilder.buildBathrooms();
    }
}
```

- 클라이언트는 아래와 같은 패턴을 통해 F-16과 Boeing747을 생성할 수 있다.

```java
public class Client {

    public void main() {

        F16Builder f16Builder = new F16Builder();
        Director director = new Director(f16Builder);
        director.construct(false);

        IAircraft f16 = f16Builder.getResult();
    }
}
```

> 결론

- AircraftBuilder는 특정 항공기의 인터페이스를 `캡슐화` 할 수 있다.

---

### Director를 사용하는 경우에 대한 이야기

Director 클래스 없이 Builder만을 사용하는 패턴도 찾아볼 수 있다.

클라이언트는 빌더를 직접 인스턴스화하고 필요한 메서드를 호출하여 제품을 자체적으로 가져올 수 있다.

이런 방식은 telescoping constructor에 대한 일시적인 해결책일 뿐이다.

- 속성이 너무 많지만 일부 속성이 선택적으로 설정되어야하는 클래스를 작성해야하는 경우
- 빌더를 통해 필수 속성만 설정하고 제품을 작성할 수 있다.

---

### 또 다른 예시(Java API)

- StringBuilder의 경우 append를 통해 연속적인 문자열을 만들 수 있다.
- HtmlDocumentBuilder, PdfDocumentBuilder, DocumentBuilder의 경우 구체적인 유형을 변경하여 다양한 문서 유형을 빌드할 수 있다.

---

### 주의사항

`빌더 패턴`은 `추상 팩토리 패턴`의 차이점

- `빌더` 패턴이 `단계별로 객체를 생성`한다.
- `추상 팩토리` 패턴은 `한 번에 객체를 반환`한다.
