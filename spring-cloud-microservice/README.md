# Microservice

## Discovery Server에 연결

### Eureka Client 모듈 추가
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

## 애플리케이션 실행 순서
1. spring-cloud-config-server 실행
2. spring-cloud-discovery-server 실행
3. spring-cloud-microservice 실행

위 순서대로 실행하고 나서 <code>http://localhost:8761</code> 대시보드에 접속하면, Eureka에 서버가 등록된 것을 확인할 수 있다.

![eureka-client-register](https://user-images.githubusercontent.com/43853352/74399216-6f08e380-4e5d-11ea-94fd-307d3dd16946.png)

## Spring Boot Actuator
스프링 컨텍스트를 탐색하고 애플리케이션 상태 정보를 제공하는 기능을 제공한다.

### 모듈 추가
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### 상태 정보
<code>http://localhost:8080/actuator/health</code>에 접속해서 상태 정보를 확인할 수 있다.

```json
{
  "status": "UP"
}
```