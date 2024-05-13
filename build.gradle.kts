plugins {
    java
}

group = "io.redscorp"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.redscorp.ip.addr.counter.Main"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
