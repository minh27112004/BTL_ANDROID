plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.btl_android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.btl_android"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.dhaval2404:imagepicker:2.1")
    implementation ("me.relex:circleindicator:2.1.6")

    implementation ("com.google.android.material:material:<version>")


    implementation ("com.github.bumptech.glide:glide:4.15.1");

}