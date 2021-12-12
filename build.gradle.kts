import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.noarg.gradle.NoArgExtension
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension


plugins {
    id("org.springframework.boot") version "2.6.1" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.0" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.0" apply false
    id("org.jetbrains.kotlin.plugin.jpa") version "1.6.0" apply false
}

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

//    configure<NoArgExtension> {
//        annotation("com.conduit.app.annotation.CacheableEntity")
//    }
//    configure<AllOpenExtension> {
//        annotation("com.conduit.app.annotation.CacheableEntity")
//    }

}

allprojects {
    group = "com.conduit"
    version = "0.0.1-SNAPSHOT"
    //java.sourceCompatibility = JavaVersion.VERSION_1_8
    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}



