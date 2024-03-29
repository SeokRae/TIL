---
description: AWS Certified Solutions Architect Associate
---

# 비용 최적화 아키텍처 설계

## 비용 효율적인 스토리지 솔루션을 식별하는 방법

> **Storage**

- 상황
    - 온라인 전자 상거래 서비스에서 S3로 일부 애플리케이션 로그 파일을 백업해야 한다.
    - 얼마나 자주 로그에 액세스 할 지 또는 어떤 로그에 가장 많이 액세스 할 지는 알 수가 없는 상황이다.
    - 이때 어떤 S3 스토리지를 사용해야 비용 효율적으로 사용할 수 있을까?

- **답변**
    - **S3 Intelligent-Tiering**

- **기술 정리**
    - **S3 Intelligent-Tiering**
        - S3 Intelligent-Tiering 스토리지 클래스는 성능 영향이나 운영간의 오버헤드 없이 데이터를 가장 비용 효율적인 액세스 계층으로 자동으로 이동하여 비용을 최적화하도록 설계되어 있다.
        - 자주 액세스 하는 데 최적화된 계층과 자주 액세스 하지 않는 데 최적화된 저비용 계층의 두 가지 액세스 계층에 개체를 저장하여 작동한다.
        - 로그 파일에 대한 액세스 패턴을 알 수 없기 때문에 이런 상황에서는 지능형 계층화를 적용하기에 적절하다.
    - [Amazon S3 스토리지 클래스 - 알 수 없거나 변화하는 액세스](https://aws.amazon.com/ko/s3/storage-classes/#Unknown_or_changing_access)

---

- 상황
    - 메일 로그 파일에 대한 분석 작업을 실행할 계획으로 스토리지 솔루션을 선택해야 한다.
    - 로그의 크기와 수는 알 수 없으며 24시간 동안만 유지된다.
    - 이러한 상황에서 선택할 수 있는 비용 효율적인 솔루션은 무엇인가?

- 답변
    - **S3 Standard**
    - 단기 스토리지 솔루션에서 S3 Standard가 적절한 선택이다.
    - 로그의 크기와 개수를 알 수 없고, 현 단계에서 접근 패턴을 완전히 파악하기 어렵기 때문에 S3 표준을 사용하는 것이 비용 효율적이고,
    - 즉각적인 액세스를 제공하여 개체당 검색 비용이나 최소 용량 요금이 없기 때문에 가장 좋다.

- 기술 정리
    - **S3 Standard**
        - [Amazon S3 스토리지 클래스](https://aws.amazon.com/ko/s3/storage-classes/)

## 비용 효율적인 컴퓨팅 및 데이터베이스 서비스를 식별하는 방법

> **EC2**

- 상황
    - 전자 상거래 웹 사이트는 ALB 뒤에 EC2 인스턴스에서 실행되고 있는 상황이다.
    - 이 애플리케이션은 stateless and elastic 한 상태이고, 최소 10 개의 인스턴스에서 최대 200개의 인스턴스까지 확장이 된다.
    - 시간에 80%가 40개 이상의 인스턴스를 필요로 하는 상황일 때 비용 최소화 하는 방법은?

- **답변**
    - 예약 인스턴스를 구매하여 40개의 인스턴스를 처리하도록 한다.
    - 온 디맨드 및 스팟 인스턴스를 사용하여 나머지 인스턴스를 처리한다.
    - 현재 상황이 시간의 80%가 최소 40개의 인스턴스를 필요로 하는 경우 예약 인스턴스가 적절하다.

- **기술 정리**
    - 예약 인스턴스
        - 온디멘드 인스턴스에 비해 최대 72% 할인을 제공하고 있다.
        - 나머지는 온디맨드 및 스팟 인스턴스를 사용해야 한다.
        - 애플리케이션은 Stateless한 상태이기 때문에 **Spot Instance**를 사용할 수 있어 비용을 최소화 할 수 있다.
        - On-Demand는 Spot 인스턴스를 사용할 수 없거나 가격이 과도할 때 사용할 수 있다.
    - [Amazon EC2 예약 인스턴스](https://aws.amazon.com/ko/ec2/pricing/reserved-instances/)

---

- 상황
    - 주 5일 밤 4시간 동안 재무 데이터에 대한 분석을 실행하는 시스템을 만들고있다.
    - 분석은 동일한 기간 동안 실행될 것으로 예상되며 시작되면 중단할 수 없다.
    - 시스템은 최소 1년 동안 필요로 하고 있을 때 어떤 유형의 EC2 인스턴스를 사용해야 할까?

- **답변**
    - 정기 예약 인스턴스
    - 정기 예약 인스턴스를 사용하는 경우 1년이라는 기간 동안 지정된 시작 시간과 기간으로 매일, 매주 또는 매월 반복되는 용량을 예약 구매가 가능하다.
    - 다만, 인스턴스를 사용하지 않더라도 인스턴스가 예약된 시간에 대해 비용을 지불해야 한다.

- **기술정리**
    - 정기 예약 인스턴스
        - 지속적으로 실행되지는 않지만 정기적으로 실행되는 워크로드에 적합한 선택이다.
        - 보통 업무 시간동안 실행되는 애플리케이션이나 주말에 실행되는 배치작업에 정기 인스턴스를 사용할 수 있다.
    - [Scheduled Reserved Instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-scheduled-instances.html)

---

- 상황
    - EC2 인스턴스는 월요일 ~ 금요일 오전 9시 ~ 오후 5시 사이에 실행된다.
    - 프로덕션 인스턴스는 연중무휴로 실행된다.
    - 이러한 요구사항이 주어졌을 때 어떤 모델을 사용해야 할까?

- 답변
    - 개발 환경을 위한 Scheduled reserved instances
    - 프로덕션 환경을 위한 Reserved instances

- 기술 정리
    - 정기 인스턴스는 지속적으로 실행되지 않지만 정기적으로 실행되는 워크로드에 적합하며 개발 환경에 이상적이다.
    - 예약 인스턴스는 지속적으로 실행되는 워크로드에 적합하며 프로덕션 환경에 적합하다.
    - [Instance purchasing options](https://docs.aws.amazon.com/AWSEC2/latest/WindowsGuide/instance-purchasing-options.html)
  

> **DynamoDB**

- 상황
    - FrontEnd에서 API Gateway API를 사용할 새로운 서비스를 설계하려 한다.
    - 서비스는 키-값으로 요청하는 방식으로 백엔드 데이터베이스에 데이터를 유지해야 한다.
    - 처음에 데이터의 요구 사항은 1GB이나 향후 성장은 알 수가 없다.
    - 요청 범위는 초당 0 ~ 800개 이상이다. 이러한 상황에서 선택할 수 있는 AWS 서비스 조합은 무엇일까?

- **답변**
    - AWS Lambda, Amazon DynamoDB
    - **Lambda**는 계산을 수행하고, **DynamoDB** 테이블에 데이터를 저장할수 있다.
    - Lambda는 수요를 충족할 수 있도록 동시 실행을 쉽게 확장할 수 있다.
    - DynamoDB는 키-값 데이터 스토리지 요구 사항에 맞게 구축되어 있어 서버리스 및 쉽게 확장이 가능하다.
    - 이러한 조합은 예측할 수 없는 워크로드에 대한 비용 효율적인 솔루션이다.

- **기술 정리**
    - [Amazon DynamoDB](https://aws.amazon.com/ko/dynamodb/)

> **AMI**

- 상황
    - 애플리케이션이 단일 리전의 EC2 인스턴스에서 실행되고 있다.
    - 해당 인스턴스에 문제가 발생하여 리소스를 두 번째 지역에도 배포할 수 있는 지 확인해야 하는 경우 어떠한 조합으로 조치를 취해야 할까?

- **답변**
    - 두번 째 리전의 Amazon Machine Image(AMI)에서 새 EC2 인스턴스를 시작한다.
    - EC2 인스턴스의 AMI를 복사하고 대상의 두 번째 리전을 지정한다.
    - 복사된 AMI를 사용하여 두 번째 리전의 동일한 EBS 볼륨에서 인스턴스를 시작할 수 있다.
    - AMI는 S3에 저장되긴하나 S3 관리 콘솔에서 보거나 S3 API를 사용하는 프로그래밍 방식으로 작업 할 수 없다.

- **기술 정리**
    - **AMI(Amazon Machine Image)**
        - AWS Management Console, AWS CLI 또는 SDK 또는 Amazon EC2 API를 사용하여 AWS 리전 내에서 또는 AWS 리전 간에 AMI를 복사할 수 있다.
        - 위 환경 모두 CopyImage 작업을 지원한다.
    - [Copy an AMI](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/CopyingAMIs.html)

> **EC2 Auto Scaling**

- 상황
    - EC2 인스턴스에서 애플리케이션을 실행하기 위한 인프라를 설계하려할 때, 애플리케이션은 고가용성을 필요로 하며 비용 효율성을 위해 수요에 따라 동적으로 확장되어야 할 때 사용할 수 있는 솔루션은?

- **답변**
    - EC2 기반 애플리케이션은 가용성이 높고 탄력적으로 확장이 가능해야 한다.
    - Auto Scaling은 수요에 따라 인스턴스를 동적으로 시작하고 종료하여 탄력성을 제공할 수 있다.
    - 이는 고가용성을 위해 가용 영역에서 발생할 수 있다.
    - 들어오는 연결을 ALB를 사용하여 인스턴스에 배포할 수 있다.
    - 따라서 여러 가용 영역에 인스턴스를 배포하도록 Auto Scaling 그룹 앞에 Application Load Balancer를 구축할 수 있다.

- **기술 정리**
    - [What is Amazon EC2 Auto Scaling?](https://docs.aws.amazon.com/autoscaling/ec2/userguide/what-is-amazon-ec2-auto-scaling.html)

## 비용 최적화된 네트워크 아키텍처를 설계하는 방법

> **CloudFront**

- 상황
    - 정적 웹 페이지를 사용하여 자선 활동에 대한 정기적인 업데이트를 공유하려고 한다.
    - 전 세계에서 많은 수의 조회수를 생성할 것으로 예상된다.
    - 정적 파일은 Amazon S3 버킷에 저장하고 있을 때 효과적으로 공유하는 방법은?

- 답변
    - S3 버킷을 오리진으로 하여 Amazon CloudFront를 사용하도록 한다.
    - CloudFront를 사용하는 경우 전 세계에 위치하는 파일을 캐시하면 웹 페이지의 성능이 향상된다.

- 기술정리
    - S3에서 호스팅되는 정적 웹 사이트를 제공하기 위해서는 아래와 같은 구성으로 CloudFront 배포를 할 수 있다.
        - 오리진 액세스 ID(OAI)로 액세스가 제한된 오리진으로 REST API 엔드포인트를 사용
        - 익명(공개) 액세스가 허용된 오리진으로 웹사이트 엔드포인트를 사용
        - Referer 헤더에 의해 엑세스가 제한된 원본으로 웹사이트 엔드포인트를 사용
    - [CloudFront를 사용하여 Amazon S3에 호스팅되는 정적 웹 사이트를 제공하려면 어떻게 해야 합니까?](https://aws.amazon.com/ko/premiumsupport/knowledge-center/cloudfront-serve-static-website/)
    