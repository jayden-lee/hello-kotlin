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

<hr/>

# Kotlin Best Practice

## DTO 생성

```kotlin
data class Customer(val name: String, val email: String)
```