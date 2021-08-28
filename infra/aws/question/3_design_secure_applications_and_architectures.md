---
description: AWS Certified Solutions Architect Associate
---

# 보안 애플리케이션 및 아키텍처 설계

## AWS 리소스에 대한 보안 액세스를 설계하는 방법

> **Networking and Content Delivery**

- 상황
    - AWS에서 실행되는 애플리케이션에서 전 세계적으로 배포된 구독자에게 컨텐츠를 제공하고 있다.
    - 애플리케이션은 ALB(Application Load Balancer) 뒤에 private subnet에 있는 EC2 인스턴스 그룹을 사용하고 있다.
    - 저작권 제한 업데이트로 인하여 특정 국가에 대한 접근을 차단하기 위한 방법은?

- 답변
    - CloudFront를 사용하여 애플리케이션을 제공하고, 차단된 국가에 대한 액세스를 거부할 수 있다.
    - CloudFront는 컨텐츠를 제공하는 지리적 제한기능을 구현하는 가장 쉽고 효과적인 방법이다.

- 기술 정리
    - CloudFront
        - 사용자가 컨텐츠를 요청하면 CloudFront는 일반적으로 사용자의 위치에 관계없이 요청된 컨텐츠를 제공한다.
        - 특정 국가의 사용자가 컨텐츠에 액세스하지 못하도록 해야 하는 경우 CloudFront 지역 제한기능을 사용할 수 있다.
        - 지역제한 기능이란?
            - 사용자가 승인된 국가의 허용 목록에 있는 국가에 속한 경우 컨텐츠에 엑세스 할 수 있도록 허용
            - 사용자가 금지된 국가의 블랙리스트에 있는 국가에 속한 경우 사용자가 컨텐츠에 액세스 불가
        - 저작권의 이유로 컨텐츠를 배포할 권한이 없는 국가에서 요청이 온 경우 CloudFront 지역제한을 통해 요청을 차단할 수 있다.
    - [Restricting the geographic distribution of your content](https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/georestrictions.html)

> **VPC-Gateway**

- 상황
    - VPC에는 여러 EC2 인스턴스가 포함되어 있다.
    - 해당 인스턴스는 DynamoDB에 대한 API 호출을 수행해야 하는 상황에서 API 호출이 인터넷을 통과하지 않도록 하길 원할 때 선택할 수 있는 솔루션은?

- **답변**
    - 엔드포인트에 대한 라우팅 테이블 리스트를 생성, DynamoDB용 게이트웨이 엔드포인트를 생성
    - DynamoDB 및 S3는 인터페이스 엔드포인트가 아니라 게이트웨이 엔드포인트를 지원한다.
    - 게이트웨이 엔드포인트를 사용하여 VPC에 엔드포인트를 생성하고 서비스에 대한 엑세스를 허용하는 정책을 연결한 다음 라우팅 테이블 리스트를 생성할 라우팅 테이블을 지정한다.

- **기술 정리**
    - [Gateway VPC endpoints](https://docs.aws.amazon.com/vpc/latest/privatelink/vpce-gateway.html)

## 보안 애플리케이션 계층을 설계하는 방법

> **IAM**

- 상황
    - EC2 생성 시에 특정 ECS 컨테이너 인스턴스에서 실행되는 애플리케이션에는 DynamoDB에 데이터를 쓸 수 있는 권한을 주기 위한 방법은?

- 답변
    - 특정 ECS 작업에만 권한을 할당하기 위해서는 해당 작업에 대한 IAM 역할을 사용해야 한다.
    - DynamoDB에 대한 권한이 있는 IAM 정책을 생성하고 taskRoleArn 파라미터를 사용하여 작업을 할당한다.

- 기술 정리
    - IAM
        - 권한 정책은 작업 정의를 생성할 때 또는 AWS CLI 또는 SDK를 사용하여 IAM 작업 역할 재정의를 사용하여 작업에 적용할 수 있다.
        - taskRoleArn 매개변수는 정책을 지정하는데 사용된다.
    - [IAM Roles for Tasks](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/task-iam-roles.html)

## 적절한 데이터 보안 옵션을 선택하는 방법