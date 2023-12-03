plugins {
    id("com.android.application")
}
android {
    namespace = "com.example.experiment2"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.experiment2"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ndk {
            // 设置支持的SO库架构（开发者可以根据需要，选择一个或多个平台的so）
            "x86"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            proguardFiles (
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
//    ndkVersion = rootProject.extra["ndkVersion"] as String
}
dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.tracing:tracing:1.2.0-beta02")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.baidu.lbsyun:BaiduMapSDK_Location:9.5.2")
    implementation("com.baidu.lbsyun:BaiduMapSDK_Map:7.5.9.2")
    implementation("com.tencent.map:tencent-map-vector-sdk:4.3.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.json:json:20231013")
//    androidTestImplementation("androidx.test.ext:junit:1.2.0-alpha02")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0-alpha02")
//    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.6.0-alpha02")
    androidTestImplementation ("androidx.test.ext:junit:1.2.0-alpha01")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.5.1")
}

