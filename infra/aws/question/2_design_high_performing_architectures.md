---
description: AWS Certified Solutions Architect Associate
---

# 고성능 아키텍처 설계

## 탄력적이고 확장이 가능한 솔루션을 식별하는 방법

> **Amazon SQS**

- 상황
    - 컴퓨팅 계층은 EC2 인스턴스에서 병렬로 실행되고 있고, 처리할 작업 수에 따라 확장되어야 한다.
    - 컴퓨팅 계층은 Stateless인 상태이다.
    - 응용 프로그램이 느슨하게 연결되어 있고, 작업 항목이 영구적으로 저장되어 있는지 확인해야 하는 상황에서 어떤 디자인을 사용해야 할까?

- 답변
    - 처리해야 하는 작업을 보관하는 Amazon SQS 대기열을 생성한다.
    - 애플리케이션에 대한 EC2 Auto Scaling 그룹을 생성하여 SQS 대기열의 항목 수에 따라 노드를 추가 및 제거할 수 있도록 Auto Scaling 그룹에 대한 조정 정책을 설정하도록 한다.

- 기술 정리 (어려움)
    - **Amazon SQS**
        - 작업을 저장하기 위해 내구성있고 느슨하게 결합된 솔루션이다.
        - 대기열에서 대기 중인 작업 수를 기반으로 동적으로 조정할 수 있도록 구성할 수 있다.
        - 이런 조정을 구성하기 위해서는 인스턴스당 백로그 지표를 사용하여 유지 관리할 인스턴스당 허용 가능한 백로그를 목표 값으로 사용할 수 있다.
            - **인스턴스당 백로그**를 계산하기 위해서는 ApproximateNumberOfMessages 대기열 속성으로 시작하여 SQS 대기열의 길이(대기열에서 검색할 수 있는 메시지 수)를
              결정한다.
            - 해당 숫자를 집합의 실행 용량(Auto Scaling 그룹의 경우 InService 상태의 인스턴스 수)으로 나누어 인스턴스당 백로그를 얻는다.
            - **인스턴스당 허용 가능한 백로그**는 목표 값을 계산하기 위해서는 먼저 애플리케이션이 대기 시간 측면에서 허용할 수 있는 것을 결정해야 한다.
            - 그런 다음 허용 가능한 지연 시간 값을 가져와 EC2 인스턴스가 메시지를 처리하는 데 걸리는 평균 시간으로 나눈다.
        - Amazon SQS 솔루션은 대기 중인 작업 수를 기반으로 Auto Scaling을 사용하여 EC2 인스턴스를 확장한다.
    - [Scaling based on Amazon SQS](https://docs.aws.amazon.com/autoscaling/ec2/userguide/as-using-sqs-queue.html)

> **Amazon Aurora**

- 상황
    - 날시 업데이트를 제공하는 웹 애플리케이션은 ALB 뒤에 Multi AZ Auto Scaling Group의 EC2 인스턴스 집합에서 실행되고 있다.
    - 인스턴스는 Aurora 데이터베이스에 데이터를 저장하고 있다.
    - 요청 속도가 산발적으로 증가하여 애플리케이션을 보다 탄력적으로 만들 수 있는 솔루션을 찾고자 할 때 알맞은 솔루션은?

- **답변**
    - 아키텍처는 이미 복원력이 높지만 요청 비율이 갑자기 증가하면서 성능이 저하될 수 있다.
    - 이러한 상황을 해결하기 위해서는 Aurora 읽기 전용 복제본을 사용하여 기본 데이터베이스의 요청을 오프로드하는 읽기 트래픽을 제공할 수 있다.
    - FrontEnd 에서 ALB 앞에 CloudFront를 배치할 수 있다.
    - 위와 같은 방법으로 구성하면 컨텐츠를 캐싱하고 백엔드에서 요청을 오프로드할 수 있기 때문에 더 나은 성능을 기대할 수 있다.

- **기술 정리**
    - [Replication with Amazon Aurora](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/Aurora.Replication.html)
    - [What is Amazon CloudFront?](https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/Introduction.html)

> Database Migration

- 상황
    - 웹 애플리케이션의 데이터베이스 계층은 온프레미스 Window 서버에서 실행된다.
    - 데이터베이스는 Microsoft SQL Server 데이터베이스이다.
    - 애플리케이션 소유자가 데이터베이스를 RDS 인스턴스로 마이그레이션 하려고 할 때 최소한의 관리 노력과 가동 중지 시간으로 마이그레이션 하는 방법은?

- 답변
    - AWS Database Migration Service(DMS)를 사용하여 데이터베이스를 RDS로 직접 마이그레이션한다.

- 기술정리
    - Microsoft SQL Server 데이터베이스 엔진을 사용하여 온프레미스 서버에서 Amazon RDS로 Microsoft SQL Server를 직접 마이그레이션할 수 있다.
    - 기본 Microsoft SQL Server 도구를 사용하거나 AWS DMS를 사용하여 이를 수행할 수 있습니다.
    - [Migrate an on-premises Microsoft SQL Server database to Amazon RDS for SQL Server](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/migrate-an-on-premises-microsoft-sql-server-database-to-amazon-rds-for-sql-server.html)
    - [Sources for data migration](https://docs.aws.amazon.com/dms/latest/userguide/CHAP_Source.html)
    - [Targets for data migration](https://docs.aws.amazon.com/dms/latest/userguide/CHAP_Target.html)
    - [AWS Schema Conversion Tool](https://aws.amazon.com/ko/dms/schema-conversion-tool/)

## 확장 가능한 고성능 스토리지를 선택하는 방법

> **Kinesis**

- 상황
    - 매장과 창고가 많은 소매 회사는 IoT 센서를 구현하여 각 위치의 장치에서 모니터링 데이터를 수집한다.
    - 데이터는 실시간으로 AWS에 전송되며, 각 장치에 대해 이벤트를 순서대로 수신하고 향후 처리를 위래 데이터를 저장하는 솔루션을 제공해야 한다.
    - 위 상황에서 어떤 솔루션을 선택하는 것이 가장 효율적일까?

- 답변
    - 각 디바이스의 파티션 키가 있는 실시간 이벤트에 Amazon Kinesis Data Streams를 사용한다.
    - Amazon Kinesis Data Firehose를 사용하여 S3에 데이터를 저장한다.

- 기술정리 (어려움)
    - Amazon Kinesis Data Streams는 실시간으로 데이터를 수집하고 처리한다.
    - Kinesis data stream은 샤드의 집합으로 각 샤드는 스트림에서 고유하게 식별된 데이터 레코드 시퀀스이다.
    - 각 데이터 레코드에는 Kinesis data stream에서 할당한 시퀀스 번호가 있다.
    - 파티션 키 스트림 내에 샤드로 그룹 데이터에 사용된다.
    - Kinesis data stream은 스트림에 속한 데이터 레코드를 여러 샤드로 분리한다.
    - 각 데이터 레코드와 연결된 파티션 키를 사용하여 주어진 데이터 레코드가 속한 샤드를 결정한다.
    - 각 장치에 대해 파티션 키를 사용하여 해당 장치에 대한 레코드가 샤드별로 그룹화되고 샤드 자체가 순서를 보장한다.
    - S3는 데이터 레코드를 저장하기 위한 대상이다.
    - [Amazon Kinesis Data Streams Terminology and Concepts](https://docs.aws.amazon.com/streams/latest/dev/key-concepts.html)

## 고성능 네트워킹 솔루션을 선택하는 방법

## 고성능 데이터베이스 솔루션을 선택하는 방법

> **Aurora**

- 상황
    - 한 보험 회사에 영국과 호주의 사용자에게 서비스를 제공하는 웹 애플리케이션이 있다.
    - 애플리케이션은 `eu-west-2`에서 호스팅되는 데이터베이스 계층에 MySQL 데이터베이스를 사용하고 있다.
    - 웹 계층은 `eu-west-2`와 `ap-southeast-2`에서 실행되고 있다.
    - Route 53은 사용자를 가장 가까운 웹 계층으로 안내하는 지리 근접 라우팅으로 사용된다.
    - 오스트레일리아 사용자의 경우 쿼리에 대한 응답 시간이 느리다는 이슈가 발생했을 때 성능을 향상시키기 위한 데이터베이스 계층에서의 방법은?

- 답변
    - 문제가 발생하는 곳이 읽기 쿼리가 호주에서 영국으로 향하는 대기시간이고, 물리적으로 거리가 멀기 때문에 호주에서 읽기 성능을 향상시키기 위한 솔루션이 필요하다.
    - ap-southeast-2에서 읽기 전용 복제본을 구성하고 MySQL 호환 모드에서 데이터베이스를 Amazon Aurora 글로벌 데이터베이스로 마이그레이션한다.
    - 이러한 작업은 오스트레일리아 지겅의 사용자에게 더 나은 성능을 제공할 수 있다.
    - 쓰기는 여전히 영국 리전에서 이루어져야 하지만 읽기 성능은 크게 향상 될 수 있다.

- 기술 정리
    - **Aurora Global Database**
        - 데이터가 마스터링되는 단일 기본 AWS 리전과 최대 5개의 읽기 전용 보조 AWS 리전으로 구성되어 있다.
        - Aurora는 1초 미만의 일반적인 지연 시간으로 보조 AWS 리전에 데이터를 복제한다.
        - 기본 AWS 리전의 기본 DB 인스턴스에 직접 쓰기 작업을 실행한다.
    - [Using Amazon Aurora global databases](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/aurora-global-database.html)

