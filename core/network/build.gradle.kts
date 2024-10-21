plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "wraparica.com.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24


        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
            arg("room.expandProjection", "true")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            dimension = "version"
            buildConfigField("String", "OPEN_WEATHER_DOMAIN", "\"https://api.openweathermap.org/\"")
            buildConfigField("String", "OPEN_WEATHER_APP_ID", "\"0528346fc3ecd7ae5836039cb42100e8\"")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.moshi.converter)

    //moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    //gson converter
    implementation(libs.moshiGson)

    implementation(libs.moshiSquare)

    //dagger hilt
    implementation(libs.daggerHilt)
    ksp (libs.daggerHiltCompiler)

    implementation(project(":core:model"))

    //Arrow
    implementation(libs.arrow.core)
    implementation(libs.androidx.lifecycle.runtime.compose)

    kapt (libs.kotlinx.metadata.jvm)
    //okhttp3 logger
    implementation(libs.okHttp3Logger)
}