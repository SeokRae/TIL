---
description: 다 쓴 객체 참조를 해제하라
---

# Item 7 발표 내용

![메모리 관리](/Users/seok/IdeaProjects/spring/java/effactive/item7/images/item7.001.png)

## Intro

* 프로그래밍을 하는 동안 메모리를 할당하고 사용하게 되는데 이와 관련된 키워드를 학습하기
* 학습 키워드
	* 메모리 누수로 인한 장애의 징조
	* 메모리의 구조
	* Garbage Collection
	* 메모리 누수의 원인
	* 메모리 모니터링
	
## 참고 내용

![참고 내용](images/item7.002.png)

## 메모리 누수로 인한 장애의 징조

* 어떤 자료구조를 사용하는 프로그램을 오래 실행하다 보면 점차 가비지 컬렉션 활동과 메모리 사용량이 늘어나 결국 성능이 저하될 것이다.
* 성능 저하되는 것을 넘어서 디스크 페이징이나 OutOfMemoryError\(OOM\)을 일으켜 예기지 않게 종료될 수 있다.

![장애의 징조](images/item7.003.png)

## Java Virtual Machine

1. Java Source
2. Java Compiler
3. Java Byte Code
4. Class Loader
5. **Runtime Data Area**
6. Garbage Collection
7. Execution Engine

![자바 가상머신 구조](images/item7.004.png)

### Runtime Data Area

1. **Method\(Static or Class\) Area**
	* Runtime Constant Pool
2. **Heap Area**
	* Young Generation
	* Old Generation
	* MetaSpace
3. **Stack Area**
4. **PC Register**
5. **Native Method Stack Area**

![Runtime Data Area](images/item7.005.png)
![자원 사용](images/item7.006.png)

## Garbage Collection

### Minor GC

![Minor GC 1](images/item7.007.png)

![Minor GC 2](images/item7.008.png)

![Minor GC 3](images/item7.009.png)

![Minor GC 4](images/item7.010.png)

![Minor GC 5](images/item7.011.png)

### MaxTenuringThreshold

![MaxTenuringThreshold](images/item7.012.png)

### Major GC

![Major GC](images/item7.013.png)

### Garbage Collection Trigger

![Garbage Collection Trigger](images/item7.014.png)

### Garbage Collection 수거 대상

![Garbage Collection 수거 대상](images/item7.015.png)

## 중간 정리

![중간 정리](images/item7.016.png)

## 메모리 누수의 원인

![메모리 누수의 원인](images/item7.017.png)

### 1. AutoBoxing

![AutoBoxing](images/item7.018.png)

### 2. Cache

![Cache](images/item7.019.png)

### 3. Connection

![Connection](images/item7.020.png)

### 4. CustomKey

![CustomKey](images/item7.021.png)

### 5. Immutable Key

![Immutable Key](images/item7.022.png)

### 6. Internal Data Structure

![Internal Data Structure](images/item7.023.png)

## 마지막 정리

![정리](images/item7.024.png)

## 메모리 모니터링

![메모리 모니터링](images/item7.025.png)
