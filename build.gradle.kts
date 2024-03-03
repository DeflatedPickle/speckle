plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.deflatedpickle"

repositories {
    mavenCentral()
}

dependencies {
    api("net.java.dev.jna:jna:5.14.0")
    api("net.java.dev.jna:jna-platform:5.14.0")

    testImplementation(kotlin("test"))
}