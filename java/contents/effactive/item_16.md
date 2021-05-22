---
description: public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
---

# Item 16

## Intro

- 인스턴스 필드들을 모아 놓은 일 외에는 아무 목적도 없는 클래스를 작성하려 할 때가 있다.

```java
class Point {
    public double x;
    public double y;
}
```

- 데이터 필드에 직접 접근할 수 있으니 캡슐화의 이점을 제공하지 못한다.[아이템 15]()
	- API를 수정하지 않고는 내부 표현을 바꿀 수 없다.
	- 불변식을 보장할 수 없다.
	- 외부에서 필드에 접근할 때 부수 작업을 수행할 수도 없다.
	- 이러한 경우 필드의 접근 제한자를 private로 바꾸고 getter 를 제공한다.


## 접근자(getter)와 변경자(setter) 메서드를 활용한 데이터 캡슐화

- 패키지 바깥에서 접근할 수 있는 클래스라면 접근자를 제공하여 내부 표현 방식을 언제든 바꿀 수 있도록 유연성을 얻을 수 있다.
- package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출한다 해도 하등의 문제가 없다.
	- 클래스가 표현하려는 추상 개념만 올바르게 표현해주면 된다.


```java
class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
```

## public 클래스의 필드를 직접 노출하지 않아야 하는 규칙을 어긴 사례

- java.awt.package 패키지의 Point, Dimension 클래스는 필드를 직접 노출하여 문제가 되는 사례이다.
- 내부를 노출한 Dimension 클래스의 심각한 성능 문제는 타산지석으로 삼아야 한다.

## public 클래스의 필드가 불변일 때 직접 노출하는 경우의 문제점

- API를 변경하지 않고는 표현 방식을 바꿀 수 없다.
- 필드를 읽을 때 부수 작업을 수행할 수 없다는 단점이 있으나, 불변식은 보장할 수 있다.

```java
public final class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    
    public final int hour;
    public final int minute;
    
    public Time(int hour, int minute) {
        if(hour < 0 || hour >= HOURS_PER_DAY) {
            throw new IllegalArgumentException(" 시간 : " + hour);
        }
        if(minute < 0 || minute >= MINUTES_PER_HOUR) {
            throw new IllegalArgumentException(" 분 : " + minute);
        }
        this.hour = hour;
        this.minute = minute;
    }
}
```

## 정리

- public 클래스는 절대 가변 필드를 직접 노출해서는 안된다.
	- 불변 필드라면 노출해도 덜 위험하지만 완전히 안심할 수는 없다.
	- package-private 클래스나 private 중첩 클래스에서는 종종 필드를 노출하는 편이 나을 때도 있다.
