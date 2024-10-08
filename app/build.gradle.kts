
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
//    id("com.google.devtools.ksp")
    alias(libs.plugins.kotlin.ksp)
}



android {
    namespace = "com.formation.mvvm_compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.formation.mvvm_compose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //Adding translations
        resourceConfigurations.addAll(listOf("es", "en"))

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
//    Be careful!! This version is needed to that kotlin compose version and room version
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin)
    implementation(libs.androidx.appCompat)


    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)


    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    //bundles
    implementation(libs.bundles.androidx.ui)
    implementation(libs.bundles.google.accompanist)
    implementation(libs.bundles.androidx.lifecyle)
    implementation(libs.bundles.androidx.datastoreCrypto)
    debugImplementation(libs.bundles.androidx.debug.ui)
    androidTestImplementation(libs.bundles.androidx.test.junit)

    //project
    implementation(project(":domain"))
    implementation(project(":data"))
}