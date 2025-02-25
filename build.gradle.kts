plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val cvGeneratorVersion = "0.1.0"

dependencies {
    implementation("com.melkassib:cv-generator-latex:$cvGeneratorVersion")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}