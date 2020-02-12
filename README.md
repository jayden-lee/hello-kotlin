# Spring Boot with Kotlin
> Kotlin 언어를 이용해서 간단한 Spring Boot 애플리케이션 만들기

## 스프링 부트 애플리케이션 실행
애플리케이션 패키징 하고 나면, 대상 폴더에 <code>spring-kotlin-0.0.1-SNAPSHOT.jar</code> 파일이 생성된다. 스프링 부트에서는
내장 톰캣을 갖고 있기 때문에 <code>java -jar</code> 명령어로 쉽게 실행할 수 있다.

```shell script
mvnw package
java -jar target/spring-kotlin-0.0.1-SNAPSHOT.jar
``` 

### 실행 가능 JAR 만들기
아래와 같이 <code>executable</code> 옵션을 활성화하면 <code>java -jar</code> 명령어 없이 jar 파일을 실행 가능한 파일로 생성할 수 있다.
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <executable>true</executable>
    </configuration>
</plugin>
```

```shell script
./target/spring-kotlin-0.0.1-SNAPSHOT.jar
```

## 애플리케이션 설정 값 설정 방법
1. application.properties
2. application.yml
3. 명령줄 인수

## 프로파일
프로파일 값에 따라 설정 값을 변경할 수 있다. 아래 프로파일 기본값은 <code>development</code>로 설정되어 있으며,
다른 프로파일로 실행하려면 <code>--spring.profiles.active="production"</code> 인수를 추가하면 된다. 

```yaml
spring:
  profiles:
    active: "development"

---

spring:
  profiles: "development"

---

spring:
  profiles: "production"
```

## 경로 변수
<code>@Pathvariable</code> 어노테이션을 사용해서 URL로 넘어오는 값을 얻을 수 있다.

```kotlin
@GetMapping("/customer/{id}")
fun getCustomer(@PathVariable id: Int) = customers[id]
```
 
## 요청 매개 변수
<code>@RequestParam</code> 어노테이션을 사용해서 매개 변수 값을 얻을 수 있다.

```kotlin
@GetMapping("/customers")
fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String)
        = customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList();
```

## Controller 에러 처리

```kotlin
@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("JSON Error", exception.message ?: "invalid json"), BAD_REQUEST)
    }
}
```

## Reactor Publisher 구현체
- Mono : 0-1개의 데이터를 전달
```kotlin
val customer = Customer(1, "Mono").toMono()
```

- Flux : 0-N개의 데이터를 전달
```kotlin
val customerFlux = listOf(Customer(1, "Customer1"), Customer(2, "Customer2")).toFlux()
```

## RouterFunction
<code>Controller</code> 대신 <code>RouterFunction</code>를 사용해서 서버로 들어오는 요청을 처리할 수 있다.

<code>/functional/customer</code> 경로에 GET 요청을 처리하는 엔드포인트 하나가 선언되어 있다. 응답에 대한 코드는 핸들러
클래스로 따로 빼내서 정의한다.

### CustomerRouter

```kotlin
@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {

    @Bean
    fun customerRoutes() = router {
        "/functional".nest {
            "/customer".nest {
                GET("/", customerHandler::get)
            }
        }
    }
}
``` 

### CustomerHandler

```kotlin
@Component
class CustomerHandler {

    fun get(serverRequest: ServerRequest) =
        ok().body(Customer(1, "Hello World").toMono(), Customer::class.java)

}
```

## 데이터베이스 설치
논블로킹 마이크로서비스가 블로킹 오퍼레이션을 사용해서 데이터를 쿼리를 하게 되면, 리액티브 프로그래밍의 이점을 잃어버린다. 스프링 데이터를 이용해서 
리액티브 작업을 진행하기 전에 먼저 데이터베이스가 설치되어 있어야 한다. 이번 예제 프로젝트에서는 NoSQL 데이터베이스인 몽고 DB를 활용한다. 

### Docker Mongo DB 설치
```shell script
docker run --name mongo -p 27017:27017 -d mongo
```

### Docker Container 확인
```shell script
docker ps
```

### Docker Container 접속
```shell script
docker exec -it mongo bash
```

### Mongo DB 버전 확인
```shell script
mongo
db.version()
```

![mongo-version](https://user-images.githubusercontent.com/43853352/73738727-5a0ac100-4788-11ea-875a-a3a15f60dba8.png)

### Mongo Auth 접속
```shell script
mongo admin -u 'username' -p 'password'
```

### MongoDB Tool
[Compass 설치](https://www.mongodb.com/products/compass)

![mongodb-compass](https://user-images.githubusercontent.com/43853352/73739702-16b15200-478a-11ea-9b58-a724181ed212.png)

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

<hr/>

# Kotlin Best Practice

## DTO 생성

```kotlin
data class Customer(val name: String, val email: String)
```