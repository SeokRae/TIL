# Spring Batch Schema

## Intro

![JobRepository Schema](/batch-study/img/BATCH_JOB_EXECUTION.png)

- 스키마의 시작점 BATCH_JOB_INSTANCE으로 잡을 식별하는 고유 정보가 포함된 잡 파라미터로 
  잡을 처음 실행하면 단일 JobInstance 레코드가 테이블에 등록된다.
  
## Batch_JOB_INSTANCE

|필드|설명|
|:---:|:---:|
|JOB_EXECUTION_ID|테이블의 기본 키|
|VERSION|낙관적인 락(optimistic locking)에 사용되는 레코드 버전|
|JOB_NAME|실행된 잡의 이름|
|JOB_KEY|잡 이름과 잡 파라미터의 해시 값으로, JobInstance를 고유하게 식별하는 데 사용되는 값|

## BATCH_JOB_EXECUTION

- BATCH_JOB_EXECUTION 테이블은 배치 잡의 실제 실행 기록을 나타낸다.
- 잡이 실행될 때마다 새 레코드가 해당 테이블에 생성되고, 잡이 진행되는 동안 주기적으로 업데이트 된다.

|필드|설명|
|:---:|:---:|
|JOB_EXECUTION_ID|테이블의 기본 키|
|VERSION|낙관적인 락에 사용되는 레코드의 버전|
|JOB_INSTANCE_ID|BATCH_JOB_INSTANCE 테이블을 참조하는 외래 키|
|CREATE_TIME|레코드가 생성된 시간|
|START_TIME|잡 실행이 시작된 시간|
|END_TIME|잡 실행이 완료된 시간|
|STATUS|잡 실행의 배치 상태|
|EXIT_CODE|잡 실행의 종료 코드|
|EXIT_MESSAGE|EXIT_CODE와 관련된 메시지나 스택 트레이스|
|LAST_UPDATED|레코드가 마지막으로 갱신된 시간|

- BATCH_JOB_EXECUTION 테이블과 연관이 있는 세 개의 테이블이 존재한다.
- BATCH_JOB_EXECUTION_CONTEXT 테이블은 retry를 위해 ExecutionContext를 사용하기 위한 정보를 갖고 있다.

### BATCH_JOB_EXECUTION_CONTEXT

|필드|설명|
|:---:|:---:|
|JOB_EXECUTION_ID|테이블의 기본 키|
|SHORT_CONTEXT|트림 처리된 SERIALIZED_CONTEXT|
|SERIALIZED_CONTEXT|직렬화된 ExecutionContext|

### BATCH_JOB_EXECUTION_PARAMS

- 잡이 매번 실행될 때마다 사용된 잡 파라미터를 저장한다.
- 앞서 잡의 식별 정보가 담긴 잡 파라미터를 사용해 새 JobInstance가 필요한지 결정하는 방법을 살펴봤다.
- 실제로는 잡에 전달된 모든 파라미터가 테이블에 저장된다.
- 재시작시에는 잡의 식별 정보 파라미터만 자동으로 전달된다.

|필드|설명|
|:---:|:---:|
|JOB_EXECUTION_ID|테이블의 기본 키|
|TYPE_CODE|파라미터 갑의 타입을 나타내는 문자열|
|KEY_NAME|파라미터의 이름|
|STRING_VAL|타입이 String인 경우 파라미터의 값|
|DATE_VAL|타입이 Date인 경우 파라미터의 값|
|LONG_VAL|타입이 Long인 경우 파라미터의 값|
|DOUBLE_VAL|타입이 Double인 경우 파라미터의 값|
|IDENTIFYING|파라미터가 식별되는지 여부를 나타내는 플래그|

- 잡의 메타데이터 정의에 사용되는 이러한 테이블 이외에 JobRepository 내에 테입르 두 개를 추가로 사용한다.
- 이 두 테이블에는 스텝의 메타데이터를 저장한다.

### BATCH_STEP_EXECUTION

- BATCH_STEP_EXECUTION 테이블에는 스텝의 시작, 완료, 상태에 대한 메타데이터를저장한다. 
- 스텝 분석이 가능하도록 다양한 횟수 값을 추가로 저장한다.
- 읽기(read)횟수, 처리(process)횟수, 쓰기(write)횟수, 건너뛰기(skip)횟수 등과 같은 모든 데이터가 저장 된다.

|필드|설명|
|:---:|:---:|
|STEP_EXECUTION_ID|테이블의 기본 키|
|VERSION|낙관적인 락(optimistic locking)에 사용되는 레코드의 버전|
|STEP_NAME|스텝의 이름|
|JOB_EXECUTION_ID|BATCH_JOB_EXECUTION 테이블을 참조하는 외래 키|
|START_TIME|스탭 실행이 시작된 시간|
|END_TIME|스탭 실행이 완료된 시간|
|STATUS|스탭의 배치 상태|
|COMMIT_COUNT|스탭 실행 중에 커밋된 트랜잭션 수|
|READ_COUNT|읽은 아이템 수|
|FILTER_COUNT|아이템 프로세서가 null을 반환해 필터링 된 아이템 |
|WRITE_COUNT|기록된 아이템 수|
|READ_SKIP_COUNT|ItemReader 내에서 예외가 던져졌을 때 건너뛴 아이템 수|
|PROCESS_SKIP_COUNT|ItemProcessor 내에서 예외가 던져졌을 때 건너뜅 아이템 수|
|WRITE_SKIP_COUNT|ItemWriter 내에서 예외가 던져졌을 때 건너뜅 아이템 수|
|ROLLBACK_COUNT|스탭 실행에서 롤백된 트랜잭션 수|
|EXIT_CODE|스탭의 종료 코드|
|EXIT_MESSAGE|스탭 실행에서 반환된 메시지나 스택 트레이스|
|LAST_UPDATED|레코드가 마지막으로 업데이트된 시간|

### BATCH_STEP_EXECUTION_CONTEXT

- 컴포넌트의 상태 저장에 사용되는 ExecutionContext가 JobExecution 내에 존재하는 것처럼, StepExecution에도 동일한 목적으로 사용되는 ExecutionContext가 존재한다.
- StepExecution의 ExecutionContext는 스탭 수준에서 컴포넌트 상태를 저장하는데 사용된다.

|필드|설명|
|:---:|:---:|
|STEP_EXECUTION_ID|테이블의 기본 키|
|SHORT_CONTEXT|트림 처리된 SERIALIZED_CONTEXT|
|SERIALIZED_CONTEXT|직렬화된 ExecutionContext|
