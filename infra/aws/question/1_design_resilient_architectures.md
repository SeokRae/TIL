---
description: AWS Certified Solutions Architect Associate
---

# 탄력적인 아키텍처 설계

## 다중 계층 아키텍처 솔루션 설계방법

## 고가용성 및 내결함성에 대한 아키텍처 설계방법

> **Elastic Load Balancing**

- 상황
    - AWS 서비스를 이용하여 멀티플레이어 게임을 호스팅
    - 애플리케이션은 단일 가용 영역에서 EC2 인스턴스를 사용하고 있다.
    - 클라이언트는 L4를 통해 연결하는 상황
    - 이와같은 상황에서 고가용성 및 비용 효율적인 설계를 하는 방법?

- **답변**
    - EC2 인스턴스 앞에 Network Load Balancer를 구축 또는 여러 AZ의 인스턴스를 자동으로 추가하거나 제거하도록 Auto Scaling 그룹을 구성
- **기술 정리**
    - 고가용성을 위한 컴퓨팅 서비스 선택
        - Amazon EC2 Auto Scaling 그룹을 생성하여 여러 가용 영역에 인스턴스를 추가 및 제거
    - 비용 효율적인 서비스를 제공하기 위한 컴퓨팅 서비스 선택
        - 트래픽을 인스턴스에 분배하기 위해 아키텍처는 L4에서 작동하는 Network Load Balancer를 사용
        - Auto Scaling 그룹은 클라이언트의 요청에 따라 적절한 수의 인스턴스가 실행되도록 보장하므로 비용 효율적이다.
    - [Elastic Load Balancing and Amazon EC2 Auto Scaling](https://docs.aws.amazon.com/autoscaling/ec2/userguide/autoscaling-load-balancer.html)

---

- 상황
    - 웹 애플리케이션은 퍼블릭 및 프라이빗 서브넷에서 실행된다.
    - 앱플리케이션 아키텍처는 웹 계층과 EC2 인스턴스에서 실행되는 데이터베이스 계층으로 구성된다.
    - 두 계층 모두 단일 가용 영역(AZ)에서 실행된다.
    - 위 상황에서 고가용성을 제공하기 위한 방법은?

- 답변
    - 여러 AZ에 걸쳐 있는 Amazon EC2 Auto Scaling 그룹 및 Application Load Balancer를 생성한다.
    - 동일한 VPC에서 각각 새 AZ에 새 퍼블릭 및 프라이빗 서브넷을 생성한다.
    - 데이터베이스를 Amazon RDS 다중 AZ 배포로 마이그레이션한다.

- 기술정리
    - 고가용성을 보장하기 위해서는 웹 및 데이터베이스 계층 모두 변경해야 한다.
    - 웹 티어의 경우 ALB가 있는 여러 AZ에 걸친 Auto Scaling 그룹은 항상 인스턴스가 실행되고 트래픽이 분산되도록 한다.
    - 다중 AZ 기능이 있는 관리형 데이터베이스를 활용하려면 데이터베이스 계층을 EC2 인스턴스에서 Amazon RDS로 마이그레이션 해야 한다.
    - 이렇게 하면 기본 데이터베이스에 대한 액세스를 방해하는 문제가 있는 경우 보조 데이터베이스가 인계받을 수 있다.
    - [Elastic Load Balancing and Amazon EC2 Auto Scaling](https://docs.aws.amazon.com/autoscaling/ec2/userguide/autoscaling-load-balancer.html)
    - [Amazon RDS 다중 AZ 배포](https://aws.amazon.com/ko/rds/features/multi-az/)

---

- 상황
    - 웹 애플리케이션은 공용 및 개인 서브넷에서 실행된다. 애플리케이션 아키텍처는 EC2 인스턴스에서 실행되는 웹 계층과 데이터베이스 계층으로 구성된다.
    - 두 계층 모두 단일 AZ(Availability Zone)에서 실행된다.
    - 이러한 아키텍처에 고가용성을 제공하기 위해 어떤 단계를 조합해야 할까?

- 답변
    - `여러 AZ에 걸쳐 있는 EC2 Auto Scaling Group 및 ALB를 생성한다.`
    - `동일한 VPC에 각각 새 AZ에 새 공용 및 개인 서브넷을 만든다. 그리고 데이터베이스를 RDS 다중 AZ 배포로 마이그레이션한다.`
    - 현재 아키텍처에 고가용성을 추가하기 위해서는 웹 및 데이터베이스 계층 모두 변경이 필요하다.
    - 웹 계층의 경우 ALB가 있는 여러 AZ에 걸쳐 자동 확장 그룹은 항상 인스턴스가 실행 중이고, 트래픽이 해당 인스턴스에 배포되고 있음을 보장할 것이다.
    - Multi-AZ 기능이 있는 관리되는 데이터베이스를 활용하려면 데이터베이스 계층을 EC2 인스턴스에서 Amazon RDS 마이그레이션해야 한다.
    - 그리하면 주 데이터베이스에 대한 액세스를 금지하는 문제가 있는 경우 보조 데이터베이스가 대신할 수 있다.

- 기술정리
    - [AutoScaling Load Balancer](https://docs.aws.amazon.com/autoscaling/ec2/userguide/autoscaling-load-balancer.html)
    - [Multi AZ](https://aws.amazon.com/rds/features/multi-az/)

## AWS 서비스를 이용한 디커플링 매커니즘 설계방법

## 탄력적인 스토리지를 선택하는 방법

---

> **Storage**

- 상황
    - 하나의 EC2 인스턴스는 데이터를 생성하고 저장하는 애플리케이션이 작동
    - 또 다른 EC2 인스턴스 그룹에는 데이터를 분석하고 수정하는 애플리케이션이 작동

    - 위 상황에서 스토리지의 용량이 지속적으로 늘어날 것이 분명할 때 어떤 스토리지를 선택해야 할지 생각하기

- **답변**
    - EFS는 여러 EC2 인스턴스에 탑재가 가능하고 동시에 액세스 할 수 있는 확장 가능한 파일 시스템을 제공할 수 있다.

- **기술 정리**
    - **Amazon EFS(Amazon Elastic File System)**
        - `EFS를 애플리케이션 인스턴스에 파일 시스템을 마운트 할 수 있다.`
        - 단순하고 확장 가능한 탄력적인 NFS 파일 시스템으로 클라우드 서비스 및 온프레미스 리소스와 함께 사용이 가능
        - 애플리케이션을 중단하지 않고 온디맨드 방식으로 페타바이트 단위로 확장 가능
        - 파일을 추가 및 제거 할 때 자동으로 확장 및 축소되므로 확장하기 위해서 용량을 프로비저닝하고 관리할 필요가 없다.
        - EFS는 네트워크 파일 시스템 버전4(NFSv4.1 및 NFSv.4.0) 프로토콜을 지원
        - EC2 인스턴스가 EFS 파일 시스템에 동시에 액세스하여 둘 이상의 인스턴스 또는 서버에서 실행되는 워크로드 및 애플리케이션에 대한 공통 데이터 소스를 제공할 수 있다.
    - [What is Amazon Elastic File System?](https://docs.aws.amazon.com/efs/latest/ug/whatisefs.html)

---

- 상황
    - 중요한 데이터를 S3 버킷에 저장하고 있는 상황에서 실수로 데이터를 삭제하는 경우 어떻게 복구할 수 있을까?

- **답변**
    - S3의 버전 관리를 통해 이를 해결할 수 있다.

- **기술 정리**
    - **S3**
        - 객체 버전 관리는 동일한 S3 버킷에 객체의 여러 버전을 유지할 수 있는 방법이다.
        - 버전 관리는 의도하지 않은 작업과 응용 프로그램의 오류를 모두 복구할 수 있는 기능을 제공한다.
        - 버전 관리를 사용하여 S3 버킷에 저장된 모든 객체의 모든 버전을 보존, 검색 및 복원할 수 있다.

---

- 상황
    - 온프레미스 인프라에서 AWS 클라우드로 마이그레이션하려는 상황
    - 응용 프로그램 중 하나는 DFSR(분산 파일 시스템 복제)을 사용하여 데이터를 동기화 상태로 유지하는 Windows 파일 서버 팜에 파일을 저장하고 있다.
    - 위 상황에서 DFSR을 어떤 서비스로 변경하여야 할까?

- **답변**
    - Amazon FSx
- **기술 정리**
    - 윈도우 파일 서버용 FSx는 업계 표준 SMB 프로토콜을 통해 액세스 할 수 있는 매우 안정적인 관리형 파일 스토리지를 제공한다.
    - FSx는 윈도우 서버를 기반으로 하며 최종 사용자 파일 복원, 사용자 할당량 및 ACL을 포함하는 다양한 관리 기능을 제공한다.
    - FSx는 단일 AZ 및 다중 AZ 배포 모두에서 DFSR을 지원한다.
    - [Availability and durability: Single-AZ and Multi-AZ file systems](https://docs.aws.amazon.com/fsx/latest/WindowsGuide/high-availability-multiAZ.html)

---

- 상황
    - 웹 사이트는 CloudFront 배포의 오리진 역할을 하는 ALB 뒤에 Auto Scaling 그룹의 EC2 인스턴스에서 실행되고 있다.
    - AWS WAF는 SQL 주입 공격으로부터 보호하는데 사용된다.
    - 보안 로그를 검토한 결과 웹 사이트 접근을 차단해야 하는 외부 악성 IP를 발견했을 때 애플리케이션을 보호하기 위한 방법은 무엇일까?
- **답변**
    - 악성 IP 주소를 차단하는 IP 일치 조건을 추가하도록 AWS WAF 구성을 수정한다.

- **기술 정리**
    - **AWS Web Application Firewall**은 "IP set match statements" 라는 기능을 제공 한다.
    - `IP 일치 조건` 및 `IP 집합 일치`라는 기능은 `IP 주소` 및 `주소 범위 집합`에 대해 웹 요청 출처의 IP주소를 검사한다.
    - 이를 사용하여 요청이 들어온 IP 주소를 기반으로 웹 요청을 허용하거나 차단한다.
    - IPv4 및 IPv6 주소 범위를 지원하며 IP set은 확인할 IP 주소 또는 IP 주소 범위를 최대 10,000개 까지 보유할 수 있다.
    - [IP set match rule statement](https://docs.aws.amazon.com/waf/latest/developerguide/waf-rule-statement-type-ipset-match.html)

---

- 상황
    - 문서 제출을 위한 응용 프로그램을 구축하는데 애플리케이션은 S3 버킷을 스토리지로 사용하려고 한다.
    - 실수로 문서를 삭제하는 것을 방지하고 모든 버전의 문서를 사용할 수 있도록 해야 한다.
    - 사용자는 문서를 업로드하고 수정할 수 있어야 할 때, 어떤 서비스가 이 요구사항을 만족할 수 있을까?

- **답변**
    - 버킷에서 버전관리를 활성화, MFA 삭제를 활성화
    - 요구사항에서의 기능을 딱 만족하는 솔루션은 없기 때문에 각각의 요구사항을 만족할 수 있는 기능을 찾아야 한다.

- **기술 정리**
    - **S3 버전 관리**
        - 문서의 각 버전 사본을 유지
        - [Using versioning in S3 buckets](https://docs.aws.amazon.com/AmazonS3/latest/userguide/Versioning.html)
    - **MFA 삭제**
        - 다중 요소 인증 삭제는 삭제를 시도할 때 두 번째 인증을 요청함으로써 실수로 삭제되는 것을 방지할 수 있다.
        - [Deleting an object from an MFA delete-enabled bucket](https://docs.aws.amazon.com/AmazonS3/latest/userguide/UsingMFADelete.html)

    
---

- 상황
    - 조직은 온프레미스 환경의 데이터 센터에 많은 양의 **데이터(파일)** 를 보유하고 있다.
    - 조직에서 데이터를 S3로 옮기고 싶을 때 AWS Direct Connect 링크를 통한 데이터 마이그레이션을 자동화 하려고 할 떄 어떤 서비스를 이용해야 할까?

- 답변
    - AWS DataSync

- 기술정리
    - AWS DataSync는 온프레미스 스토리지와 S3 또는 Elastic File System(EFS) 간에 대용량 데이터를 온라인으로 이동할 수 있다.
    - DataSync는 복사 작업 스크립팅, 예약, 전송 모니터링, 데이터 유효성 검사, 네트워크 활용 최적화를 포함하여 이러한 많은 작업을 제거하거나 자동으로 처리한다.
    - 소스 데이터 저장소는 SMB(서버 메시지 블록) 파일 서버일 수 있다.
    - [AWS DataSync FAQ](https://aws.amazon.com/ko/datasync/faqs/)