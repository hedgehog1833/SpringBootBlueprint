val javaVersion: JavaVersion = JavaVersion.VERSION_21
val dependencyVersions: List<String> = listOf(
    "org.junit:junit-bom:5.10.2"
)
val dependencyGroupVersions: Map<String, String> = emptyMap()

plugins {
  alias(libs.plugins.springBoot)
  alias(libs.plugins.springDependencyManagement)
  groovy
}

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
  bootJar {
    archiveClassifier.set("boot")
    enabled = true
  }

  jar {
    enabled = false
  }

  withType<Test> {
    useJUnitPlatform()
  }
}

dependencies {
  implementation(libs.bundles.springBootStarterApp)

  implementation(libs.bundles.groovyApp)
  runtimeOnly(libs.postgres)

  developmentOnly(libs.springBootDevTools)
  annotationProcessor(libs.springBootConfigurationProcessor)

  testImplementation(libs.springSecurityTest)
  testImplementation(libs.bundles.testing)
  testRuntimeOnly(libs.h2)
}

tasks {
  wrapper {
    gradleVersion = "8.6"
    distributionType = Wrapper.DistributionType.ALL
  }
}
