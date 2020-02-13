# Gateway

## Eureka 대시보드
Zuul 구현체 Gateway 서버를 실행하고 나서 Eureka 대시보드에 접속하면 Gateway가 등록된 것을 확인할 수 있다.

![eureka-gateway](https://user-images.githubusercontent.com/43853352/74400271-ca88a080-4e60-11ea-8fa1-09dfbfe05efe.png)

## 애플리케이션 실행 순서
1. spring-cloud-config-server 실행
2. spring-cloud-discovery-server 실행
3. spring-cloud-microservice 실행
4. spring-cloud-gateway 실행