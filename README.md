# Spring BATCH 3.0

예제 코드는 spring 3.x 버전 기준으로 작성 하였다.

ConditionalOnProperty annotation을 사용하여 사용할 배치만 bean으로 등록해서 실행 하는 구조로 되어 있다.
원하는 배치는 아래 처럼 호출 해서 사용할 수 있다.
```shell
java -jar .\batch-0.0.1.jar --spring.batch.job.name=example.plugin
```


# hello
Tasklet 를 이용해서 간단하게 문구를 LOG로 남기는 샘플 배치


# plugin & strategy
플러그인 기반 아키텍처 와 전략 패턴을 이용해서 특정 동작을 실행 하는 샘플 배치

# database
두개의 database를 사용해서 database A에서 데이터를 조회 해서 database  B에 insert 하는 샘플 배치






