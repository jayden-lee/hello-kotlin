# Microservice

## 스프링 클라우드
벤더 독립적인 접근 방식을 장점으로 클라우드 네이티브 마이크로서비스를 쉽게 만들 수 있는 프레임워크를 제공한다.

주요 컴포넌트

- Config Server
- Service Discovery
- Gateway
- Circuit Breaker

### Config Server
Config Server는 설정 값을 검색할 수 있는 기능을 마이크로서비스에 제공한다. 예를 들어 상품, 결제 두 개의 마이크로서비스 인스턴스를 시작할 때, Config Server에 설정 값을 요청해서 받아와서 사용한다.

Config Server는 설정 값을 별도의 저장소(Git, SVN 등)에 보관하고 이를 가져와서 사용할 수 있도록 구현할 수도 있다.

#### 읽어볼 거리
- [Spring Cloud Config Server에 관하여 알아봅시다.](http://blog.leekyoungil.com/?p=352)

### Service Discovery
마이크로서비스들은 생성되면 시작과 함께 Service Discovery Server에 등록된다. 반대로 마이크로서비스가 종료가 되는 경우에는 Service Discovery Server의 등록된 목록에서 제외된다. 만약 마이크로서비스가 비정상 종료로 인해 Service Discovery Server에 알리지 못하더라도 Heart-beat 매커니즘을 통해 동기화 체크를 한다.

A 마이크크서비스가 B 마이크로서비스에 요청을 보내야 하는 경우에 Service Discovery Server로부터 B 마이크로서비스의 인스턴스 목록을 받아 올 수 있다. 인스턴스 목록 중 하나를 선택해서 필요한 요청을 보낼 수 있으며, 부하를 막기 위해 내부적으로 로드밸런서(라운드 로빈) 패턴을 사용한다.

#### 읽어볼 거리
- [(Spring Boot / Spring Cloud / MSA) 3. Eureka란? / 적용방법](https://lion-king.tistory.com/12)

### Gateway
애플리케이션에서 사용할 마이크로서비스를 노출하는 경우에 Gateway 패턴을 사용해서 마이크로서비스의 엔드포인트를 제공할 수 있다. 마치 프록시 서버처럼 동작하며 인증, 권한, 모니터링, 로깅 등 추가적인 기능도 수행할 수 있다. 또한, 내부적으로 외부 클라이언트 요청에 따라 적합한 마이크로서비스를 탐색하고 로드 밸런스를 수행한다.

### Circuit Breaker
마이크로서비스 아키텍처에서는 여러 마이크로서비스가 서로간에 통신하게 된다. 이 때, 특정 마이크로서비스가 장애가 났는데 해당 마이크로서비스를 의존하고 있는 서비스들도 장애가 전파되어서 서비스를 못하게 되는 상황이 발생할 수 있다.

Circuit Breaker 패턴을 사용해서 특정 마이크로서비스가 장애가 발생하면 해당 서비스의 응답을 기다리거나 장애를 전파 받지 않기 위해서 예외 처리를 할 수 있다. 이렇게 하면 애플리케이션은 장애 없이 정상적으로 서비스를 운영할 수 있는 장점을 얻을 수 있다. 이러한 과정을 Circuit breaker가 OPEN 상태가 되었다고 한다.

특정 시간이 지나고 나면 Circuit Breaker가 장애가 발생한 마이크로서비스의 상태를 파악하고 CLOSE 상태로 변경하게 된다.

#### 읽어볼 거리
- [Circuit Breaker Pattern](https://brunch.co.kr/@springboot/262)

## 스프링 클라우드 넷플릭스 (Netflix OSS)

- Eureka : 마이크로서비스 인스턴스 등록, 해제, 검색할 수 있는 Service Discovery
- Ribbon : Eureka와 통합해서 마이크로서비스 인스턴스 내에서 호출을 분산할 수 있는 구성 가능한 소프트웨어 로드 밸런서
- Hystrix : 마이크로서비스를 만들 때 사용할 수 있는 대체 매커니즘이 있는 Circuit Breaker
- Zull : Eureka, Ribbon, Hystrix을 사용해서 Gateway 패턴을 구현한 Gateway 서버

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
<code>http://localhost:8081/actuator/health</code>에 접속해서 상태 정보를 확인할 수 있다.

```json
{
  "status": "UP"
}
```