---
description: 자바 궁금증
---

# 자바 제네릭의 타입 소거와 기존 라이브러리와의 호환

- 참고
	- [자바의 정석](https://codechobo.tistory.com/)

## Intro 

- JDK 1.5에 처음 도입된 제네릭은 기존 라이브러리와 어떻게 호환되는 걸까?

## 제네릭(generic) 이란?

- 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입체크(compile-time type check)를 해주는 기능
- 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형변환의 번거로움을 줄일 수 있다.

## 컴파일 시 제네릭 클래스

- 제네릭 클래스는 컴파일 후에 raw 타입으로 바뀐다. 즉, 제네릭이 제거 된다.

## 제네릭 타입의 제거

- 컴파일러는 제네릭 타입을 이용하여 소스파일을 체크하고, 필요한 곳에 형변환을 넣어준다. 그리고 제네릭 타입을 제거한다.
- 즉, 컴파일된 파일에는 제네릭 타입에 대한 정보가 없다.

### 제네릭 타입 제거과정

1. 제네릭 타입의 경계(bound)를 제거한다.
    - 제네릭 타입이 <T extends Fruit> 라면 Fruit로 치환된다.
	- <T>인 경우 T는 Object로 치환된다.
	- 그리고 클래스 옆에 타입 선언은 제거된다.
	
2. 제네릭 타입을 제거한 후에 타입이 일치하지 않으면, 형변환을 추가한다.
	- List의 get()은 Object 타입을 반환하므로 형변환이 필요하다.
	- 와일드 카드가 포함되어 있는 경우, 적절한 타입으로 형변환이 추가된다.
	```java
 	// 컴파일 전
	class Example {
 		static Juice makeJuice(FruitBox<? extends Fruit> box) {
 			String tmp = "";
 			for(Fruit f : box.getList()) {
 				tmp += f + " ";
			}
 			return new Juice(tmp);
    	}
	}
	// 컴파일 후
	class Example {
		static Juice makeJuice(FruitBox box) {
			String tmp = "";
 			Iterator it = box.getList().iterator();
 			while(it.hasNext()) {
 				tmp += (Fruit) it.next() + " "; 			
			}
 			return new Juice(tmp);
		}
	}
	```
