plugins {
    id("com.android.library")
}

android {
    namespace = "io.intercom.android"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("io.intercom.android:intercom-sdk:17.0.2")
    implementation("com.google.firebase:firebase-messaging:24.1.+")
}

