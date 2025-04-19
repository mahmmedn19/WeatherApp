plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.project.weather_utils"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.project.weather"
            artifactId = "weather-utils"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            name = "local"
            url = uri("${System.getProperty("user.home")}/.m2/repository")
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    api(libs.composeUi)
    api(libs.composeMaterial3)
    api(libs.coilCompose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}