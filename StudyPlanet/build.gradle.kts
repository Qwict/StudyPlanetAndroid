plugins {

    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    kotlin("kapt")
    id("org.jetbrains.dokka") version "1.9.10"
}

android {
    namespace = "com.qwict.studyplanetandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.qwict.studyplanetandroid"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.9.18"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Compose Imports
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
//    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    runtimeOnly("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")

    // Material design
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")

    implementation("com.google.android.engage:engage-core:1.3.1")
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Dagger Hilt
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("com.google.dagger:hilt-android:2.49")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0-alpha01")
    annotationProcessor("com.google.dagger:hilt-compiler:2.48.1")

    // Retrofit for backend API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Required for Room (local database) v2.5.2 is stable apparently (Lector)
    implementation("androidx.room:room-runtime:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")

    // Used for JWT token decoding
    // https://mvnrepository.com/artifact/com.auth0.android/jwtdecode
    implementation("com.auth0.android:jwtdecode:2.0.2")

    // utility library for datastore encryption
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")

    // Testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    androidTestAnnotationProcessor("com.google.dagger:hilt-compiler:2.48.1")
    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    testAnnotationProcessor("com.google.dagger:hilt-compiler:2.48.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Debugging
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Old Imports
    // Added for navigation
//    implementation("androidx.media3:media3-ui:1.2.0")
//    implementation("com.google.firebase:firebase-sessions:1.2.0")
//    implementation("androidx.navigation:navigation-compose:2.7.6")
//    kapt("androidx.hilt:hilt-compiler:1.1.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
//    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.annotation:annotation:1.7.1")
    // datastore used to store token
//    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // extension for datastore to support encryption
//    implementation("io.github.osipxd:security-crypto-datastore-preferences:1.0.0-alpha04")
    // using this to store the token in the keystore
//    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    // https://mvnrepository.com/artifact/io.coil-kt/coil-compose
//    runtimeOnly("io.coil-kt:coil-compose:2.5.0")
}
