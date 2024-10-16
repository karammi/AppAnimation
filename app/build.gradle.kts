plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.asad.appanimation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.asad.appanimation"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
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
    packaging {
        resources {
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {

    implementation(project(":Theme"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    kapt(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt)
    implementation(libs.navigation.compose)

    //room
//    implementation(libs.room.runtime)
//    kapt(libs.room.compiler)
//    implementation(libs.room.ktx)
//    androidTestImplementation(libs.room.testing)

    implementation(libs.retrofit)
    implementation(libs.reftofit.converter.moshi)

    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
//    kapt(libs.moshi.kotlin.codegen)

    implementation(libs.material)

    implementation(libs.kotlin.collection)

    //coroutine_test
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.core.testing)
    testImplementation(libs.turbine)
    testImplementation(libs.google.truth)

    kaptTest(libs.dagger.hilt.compiler)
    testImplementation(libs.room.testing)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.dagger.hilt.compiler)

    testImplementation(libs.mockk)
    androidTestImplementation(libs.okhttp)
}