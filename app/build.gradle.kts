import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
val key: String = gradleLocalProperties(rootDir).getProperty("MAPS_API_KEY")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.ecotup.ecotupapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ecotup.ecotupapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "URL", "\"https://backend-prod-dot-ecotup.et.r.appspot.com/\"")
        buildConfigField("String", "URL_SERVICE", "\"https://ecotup-service-mak5zhjhrq-et.a.run.app/\"")
        buildConfigField("String", "URL_TIMEZONE", "\"https://timeapi.io/api/Time/current/zone?\"")
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
        getByName("debug") {
            buildConfigField("String", "API_MAPS_KEY", "\"$key\"")
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
        buildConfig = true
        mlModelBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
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
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("org.tensorflow:tensorflow-lite-support:0.1.0")
    implementation("org.tensorflow:tensorflow-lite-metadata:0.1.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Coil for Image
    implementation("io.coil-kt:coil-compose:2.2.2")


    // ViewModel
    val lifecycle_version = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // GMS Google Maps Services
    implementation("com.google.android.gms:play-services-base:18.2.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Android Maps Compose composables for the Maps SDK for Android
    implementation("com.google.maps.android:maps-compose:4.3.0")

    // Google Maps Util
    implementation("com.google.maps.android:android-maps-utils:3.4.0")

    // Testing
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")

    // Sweetalert2
    implementation("com.github.f0ris.sweetalert:library:1.6.2")

    // ViewPager
    implementation("com.google.accompanist:accompanist-pager:0.12.0")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Material Icons rating : https://jetpackcomposeworld.com/custom-rating-bar-in-jetpack-compose-a-comprehensive-guide/
    implementation ("androidx.compose.material:material-icons-extended-android:1.6.0-beta02")

    // Swipe to refresh
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.27.0")

    // Firebase
    implementation("com.google.firebase:firebase-auth:22.3.0")

    // Play Service Auth
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Lottie
    val lottieVersion = "6.2.0"
    implementation ("com.airbnb.android:lottie:$lottieVersion")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

}