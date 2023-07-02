plugins {
    kotlin("jvm") version "1.8.20"
}

repositories {
    mavenCentral()
}

dependencies {
    // Lincheck dependency
    testImplementation("org.jetbrains.kotlinx:lincheck:2.20")
    // This dependency allows you to work with kotlin.test and JUnit:
    testImplementation(kotlin("test-junit"))
    testImplementation(kotlin("stdlib-jdk8"))
}
