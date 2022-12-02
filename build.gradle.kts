val javaVersion: JavaVersion = JavaVersion.VERSION_17
val dependencyVersions: List<String> = listOf(
    "org.jetbrains:annotations:21.0.0",
    "org.junit:junit-bom:5.8.2"
)
val dependencyGroupVersions: Map<String, String> = mapOf()

plugins {
  id("org.springframework.boot") version "2.7.6"
  id("io.spring.dependency-management") version "1.1.0"
  id("groovy")
}

springBoot {
  mainClass.set("com.dammenhayn.blueprint.SpringBootBlueprintApplication")
}

group = "com.dammenhayn"

repositories {
  mavenCentral()
}

configure<JavaPluginExtension> {
  sourceCompatibility = javaVersion
  targetCompatibility = javaVersion
}

configurations {
  all {
    resolutionStrategy {
      failOnVersionConflict()
      force(dependencyVersions)
      eachDependency {
        val forcedVersion = dependencyGroupVersions[requested.group]
        if (forcedVersion != null) {
          useVersion(forcedVersion)
        }
      }
      cacheDynamicVersionsFor(0, "seconds")
    }
  }
}

tasks {
  withType<Test> {
    useJUnitPlatform()
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

  implementation("io.micrometer:micrometer-registry-prometheus")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

  implementation("org.codehaus.groovy:groovy:${libs.versions.groovy.get()}")
  implementation("org.codehaus.groovy:groovy-json:${libs.versions.groovy.get()}")
  implementation("org.codehaus.groovy:groovy-datetime:${libs.versions.groovy.get()}")

  developmentOnly("org.springframework.boot:spring-boot-devtools")
  runtimeOnly("com.h2database:h2")
  runtimeOnly("org.postgresql:postgresql:${libs.versions.postgresql.get()}")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "junit", module = "junit")
  }
  testImplementation("org.codehaus.groovy:groovy-test:${libs.versions.groovy.get()}")
  testImplementation("org.spockframework:spock-core:${libs.versions.spock.get()}")
  testImplementation("org.spockframework:spock-spring:${libs.versions.spock.get()}")
  testImplementation("org.springframework.security:spring-security-test")
}

tasks {
  wrapper {
    gradleVersion = "7.6"
    distributionType = Wrapper.DistributionType.ALL
  }
}
