plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}
dependencies {
    implementation(libs.coroutinesCore)
    implementation(libs.serializationJson)
}
