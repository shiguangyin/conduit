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
}

dependencies {
    api("com.google.protobuf:protobuf-java:3.6.1")
}


