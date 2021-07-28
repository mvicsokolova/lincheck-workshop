import org.jetbrains.kotlin.gradle.plugin.*

plugins {
    kotlin("jvm") version "1.4.0"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.jetbrains.kotlinx:lincheck:2.14")

    // TODO: the bug from scr/3.2-jctools was fixed in 3.3.0
    testImplementation("org.jctools:jctools-core:3.1.0")
}

sourceSets["test"].java.setSrcDirs(listOf("src"))