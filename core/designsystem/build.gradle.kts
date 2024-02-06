@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.amg.randomuser.android.library)
    alias(libs.plugins.amg.randomuser.android.library.compose)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
}

android {
    namespace = "com.amarinag.randomuser.core.designsytem"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)
    implementation(libs.coil.kt.compose)

    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.hilt.android.testing)
    testImplementation(project(":core:testing"))

    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(project(":core:testing"))
}