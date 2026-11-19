plugins {
    id("com.android.application")
}

android {
    namespace = "it.dhd.ocnotificationstyletemplate"
    compileSdk = 37

    defaultConfig {
        applicationId = "it.dhd.ocnotificationstyletemplate"
        minSdk = 33
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        disable += "UnusedResources"
    }
}
