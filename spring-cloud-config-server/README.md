# Spring Cloud Config Server

## Config 요청
애플리케이션 실행 하고 나서 <code>http://localhost:8888/application/default</code>을 요청하면 다음과 같이 출력된다.

```
{
"name": "application",
"profiles": [
   "default"
],
"label": null,
"version": null,
"state": null,
"propertySources": [ ]
}
```

## 프로파일 설정해서 Config Server 패키징
```sql
java -jar target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar --spring.cloud.config.profile=production
```