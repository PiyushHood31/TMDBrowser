plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization")
    alias(libs.plugins.hilt)
    kotlin("kapt")
}
hilt {
    enableAggregatingTask = false
}

android {
    namespace = "com.piyushhood.tmdbrowser"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.piyushhood.tmdbrowser"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "TMDB_API_KEY",
            "\"${project.findProperty("TMDB_API_KEY") ?: ""}\""
        )
        println("TMDB_API_KEY from Gradle = ${project.findProperty("TMDB_API_KEY")}")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kapt{
        correctErrorTypes = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

configurations.all {
    resolutionStrategy {
        force ("com.squareup:javapoet:1.13.0")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation("com.squareup:javapoet:1.13.0")
    kapt ("com.squareup:javapoet:1.13.0")

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.complier)
    implementation(libs.androidx.room.paging)

    implementation(libs.accompanist.swiperefresh)

    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)


}