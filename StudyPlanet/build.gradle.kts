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
        versionName = "1.0"
        manifestPlaceholders["auth0Domain"] = "@string/com_auth0_domain"
        manifestPlaceholders["auth0Scheme"] = "@string/com_auth0_scheme"

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
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    runtimeOnly("androidx.compose.material:material:1.5.4")

    // Needed to get a view model reference in Jetpack Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
//    Why does this throw an error?
//    implementation("androidx.annotation:annotation-jvm:1.7.0")
    implementation("androidx.annotation:annotation:1.5.0")
    implementation("com.google.android.engage:engage-core:1.3.0")
    implementation("androidx.media3:media3-ui:1.1.1")
    implementation("com.google.firebase:firebase-sessions:1.1.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Added for navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // Retrofit for backend API calls
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // Using Jackson for JSON parsing (because of tutorial, and I don't like GSON)
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")

    // maybe for image loading in the future
    implementation("io.coil-kt:coil-compose:+")

    // required for Auth0 (might remove later)
//    implementation("com.auth0.android:auth0:2.0.0")
    implementation("com.auth0.android:jwtdecode:2.0.2")
    implementation("com.auth0.android:auth0:+")
    implementation("com.auth0.android:jwtdecode:+")

    // datastore used to store token
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // extension for datastore to support encryption
    implementation("io.github.osipxd:security-crypto-datastore-preferences:1.0.0-alpha04")
    // utility library for datastore encryption
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha05")

    // using this to store the token in the keystore
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    // for improved api service
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Required for Room (local database)
    implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
    ksp("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
    implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")

//    for more icons
    implementation("androidx.compose.material:material-icons-extended:1.4.3")

    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation("com.google.dagger:hilt-android:2.48.1")
    annotationProcessor("com.google.dagger:hilt-compiler:2.48.1")

    // For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    androidTestAnnotationProcessor("com.google.dagger:hilt-compiler:2.48.1")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    testAnnotationProcessor("com.google.dagger:hilt-compiler:2.48.1")

    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-rc01")
}