buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
    }
}
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.com.android.library) apply false
}
true