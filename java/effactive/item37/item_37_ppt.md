---
description: ordinal 인덱싱 대신 EnumMap을 사용하라
---

# Item 37 발표 내용


![Intro](images/item37.001.png)

## Intro

![Reference](images/item37.002.png)

## Matrix 구조의 데이터 정의

![데이터 정의](images/item37.003.png)

![행과 열로 구분](images/item37.004.png)

### 열거 타입으로 관리하기 - 배열

![Array](images/item37.005.png)

### 열거 타입으로 관리하기 - EnumMap

![EnumMap](images/item37.006.png)

### Stream으로 관리하기 - EnumMap 미사용

![Stream - EnumMap 미사용](images/item37.007.png)

### Stream으로 관리하기 - EnumMap 사용

![Stream - EnumMap 사용](images/item37.008.png)

## 정의된 Matrix 구조 데이터

![이미 정의된 데이터 관리](images/item37.009.png)

### 중첩 열거 타입으로 데이터 관리하기 - 배열

![중첩 열거 타입 - 배열](images/item37.010.png)

### 중첩 열거 타입으로 데이터 관리하기 - Stream & EnumMap

![중첩 열거 타입 EnumMap](images/item37.011.png)

> Stream.groupingBy 조금만 더 상세히

![Stream GroupingBy](images/item37.012.png)

### 중첩 열거 타입에 데이터 추가 시

> 배열 기반

![배열 기반의 데이터 추가](images/item37.013.png)

> "이전" "이후" 쌍으로 연결한 타입 기반

!["이전" "이후" 쌍으로 연결된 타입 기반의 데이터 추가](images/item37.014.png)

## 추가적인 방법

![extra](images/item37.015.png)
