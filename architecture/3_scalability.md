---
description: Scalability
---

# Scalability

- 확장성이란 무엇일까?
- 개발을 하면서 빠질 수 없는 키워드인 확장성에 대해 생각해보고 정리해보는 시간을 갖는다.

## Scalability 이란 무엇인가?

- 확장성은 애플리케이션이 대기시간이라는 비용을 사용하지 않고 증가된 워크로드를 처리하고 견딜 수 있는 능력을 말한다.
- 앱의 생황을 가정할 때, 앱이 사용자 요청에 응답하는데 x초가 걸리는 경우, 앱에 대한 백만 명에 대한 동시 요청에 각각 응답하는 데 x초가 걸린다.
- 앱의 백엔드 인프라는 백만 개의 동시 요청 부하로 인해 문제가 발생하면 안된다.
- 트래픽 부하가 높을 때, 확장되어야 하고 시스템의 대기 시간을 유지해야 한다.

### 확장성에서 대기 시간(latency)을 무엇을 의미할까?

- 대기시간은 시스템이 사용자 요청에 응답하는 데 걸리는 시간이다.
- 이미지를 가져오기 위해서 앱에 요청을 보내고, 시스템이 요청에 응답하는 데 2초가 걸린다고 가정했을 때, 시스템의 대기 시간은 2초이다.

- 최소 대기 시간은 효율적인 소프트웨어 시스템이 추구하는 것이다.
- 시스템의 트래픽 부하가 아무리 많아도 대기 시간은 늘어나지 않아야 한다.
- 이것이 바로 확장성이다.

- 대기 시간이 동일하게 유지되면 애플리케이션이 증가된 로드와 함께 잘 확장되었고, 해당 애플리케이션은 확장성이 높다고 할 수 있다.

### 확장성의 지연시간 측정(Measuring latency)

- 대기 시간은 사용자가 웹 사이트에서 요청에 대한 응답을 받는 작업 사이의 시간 차이로 측정할 수 있다.

- 지연 시간은 두 부분으로 나뉠 수 있다.
    - 첫 번째로는 네트워크의 지연
    - 두 번째로는 애플리케이션의 지연

> **네트워크 대기 시간**

- 네트워크 대기 시간은 네트워크가 A 지점에서 B 지점으로 데이터 패킷을 보내는 데 걸리는 시간이다.
- 네트워크는 웹 사이트의 증가된 트래픽 부하를 처리할 수 있을 만큼 충분히 효율적이어야 한다.
- 네트워크 대기 시간을 줄이기 위해서 CDN을 사용하고, 가능한 최종 사용자와 가깝게 전 서버에 서버를 배포하는 것이 좋다.

> **애플리케이션 지연 시간**

- 애플리케이션 지연 시간은 애플리케이션이 사용자 요청을 처리하는데 걸리는 시간이다.
- 애플리케이션 대기 시간을 줄이는 방법
    - 애플리케이션에 대한 스트레스 및 부하 테스트를 실행하고 시스템 전체를 느리게 하는 병목 현상을 스캔하는 것

## 확장성의 유형

- 확장이 잘 되기 위해서는 컴퓨터의 성능도 중요하다.
- 서버는 증가된 트래픽 로드를 처리할 수 있을 만큼 강력해야 한다.

- 애플리케이션을 확장하는 두 가지 방법
  - 수직 스케일링(Vertical scaling)
  - 수평적 스케일링(Horizontal scaling)

### 수직 스케일링이란?

- 수직적인 확장은 서버에 더 많은 리소스를 추가하는 것을 의미한다.
- 기존 앱이 16GB RAM이 있는 서버에서 호스팅된다고 가정하는 경우, 증가된 로드를 처리하기 위해 RAM을 32기가로 늘리는 것이다.
- 앱에서 트래픽의 증가가 시작될 때 첫 번째 단계는 수직으로 확장하는 것이다.
- 수직적인 확장을 **scaling up**이라고도 한다.

- 이러한 유형의 확장은 앱을 실행하는 하드웨어의 성능을 높이는 것이다.
- 이러한 방식은 코드 리펙토링이나 복잡한 구성 및 작업을 수행할 필요가 없기 때문에 확장을 위해서는 가장 간단한 방법이다.
- 그러나 수직으로 크기를 조정할 때의 문제점은 단일 서버에 대해 추가할 수 있는 용량에는 제한이 있다는 것이다.

- 트래픽이 너무 커져서 단일 하드웨어로 처리할 수 없는 경우 더 많은 서버를 가져와 함께 작동해야 한다.

### 수평적 스케일링이란?

- 수평 확장은 기존의 하드웨어 리소스 풀에 더 많은 하드웨어를 추가하는 방법으로 시스템 전체의 컴퓨팅 능력을 증가시키는 것이다.
- 증가된 트래픽 유입을 증가된 컴퓨팅 용량으로 쉽게 처리할 수 있으며, 수직적으로 확장하는 방법과는 달리 수평적으로 확장하는 양에는 제한이 없다.

- 수평적인 확장은 사전 계획과 정해진 시간이 필요한 수직적인 확장과는 달리 일정 기간 동안 웹 사이트의 트래픽이 증가 및 감소함에 따라서 실시간으로 동적으로 확장할 수 있는 기능을 제공한다.

## 클라우드 탄력성

- 클라우드 컴퓨팅이 인기가 많은 이유는 동적으로 확장 및 축소가 가능하기 때문이다.
- 사이트에 트래픽 유입이 많은 경우 더 많은 서버 노드가 추가되고, 그렇지 않은 경우 동적으로 추가된 노드가 제거된다.
- 이러한 접근 방법을 클라우드 탄력성이라 하고, 이는 비용 효율적으로 서버를 사용할 수 있다.
- 백엔드에 여러 서버의 노드가 있으면 몇 개의 서버 노드가 충돌하더라도 웹 사이트가 항상 온라인 상태를 유지하는데 도움이 된다.
- 이를 고가용성이라한다.

## 수직적인 확장과 수평적인 확장을 사용하는 기준은 뭘까?

> **수직 및 수평 스케일링의 장 단점**

- 수직 확장은 코드를 건드릴 필요가 없고 복잡한 분산 시스템의 구성을 만들 필요가 없기 때문에 수평 확장에 비해 간단하다.
- 분산 환경을 관리하는 것보다 훨씬 적은 관리, 모니터링 및 관리하는 노력이 필요하다.

- 수직적 확장의 주요한 단점은 가용성 위험이다. 서버는 강력하지만 수는 적다.
- 서버가 다운되는 경우 전체 웹 사이트가 오프라인이 될 위험이 있다.

### 수평적인 확장을 할 때 코드를 변경해야 하는 이유?

- 분산 환경에서 코드를 실행해야 하는 경우 Stateless 이어야 한다.

- 클래스에는 정적 인스턴스가 있을 수 없다.
- 정적 인스턴스는 애플리케이션 데이터를 보유하고 특정 서버가 다운되면 모든 정적 데이터 및 상태가 손실된다.
- 앱이 일관성 없는 상태로 남아있게 된다.

- 키-값 저장소와 같은 영구 메모리를 사용하여 데이터를 보유하고 클래스에서 모든 상태/정적 변수를 제거해야 한다.
- 이러한 방식이 함수형 프로그래밍이 분산 시스템에서 인기를 얻는 이유이다.
- 함수는 항상 Stateless이기 때문이다.

- 앱을 디자인 할 때 항상 얼마나 많은 트래픽을 처리해야 할 지 생각해야 한다.

- 최근 개발되는 서비스는 처음부터 분산형 마이크로 서비스 아키텍처를 채택하고 있으며 워크로드는 클라우드에 배포된다.
- 따라서 기본적으로 워크로드는 수평으로 확장된다.
- 수평적인 확장의 장점은 하드웨어 용량 확장에 대한 제한이 없다.
- 노드와 데이터 센터가 전 세계적으로 설정됨에 따라 데이터는 여러 지리적 지역에 걸쳐 복제된다.

### 앱에 적합한 확장성 접근 방식의 기준으로 무엇일까?

- 앱이 최소한의 일관된 트래픽을 수신할 것으로 예상되는 유틸리티 또는 도구인 경우 중요하지 않을 수 있다.
- 단일 서버로 트래픽을 관리하기에 충분하므로 트래픽 부하가 크게 증가하지 않을 것임을 알면 수직적인 확장을 선택해야 한다.
- 앱이 소셜 네트워크, 피트니스 앱 또는 유사한 것과 같은 앱인 경우 앞으로 트래픽이 기하급수적으로 증가할 것으로 예상되기 때문에, 고가용성과 수평적 확장성은 모두 중요하다.
