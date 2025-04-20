plugins {
    kotlin("jvm") version "2.0.21"
    `maven-publish`
}

group = "com.project"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("weatherUtils") {
            from(components["java"])
            groupId = "com.project"
            artifactId = "weather-utils"
            version = "1.0.0"
        }
    }

    repositories {
        maven {
            name = "Local"
            url = uri(layout.buildDirectory.dir("maven-repo"))
        }
    }
}
dependencies {
    testImplementation(kotlin("test"))
    testImplementation(libs.junit)
}