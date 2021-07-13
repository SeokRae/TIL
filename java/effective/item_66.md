---
description: 네이티브 메서드는 신중히 사용하라
---

# Item 66

## Intro

- 네이티브 메서드의 주요 쓰임 세 가지
	- 레지스트리 같은 플랫폼 특화 기능을 사용하기 위함
	- 네티이브 코드로 작성된 기존 라이브러리를 사용
	- 성능 개선을 목적으로 성능에 결정적인 영향을 주는 역역만 따로 네이티브 언어로 작성

- 플랫폼 특화 기능을 활용하려면 네이티브 메서드를 사용해야 한다.
	- 자바가 성숙해지면서 하부 플랫폼의 기능들을 점차 흡수하고 있다.

- 성능을 개선할 목적으로 네이티브 메서드를 사용하는 것은 거의 권장하지 않는다.
	- 네티이브 메서드는 심각한 단점이 많이 있다...
	- 네이티브 메서드를 사용하려거든 한 번 더 생각해라

> 생각해보기

- 프로젝트 내에서 네이티브 메서드를 사용하고 있는지?
	- 사용한다면 어떤 기능때문에 사용하고 있는지?