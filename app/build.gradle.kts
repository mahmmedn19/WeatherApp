plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
}

android {
    namespace = "com.project.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.project.weatherapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":features:cityinput"))
    implementation(project(":features:currentweather"))
    implementation(project(":features:forecast"))
    implementation(project(":weather-utils"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.composeMaterial3)
    implementation(libs.navigationCompose)
    implementation(libs.composeUi)
    implementation(libs.coil)
    implementation(libs.coilCompose)
    implementation(libs.coilGif)
    implementation(libs.splashscreen)
    implementation(libs.androidx.appcompat)


    implementation(libs.daggerHiltAndroid)
    ksp(libs.daggerHiltCompiler)
    implementation(libs.hiltNavigationCompose)

    // Coroutines
    implementation(libs.coroutines)
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesTest)

    //acompanist permissions
    implementation(libs.accompanistPermissions)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}