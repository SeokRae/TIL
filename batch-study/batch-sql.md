## Spring Batch Job 조회하기

- Job 전체 조회

```sql
SELECT *
FROM BATCH_JOB_EXECUTION
WHERE 1 = 1
ORDER BY JOB_EXECUTION_ID DESC;
```

- JobName으로 실행된 JobInstance 횟수 조회

```sql
SELECT A.JOB_NAME
     , COUNT(1) AS 'instance count of jobName'
FROM BATCH_JOB_INSTANCE A
WHERE 1 = 1
  AND JOB_NAME = 'TOTAL_PROCESS_JOB'
GROUP BY A.JOB_NAME
;
```

- JobName으로 실행된 JobInstance 조회하기

```sql
SELECT JOB_INSTANCE_ID
     , JOB_NAME
     , VERSION
FROM BATCH_JOB_INSTANCE
WHERE 1 = 1
  AND JOB_NAME = 'FLOW_H_DbToDb_JOB'
ORDER BY JOB_INSTANCE_ID DESC
;
```

- JobName으로 실행된 instance와 ExecutionContext 확인

- 잡을 실행할 때 생성하는 각 JobExexution은 BATCH_JOB_EXECUTION 테이블의 레코드로 저장된다.
- JobExecution이 실행될 때의 상태는 BATCH_JOB_EXECUTION_CONTEXT 테이블에 저장된다.

```sql
SELECT B.JOB_INSTANCE_ID
     , B.VERSION
     , JOB_NAME
     , JOB_KEY
     , A.JOB_EXECUTION_ID
     , CREATE_TIME
     , START_TIME
     , END_TIME
     , STATUS
     , EXIT_CODE
     , EXIT_MESSAGE
     , LAST_UPDATED
     , JOB_CONFIGURATION_LOCATION
FROM BATCH_JOB_EXECUTION A
         JOIN BATCH_JOB_INSTANCE B
              ON A.JOB_INSTANCE_ID = B.JOB_INSTANCE_ID
WHERE 1 = 1
  AND B.JOB_NAME = 'AREA_JOB'
```

- JOB_NAME으로 실행된 JOB_INSTANCE로 실행된 JOB_EXECUTION을 조회

```sql
SELECT JOB_EXECUTION_ID
     , VERSION
     , JOB_INSTANCE_ID
     , CREATE_TIME
     , START_TIME
     , END_TIME
     , STATUS    -- COMPLETED, FAILED
     , EXIT_CODE -- COMPLETED, FAILED
     , EXIT_MESSAGE
     , LAST_UPDATED
     , JOB_CONFIGURATION_LOCATION
FROM BATCH_JOB_EXECUTION B
         JOIN BATCH_JOB_INSTANCE A
              ON B.JOB_INSTANCE_ID = A.JOB_INSTANCE_ID
WHERE 1 = 1
  AND A.JOB_NAME = 'testJob'
ORDER BY A.JOB_INSTANCE_ID DESC, B.LAST_UPDATED DESC
;
```

- 실행된 JOB_INSTANCE의 파라미터를 확인하기 위한 쿼리

```sql
SELECT B.JOB_EXECUTION_ID
     , GROUP_CONCAT(C.KEY_NAME SEPARATOR ', ') AS PARAMS
FROM BATCH_JOB_EXECUTION_PARAMS C
         JOIN BATCH_JOB_EXECUTION B
              ON C.JOB_EXECUTION_ID = B.JOB_EXECUTION_ID
         JOIN BATCH_JOB_INSTANCE A
              ON A.JOB_INSTANCE_ID = B.JOB_INSTANCE_ID
WHERE 1 = 1
  AND A.JOB_NAME = 'AREA_JOB'
GROUP BY B.JOB_EXECUTION_ID DESC
ORDER BY B.JOB_EXECUTION_ID DESC
;
```

- JOB_INSTANCE에 대한 JOB_EXECUTION 실행 정보 확인

```sql
SELECT B.JOB_INSTANCE_ID
     , B.JOB_NAME
     , COUNT(1) AS 'JOB_INSTANCE_ID 당 EXECUTE 건수'
FROM BATCH_JOB_EXECUTION A
         JOIN BATCH_JOB_INSTANCE B
              ON A.JOB_INSTANCE_ID = B.JOB_INSTANCE_ID
WHERE 1 = 1
  AND B.JOB_NAME = 'AREA_JOB'
GROUP BY JOB_INSTANCE_ID, JOB_NAME
;
```

## STEP

- Step 정보 조회

```sql

SELECT D.JOB_INSTANCE_ID /* JOB 실행시 마다 생성되는 JobInstanceId    */
     , D.JOB_EXECUTION_ID /* Job Instance에 따른 실행 Id             */
     , E.STEP_EXECUTION_ID /* JobExecutionId에 따른 StepExecutionId  */
     , D.JOB_NAME /* Job 실행시 파라미터 job.name             */
     , E.STEP_NAME /* STEP 실행 NAME                        */
FROM (
         /* 파라미터 테이블과 조인 시 version, job.name, increment 값 설정 수 마다 row 추가 되기 때문에 group by로 jobInstanceId에 따른 jobExecutionId를 확인 */
         SELECT A.JOB_INSTANCE_ID
              , A.JOB_NAME
              , B.JOB_EXECUTION_ID
         FROM BATCH_JOB_INSTANCE A
                  JOIN BATCH_JOB_EXECUTION B
                       ON A.JOB_INSTANCE_ID = B.JOB_INSTANCE_ID
                  JOIN BATCH_JOB_EXECUTION_PARAMS C
                       ON B.JOB_EXECUTION_ID = C.JOB_EXECUTION_ID
         WHERE 1 = 1
           AND A.JOB_NAME = 'AREA_JOB'
         GROUP BY A.JOB_INSTANCE_ID, A.JOB_NAME, B.JOB_EXECUTION_ID
     ) D
         JOIN BATCH_STEP_EXECUTION E
              ON E.JOB_EXECUTION_ID = D.JOB_EXECUTION_ID
ORDER BY START_TIME DESC
;
```
