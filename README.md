신규 입사자 토이프로젝트
--
1. 목표 
    - SpringBoot 익히기
    - JPA 익히기
    - DDD를 기반으로 미니 라스트마일 이해하기
    - AWS에 배포해보기 ? 
    
2. 환경
    - JDK 1.8
    - SpringBoot 2.2.0
    - Gradle version 4.10.2
    
3. 구조
   
   |Layer|Module|설명|
   |-----|----|----|
   |Presentation Layer|vroong-newbee-apiapplication|controller|
   |Application Layer|vroong-newbee-application|알맞는 도메인에 로직 수행 위임|
   |Domain Layer|vroong-newbee-domain|도메인 핵심 로직 구현|
   |Infrastructure Layer|vroong-newbee-infrastructure|DB, AWS 등등 연동.. |
   
4. api 목록
    i. 팀의 새로운 url사용정책 따라기 "/서비스/리소스/버전/명사"
     
     |라스트마일|뉴비|용도|
     |---|---|---|
     |/manager/order/createOrder|/manager/order/v1/create|오더생성|
     |/manager/order/assignAgent|/manager/order/v1/assignment|오더배정|
     |/manager/order/unAssignAgent|/manager/order/v1/release/assignment|오더배정취소|
     |/manager/order/pickUpOrder|/manager/order/v1/pickup|오더픽업|
     /manager/order/unAssignAgent|/manager/order/v1/release/pickup|오더배정취소|
     |/manager/order/cancelOrder|/manager/order/v1/cancel|오더취소|
     
     
5. 변경해보고 싶었던 부분

    1. @Autowire 말고 생성자 주입으로 변경 
        이유: 환참조 방지, 그외 좋은 점은 .. 순
    2. api restful 하게
    3. 

6. 깨달은점
    1. gradle 빌드하면 진입부분이 public static void main(String[]) 을 본다는것,
    2. 스프링버전이 2.x 대로 올라가면 SpringBoot{mainClassName = ''} 이 된다.