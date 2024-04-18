plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.rizan.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rizan.newsapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation("io.coil-kt:coil:2.2.0")

    implementation ("androidx.lifecycle:lifecycle-common:$2.6.0-alpha05")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha05")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha05")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha05")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    implementation ("androidx.navigation:navigation-runtime-ktx:2.6.0-alpha04")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.6.0-alpha04")
    implementation ("androidx.navigation:navigation-ui-ktx:2.6.0-alpha04")

    implementation ("androidx.room:room-runtime:2.5.0")
    annotationProcessor ("androidx.room:room-compiler:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
    implementation ("androidx.room:room-paging:2.5.0")

    implementation ("com.tbuonomo:dotsindicator:4.2")

    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")

    implementation ("androidx.fragment:fragment-ktx:1.5.5")

    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}