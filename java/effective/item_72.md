---
description: 표준 예외를 사용하라
---

# Item 72

## Intro

- 재사용 **지향** 예외클래스
	- 상황에 부합한다면 항상 표준 예외를 재사용해야 한다.

|**예외**|**주요 쓰임**|
|:---|:---|
|**IllegalArgumentException**|허용하지 않는 값이 인수로 던져졌을 때(null은 따로 NullPointerException으로 처리)|
|**IllegalStateException**|객체가 메서드를 수행하기에는 적절하지 않은 상태일 때|
|**NullPointerException**|null을 허용하지 않는 메서드에 null을 던졌을 때|
|**IndexOutOfBoundsException**|인덱스가 범위를 넘어섰을 때|
|**ConcurrentModificationException**|허용하지 않는 동시 수정이 발견되었을 때|
|**UnsupportedOperationException**|호출한 메서드를 지원하지 않을 때|

- 재사용 **지양** 예외클래스
	- 여러 성격의 예외들을 포괄하는 클래스이므로 안정적으로 테스트할 수 없다.
	- Exception
	- RuntimeException
	- Throwable
	- Error
