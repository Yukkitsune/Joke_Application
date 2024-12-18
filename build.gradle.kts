// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("io.gitlab.arturbosch.detekt") version("1.23.7")
    java
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)

    }


}
