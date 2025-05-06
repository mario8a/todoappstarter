plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

room { schemaDirectory("$projectDir/schemas") }

    android {
    namespace = "com.mario8a.todoappstarter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mario8a.todoappstarter"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.mario8a.todoappstarter.CustomTestRunner"
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)


    //Libreria Serializacion
    implementation(libs.kotlinx.serialization.json)

    //Librerias Room
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    //Librerias Dagger Hilt
    implementation(libs.dagger.hilt.navigation.compose)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    //Testing
    testImplementation(libs.junit4)
    testImplementation(libs.mockk)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.robolectric)
    testImplementation(libs.truth)

    //Android Testing
    androidTestImplementation(libs.junit4)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)

    // Hilt Testing
    androidTestImplementation(libs.dagger.hilt.android.testing)
    kspAndroidTest(libs.dagger.hilt.compiler)

}