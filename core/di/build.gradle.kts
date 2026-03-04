plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.dashkin.dataviewer.core.di"
    compileSdk = 35

    defaultConfig {
        minSdk = 29
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    api(libs.koin.core)
    api(libs.koin.android)
    api(libs.koin.androidx.compose)

    implementation(project(":core:parser"))
    implementation(project(":core:utils"))
}
