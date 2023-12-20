import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"

}

android {
    namespace = "com.example.quranme"
    compileSdk = 34

    defaultConfig {

        val keystoreFile = project.rootProject.file("apikeys.properties")
        if (keystoreFile.exists()) {
            val properties = Properties()
            properties.load(keystoreFile.inputStream())

            // Get the API key, or use an empty string if not found
            val apiKey = properties.getProperty("GPT_API_KEY") ?: ""

            // Add the API key to BuildConfig
            buildConfigField("String", "GPT_API_KEY", "\"$apiKey\"")
        }


        applicationId = "com.example.quranme"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/INDEX.LIST"
            excludes += "/META-INF/io.netty.versions.properties"
            excludes += "/META-INF/{AL2.0,LGPL2.1}"

        }
    }
}

dependencies {



    // Font
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")

    // API
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson Converter
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // OkHttp

    // Coil
    implementation("io.coil-kt:coil-compose:1.3.2")

    // Livedata
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")


    // Compose
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.5")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("com.google.firebase:firebase-analytics:21.5.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")


    // backend untuk chatbot
    implementation("io.ktor:ktor-client-android:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.ktor:ktor-client-logging:1.6.7")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    // ktor utk backend
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-host-common:1.6.7")
    implementation("io.ktor:ktor-client-cio:1.6.7")
    implementation("io.ktor:ktor-client-gson:1.6.7")

    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling-preview:1.1.1")

    // For image loading, if needed
    implementation("com.google.accompanist:accompanist-coil:0.13.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")


    implementation("com.google.android.gms:play-services-location:18.0.0")

    // CameraX
    implementation("androidx.camera:camera-camera2:1.3.0-alpha06")
    implementation("androidx.camera:camera-lifecycle:1.3.0-alpha06")
    implementation("androidx.camera:camera-view:1.3.0-alpha06")

// MLKit
    implementation("com.google.mlkit:face-detection:16.1.5")
    implementation("com.google.mlkit:image-labeling:17.0.7")
    implementation("com.google.mlkit:object-detection:17.0.0")
    implementation("com.google.mlkit:barcode-scanning:17.1.0")
    implementation("com.google.mlkit:text-recognition:16.0.0-beta6")
    implementation("com.google.mlkit:face-mesh-detection:16.0.0-beta1")

    // Accompanist
    implementation("com.google.accompanist:accompanist-navigation-animation:0.23.0")
    implementation("com.google.accompanist:accompanist-permissions:0.19.0")
// SQLite
// Room components

    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")


}