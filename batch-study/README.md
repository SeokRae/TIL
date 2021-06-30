# Spring Batch

* [Github](https://github.com/spring-org/springbatch-in-action)

## 심플 배치 작성기

- [배치용 디비 설치](/batch-study/contents/_1.md)

## 스터디 진행 방식

1. 스프링 배치 `기본 개념` 학습
2. 전체적 배치 프로세스를 이해하기 위한 `도메인` 선택 및 세 가지 이상 기능이 추가된 `프로세스 설계`
3. 프로세스 기능에 필요한 `Reader`, `Processor`, `Writer` 또는 `Tasklet`으로 구현
	* FlatFileReader, JdbcCursorItemReader, JpaPagingItemReader
	* ItemProcessor
	* JpaItemWriter, CompositeItemWriter, ExcelItemWriter
	* MySQL, H2, Oracle
4. 프로세스 구현 시 중복되는 기능 및 프로세스 개선\(필요시 Redis 등등 추가\)
5. 스케줄러에 대한 종류 찾아보기\(Jenkins, Airflow, custom 등등\)

## SpringBatch Schema ERD

![erd](../.gitbook/assets/springbatch_schema_erd.png)

- [batch-schema](/batch-study/batch-schema.md)

## 추가적 진행

* [x] txt 파일 reader 기능 추가 \(csv, txt, excel\)
* [x] excel Report 모듈 참고 구현
* [x] 배치 프로세스상에서 Step 간의 `데이터 공유`하는 방법 개선\(외부 모듈 사용 X\)
* [x] 속도를 줄이기 위해 Parallel Step or MultiThread로 구현해보기
* [x] JPA Querydsl를 활용하여 조인쿼리로 수정하기
* [x] JPA, Jdbc 속도 비교 \(속도를 어느정도 포기하고 코드양을 줄일 것인지..?\)
* [ ] 엘라스틱 서치 스택을 추가하여 배치와 연동
