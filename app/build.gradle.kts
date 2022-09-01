plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt") version "1.7.10"
    id("dagger.hilt.android.plugin")
}

repositories {
    google()
    mavenCentral()
}


android {
    compileSdk = Config.compileSdk

    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }


    sourceSets {
        getByName("androidTest") {
            assets.srcDirs(files(projectDir, "schemas"))
        }
    }

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = Dependencies.Compose.compilerVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.Common.core)
    implementation(Dependencies.Common.lifecycle)
    testImplementation(Dependencies.Test.junitTest)
    androidTestImplementation(Dependencies.Test.junitExtTest)
    androidTestImplementation(Dependencies.Test.espresso)


    // Compose dependencies
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.activity)

    implementation(Dependencies.Compose.viewModelLifecycle)
    implementation(Dependencies.Navigation.navigationCompose)
    implementation(Dependencies.Compose.flowLayout)

    androidTestImplementation(Dependencies.Compose.uiTest)
    debugImplementation(Dependencies.Compose.toolingTest)
    debugImplementation(Dependencies.Compose.manifestTest)

    // Coroutines
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(Dependencies.Coroutines.lifecycleViewModel)
    implementation(Dependencies.Coroutines.lifecycleRuntime)

    // Dagger - Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.androidCompiler)
    kapt(Dependencies.Hilt.compiler)
    implementation(Dependencies.Hilt.navigation)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.converter)
    implementation(Dependencies.Retrofit.okHttp)

    // Coil
    implementation(Dependencies.Common.coil)

    // Palette
    implementation(Dependencies.Common.palette)

    // Room
    implementation(Dependencies.Room.runtime)
    kapt(Dependencies.Room.compiler)

    implementation(Dependencies.Room.ktx)
}