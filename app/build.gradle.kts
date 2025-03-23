plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.nntk.nba.watch.lillard"
    compileSdk = 35
    signingConfigs {
        create("release") {
            storeFile = file("../key")
            storePassword = "nbanba"
            keyAlias = "nba"
            keyPassword = "nbanba"
        }
    }
    defaultConfig {
        applicationId = "com.nntk.nba.watch.lillard"
        minSdk = 34
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.blankj:utilcodex:1.31.1")
    implementation("org.jsoup:jsoup:1.15.3")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation("com.alibaba.fastjson2:fastjson2-kotlin:2.0.53")
    implementation("com.orhanobut:logger:2.2.0")

    implementation("io.github.cymchad:BaseRecyclerViewAdapterHelper:3.0.14")

    implementation("androidx.wear:wear:1.3.0")
    implementation(libs.watchface.complications.data.source.ktx)


    implementation(libs.play.services.wearable)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.wear.tooling.preview)
    implementation(libs.activity.compose)
    implementation(libs.core.splashscreen)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}