import com.google.protobuf.gradle.*

plugins {
    id("java")
    id("idea")
    id("com.google.protobuf") version "0.8.18"
}


sourceSets {
    main {
        proto {
            srcDir(rootDir.path + "/idl")
        }
    }
}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.6.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.4.0"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}

dependencies {
    api("com.google.protobuf:protobuf-java:3.19.1")
    api("io.grpc:grpc-stub:1.42.1")
    api("io.grpc:grpc-protobuf:1.42.1")
    if (JavaVersion.current().isJava9Compatible) {
        api("javax.annotation:javax.annotation-api:1.3.1")
    }
}


