# description: AWS Certified Solutions Architect
---

# Question

## 단순한 질문들에 대한 답변

> 데이터를 **Amazon S3 bucket**에 저장하는 경우, 실수로 삭제하는 경우 데이터 복구(recovered)하는 방법?

- **Amazon S3 versioning**
	- 객체를 versioning 하게 되면 동일한 s3 버킷에 여러 버전을 유지할 수 있다.
	- 버전 관리 기능은 의도하지 않은 사용자 작업과 애플리케이션 장애로부터 모두 복구할 수 있는 기능을 제공한다.
	- 버전 관리를 사용하면 s3 버킷에 저장된 모든 개체의 모든 버전에 대해서 preserve, retrieve, and restore 가 가능하다.

- 헷갈릴 수 있는 내용
	- `Amazon S3 Intelligent-Tiering`
		- 사용 패턴에 따라 빈번한 액세스와 간헐적인 액세스 클래스 사이에서 데이터를 자동으로 이동하는 스토리지
		- 데이터 복구와는 다른 용도
	- `Amazon S3 lifecycle policy`
		- s3 라이프사이클 정책은 개체를 다른 스토리지 클래스로 전환하는 것과 같이 s3 개체 그룹에 적용되는 작업을 정의하는 규칙 집합이다.
	- `Amazon S3 cross-Region replication`
		- 개체를 다른 영역에 복사하는 데 사용되므로 올바른 내용이 아니다.
		- CRR은 실수로 삭제되는 것을 방지하기 위해 필요한 기능인 **versioning**에 의존하는 기능이다.

- [참고](https://d0.awsstatic.com/whitepapers/protecting-s3-against-object-deletion.pdf)

> **on-premises 인프라**에서 **AWS 클라우드**로 마이그레이션 하려는 상황이다.
> 애플리케이션 중 하나가 데이터를 동기화하기 위해 DFSR(분산 파일 시스템 복제)을 사용하는 **windows 파일 서버 farm**을 사용하고 있었는데
> AWS로 마이그레이션 할때 어떤 서비스를 이용해야 할까?

- **Amazon FSx**
	- 윈도우 파일 서버용인 FSx는 업계 표준 SMB 프로토콜을 통해 액세스할 수 있는 신뢰성 높은 파일 스토리지를 제공한다.
	- 윈도우 서버를 기반으로 구축되어 있으며, `end-user file restore`, `user quotas`, and `Access Control Lists` (ACLs) 등을 제공한다.
	- 윈도우 파일 서버용 Amazon FSX는 Single-AZ 및 Multi-AZ 배포 모두에서 DFSR(분산 파일 시스템 복제)을 지원한다.

- 헷갈릴 수 있는 내용
	- `Amazon EFS`
		- EFS는 리눅스 시스템만 지원한다.
	- `Amazon S3`
		- Microsoft 파일 시스템에 적합한 대체품이 아니다.
	- `AWS Storage Gateway`
		- 사내 스토리지를 클라우드 스토리지에 연결하는데 주로 사용하기 위함이다.
		- 사내에 설치된 소프트웨어 기기로 구성되어 중소기업 공유와 함께 사용할 수 있지만 실제로 데이터를 s3에 저장한다.
		- 다만 파일 서버 팜을 교체해야 하고 아마존 FSx가 이일에 가장 적합하다.

- [참고](https://docs.aws.amazon.com/fsx/latest/WindowsGuide/high-availability-multiAZ.html)

> **Amazon CloudFront** 배포의 origin 역할을 하는 **Application Load Balancer(ALB)** 뒤에 있는 자동 확장 그룹의 Amazon EC2 인스턴스에서 웹사이트가 실행된다.
> **SQL 주입 공격으로부터 보호**하기 위해 **AWS WAF**가 사용되고 있다.
> 보안 로그 검토 결과 웹사이트 접속 차단이 필요한 **외부 악성 IP**가 발견되었다.
> 애플리케이션을 보호하기 위해 무엇을 해야 할까?

- 악성 IP 주소를 차단하는 IP 일치 조건을 추가하도록 AWS WAF 구성 수정하는 방법
	- AWS Web Application Firewall
	- AWS WAF 클래식은 `IP match conditions`을 생성하는 반면, AWS WAF 새 버전에서는 `IP set match statements`을 생성한다.
	- 두 가지 방식 모두 일련의 IP 주소 및 주소 범위에 대해 웹 요청 발신지의 IP 주소를 검사한다.
	- 요청이 원본인 IP주소를 기준으로 웹 요청을 허용하거나 차단하기 위한 옵션이다.
	- AWS WAF는 모든 IPv4 및 IPv6 주소 범위를 지원한다.
	- `IP 세트`는 **확인할 IP 주소** 또는 **IP 주소 범위**를 최대 10,000개까지 보유할 수 있다.

- 헷갈릴 수 있는 내용
	- **CloudFront** 배포에서 **네트워크 ACL**을 수정하여 악성 IP 주소에 대한 거부 규칙 추가
		- CloudFront가 서브넷 내에 있지 않으므로 네트워크 ACL이 적용되지 않는다.
	- ALB 뒤에 있는 대상 그룹의 EC2 인스턴스에 대한 **네트워크 ACL**을 수정하여 악성 IP 주소를 거부하는 방법
		- EC2 인스턴스의 서브넷에 있는 데이터의 소스 IP 주소가 ELB IP 주소가 되므로 올바르지 않다.
	- ALB 뒤에 있는 대상 그룹의 EC2 인스턴스에 대한 **보안 그룹**을 수정하여 악성 IP 주소를 거부하는 방법
		- security group과 함께 거부 규칙을 만들 수 없으므로 올바르지 않다.

- [참고](https://docs.aws.amazon.com/waf/latest/developerguide/waf-rule-statement-type-ipset-match.html)
