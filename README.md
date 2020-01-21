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