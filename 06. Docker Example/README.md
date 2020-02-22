# Docker Example

## Build Image and Run Container

### JAR 패키징
서비스를 도커로 만들기 위해서 먼저 JAR로 패키징한다.

```
mvn pacakge
```

### Dockerfile 수정
```dockerfile
FROM openjdk:8-jdk-alpine

ADD target/*jar docker-example.jar

ENTRYPOINT ["java", "-jar", "docker-example.jar"]
```

### Build Image
```
docker build . -t docker-example
```

### Run
도커를 데몬으로 실행한다.

```
docker run -d -p8080:8080 docker-example
```

### 로그 확인
실행 중인 도커의 로그가 표시된다.

```
docker logs $CONTAINER_ID -f
```

## Publish Docker

### Login Docker
```
docker login
```

### Build image
```
docker build . -t gglee/docker-example
```

### Push image
```
docker push gglee/docker-example
```

## Docker and Maven

### Docker Maven Plugin
Docker Maven Plugin을 추가해서 Docker 이미지 빌드 작업을  Maven 빌드 작업 중 하나로 만들 수 있다.

```
<plugin>
    <groupId>io.fabric8</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>0.33.0</version>
    <configuration>
        <verbose>true</verbose>
        <images>
            <image>
                <name>gglee/docker-example</name>
                <build>
                    <dockerFileDir>${project.basedir}/src/docker</dockerFileDir>
                    <assembly>
                        <descriptorRef>artifact</descriptorRef>
                    </assembly>
                    <tags>
                        <tag>latest</tag>
                        <tag>${project.version}</tag>
                    </tags>
                </build>
                <run>
                    <ports>
                        <port>8080:8080</port>
                    </ports>
                </run>
            </image>
        </images>
    </configuration>
</plugin>
```

### Build Docker Image and Publish
```
./mvnw package docker:build docker:push
```