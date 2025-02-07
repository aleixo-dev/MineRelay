plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

group = "com.nicolas"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    compileOnly(libs.paper.api.v1214r01snapshot)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.kotlinx.serialization.json)
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.shadowJar {
    archiveBaseName = "MineRelay"
    archiveVersion = ""
    archiveClassifier = ""
    destinationDirectory.set(file("C:/Users/nicol/OneDrive/Área de Trabalho/ARQUIVOS/MINECRAFR_1_21_4_SERVER/plugins"))
}