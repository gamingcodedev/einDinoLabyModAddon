version = "0.1.0"

plugins {
    id("java-library")
}

dependencies {
    api(project(":api"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

labyModProcessor {
    referenceType = net.labymod.gradle.core.processor.ReferenceType.DEFAULT
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}