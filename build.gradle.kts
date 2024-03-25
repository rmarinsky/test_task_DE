plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.ravlyk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.codeborne:selenide:7.2.2")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    testImplementation ("org.slf4j:slf4j-simple:2.0.12")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}