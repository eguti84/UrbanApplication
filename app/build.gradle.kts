plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Versions.compile)
    defaultConfig {
        applicationId = "com.example.urbanapplication"
        minSdkVersion(Versions.min)
        targetSdkVersion(Versions.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Kotlin.coreKtx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidArchitecture.navigationKtx)
    implementation(Dependencies.AndroidArchitecture.navigationUiKtx)
    implementation(Dependencies.Image.glide)
    implementation(Dependencies.Parsing.moshi)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitMoshi)
    implementation(Dependencies.Network.retrofitCoroutines)
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpInterceptor)
    implementation(Dependencies.Injection.dagger)
    implementation(Dependencies.Injection.daggerAndroid)
    implementation(Dependencies.Injection.daggerAndroidSupport)

    implementation(Dependencies.MultiThreading.coroutinesCore)
    implementation(Dependencies.MultiThreading.coroutinesAndroid)

    kapt(Dependencies.Injection.daggerCompiler)
    kapt(Dependencies.Injection.daggerAnnotationProcessor)
    kapt(Dependencies.Image.glideCompiler)
    kapt(Dependencies.Parsing.moshiCodeGen)

    testImplementation(Dependencies.TestLibraries.junit4)
    testImplementation(Dependencies.TestLibraries.coroutines)
    testImplementation(Dependencies.TestLibraries.testExt)
    testImplementation(Dependencies.TestLibraries.espresso)
    testImplementation(Dependencies.TestLibraries.mockk)
    testImplementation(Dependencies.TestLibraries.core)

    androidTestImplementation(Dependencies.TestLibraries.coroutines)
}
