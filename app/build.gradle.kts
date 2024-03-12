plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.perfomax.dataviewer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.perfomax.dataviewer"
        minSdk = 24
        targetSdk = 34
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "**/*"
//            exclude 'META-INF/DEPENDENCIES'
//            exclude 'META-INF/LICENSE'
//            exclude 'META-INF/LICENSE.txt'
//            exclude 'META-INF/license.txt'
//            exclude 'META-INF/NOTICE'
//            exclude 'META-INF/NOTICE.txt'
//            exclude 'META-INF/notice.txt'
//            exclude 'META-INF/ASL2.0'
//            exclude 'META-INF/AL2.0'
//            exclude 'META-INF/LGPL2.1'
//            exclude 'META-INF/gradle/incremental.annotation.processors'
//            exclude("META-INF/*.kotlin_module")
        }
    }
}

dependencies {

    implementation(libs.core.ktx)

    implementation(libs.lifecycle.runtime.ktx)

    // ui compose
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.compose.viewmodel)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.navigation)
    implementation(libs.androidx.navigation.runtime.ktx)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.coil.compose)

//    kapt(libs.lifecycle.compiler)

    // parsing
    implementation(libs.moshi.kotlin)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
//    implementation(libs.hilt.lifecycle.viewmodel)

    implementation(libs.lifecycle.viewmodel.ktx)
//    implementation(libs.lifecycle.livedata.ktx)
    implementation (libs.lifecycle.livedata.ktx)
    implementation (libs.lifecycle.compiler)

    implementation (libs.lifecycle.viewmodel.compose)
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")




    // Datastore
    implementation(libs.datastore)
    implementation(libs.datastore.preferences)

    // tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
}