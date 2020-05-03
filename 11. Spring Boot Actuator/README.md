# Spring Boot Actuator
> Actuator는 스프링 부트 애플리케이션의 운영 및 모니터링을 위해서 헬스 체크, 감사, 매트릭 추적 등 기능을 제공한다.

## Properties 설정
```yaml
server:
  port: 8080

info:
  app:
    name: Acutator App
    java:
      source: 1.8
      target: 1.8

management:
  server:
    port: 9090
  endpoint:
    health:
      show-details: always
  endpoints:
    web:
      exposure:
        include: health, info, metrics, env
```

## Endpoint
### Health
- `/actuator/health`

```json
{
   "status":"UP",
   "components":{
      "db":{
         "status":"UP",
         "details":{
            "database":"H2",
            "result":1,
            "validationQuery":"SELECT 1"
         }
      },
      "diskSpace":{
         "status":"UP",
         "details":{
            "total":250685575168,
            "free":104563916800,
            "threshold":10485760
         }
      },
      "ping":{
         "status":"UP"
      }
   }
}
```

### Info
- `/actuator/info`

```json
{
   "app":{
      "name":"Acutator App",
      "java":{
         "source":1.8,
         "target":1.8
      }
   }
}
```

### metrics
- `/actuator/metrics`
    - 지원하는 모든 metric 이름을 반환한다

```json
{
   "names":[
      "jvm.memory.max",
      "jvm.threads.states",
      "jdbc.connections.active",
      "process.files.max",
      "jvm.gc.memory.promoted",
      "system.load.average.1m",
      "jvm.memory.used",
      "jvm.gc.max.data.size",
      "jdbc.connections.max",
      "jdbc.connections.min"
   ]
}
```

- `/actuator/metrics/${metric_name}`
    - metric 상세정보를 반환한다

```json
{
   "name":"jvm.memory.used",
   "description":"The amount of used memory",
   "baseUnit":"bytes",
   "measurements":[
      {
         "statistic":"VALUE",
         "value":198696104
      }
   ],
   "availableTags":[
      {
         "tag":"area",
         "values":[
            "heap",
            "nonheap"
         ]
      },
      {
         "tag":"id",
         "values":[
            "Compressed Class Space",
            "PS Survivor Space",
            "PS Old Gen",
            "Metaspace",
            "PS Eden Space",
            "Code Cache"
         ]
      }
   ]
}
```

### environment
- `/actuator/env`
```json
{
   "activeProfiles":[
   ],
   "propertySources":[
      {
         "name":"server.ports",
         "properties":{
            "local.server.port":{
               "value":8080
            },
            "local.management.port":{
               "value":9090
            }
         }
      },
      {
         "name":"systemProperties",
         "properties":{
            "java.runtime.name":{
               "value":"OpenJDK Runtime Environment"
            },
            "spring.output.ansi.enabled":{
               "value":"always"
            },
            "sun.boot.library.path":{
               "value":"/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib"
            },
            "java.vm.version":{
               "value":"25.222-b10"
            },
            "gopherProxySet":{
               "value":"false"
            },
            "java.vm.vendor":{
               "value":"AdoptOpenJDK"
            },
            "java.vendor.url":{
               "value":"http://java.oracle.com/"
            },
            "java.rmi.server.randomIDs":{
               "value":"true"
            }
         }
      }
   ]
}
```