# Kotlin Querydsl Example

## Setting Gradle
```
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("idea")
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    kotlin("plugin.jpa") version "1.3.61"
    kotlin("kapt") version "1.3.61"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

sourceSets["main"].withConvention(KotlinSourceSet::class) {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("com.h2database:h2")

    compileOnly("com.querydsl:querydsl-jpa:4.2.1")
    kapt("com.querydsl:querydsl-apt:4.2.1:jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
```

## Generate QClasses
<code>build/generated/source/kapt/main</code> 경로에 클래스 파일이 생성된다.

```shell script
./gradlew clean compileKotlin
```

## Example Querydsl
```kotlin
class CustomMemberRepositoryImpl : CustomMemberRepository, QuerydslRepositorySupport(Member::class.java) {

    override fun searchMember(teamName: String?): List<Member> =
        from(member)
            .join(member.team, team)
            .fetchJoin()
            .where(eqTeamName(teamName))
            .fetch()

    private fun eqTeamName(teamName: String?): BooleanExpression? {
        return if (teamName == null) {
            null
        } else {
            team.name.eq(teamName)
        }
    }
}
``` 