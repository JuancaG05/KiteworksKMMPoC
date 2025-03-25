import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.ksp)
    alias(libs.plugins.mockmp)
    id("co.touchlab.skie") version "0.10.1"
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            linkerOpts += "-lsqlite3"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kermit)
            implementation(libs.sqlDelight.coroutinesExt)
            implementation(libs.koin.core)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
        androidMain.dependencies {
            implementation(libs.sqlDelight.android)
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.sqlDelight.native)
            implementation(libs.ktor.client.ios)
        }
    }
}

mockmp {
    onTest {
        withHelper()
    }
}

android {
    namespace = "com.kiteworks.kiteworkskmmpoc"
    compileSdk = 35
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases.create("KiteworksKMMPoCDB") {
        packageName.set("com.kiteworks.kiteworkskmmpoc.db")
    }
}

skie {
    features {
        enableSwiftUIObservingPreview = true
    }
}
