object Dependencies {

    object Common {
        const val core = "androidx.core:core-ktx:1.8.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"

        const val coil = "io.coil-kt:coil-compose:2.1.0"
        const val palette = "androidx.palette:palette-ktx:1.0.0"
    }

    object Compose {
        const val compilerVersion = "1.3.2"
        private const val bomVersion = "2022.11.00"

        const val composeBom = "androidx.compose:compose-bom:$bomVersion"

        const val ui = "androidx.compose.ui:ui"
        const val material = "androidx.compose.material:material"
        const val tooling = "androidx.compose.ui:ui-tooling-preview"
        const val activity = "androidx.activity:activity-compose:1.5.1"

        const val toolingTest = "androidx.compose.ui:ui-tooling"
        const val uiTest = "androidx.compose.ui:ui-test-junit4"
        const val manifestTest = "androidx.compose.ui:ui-test-manifest"

        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:0.17.0"
        const val viewModelLifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    }

    object Room {
        private const val version = "2.4.3"
        const val ktx = "androidx.room:room-ktx:$version"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object Navigation {
        const val navigationCompose = "androidx.navigation:navigation-compose:2.5.1"
    }

    object Coroutines {
        private const val version = "1.6.2"
        private const val lifecycleVersion = "2.5.1"

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"

        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    }

    object Retrofit {
        private const val version = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converter = "com.squareup.retrofit2:converter-gson:$version"
        const val okHttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.3"
    }

    object Test {
        const val junitTest = "junit:junit:4.13.2"
        const val junitExtTest = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Hilt {
        private const val version = "2.42"
        const val android = "com.google.dagger:hilt-android:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"

        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val compiler = "androidx.hilt:hilt-compiler:1.0.0"
    }
}