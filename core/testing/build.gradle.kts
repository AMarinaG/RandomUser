@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.amg.randomuser.android.library)
    alias(libs.plugins.amg.randomuser.android.library.compose)
    alias(libs.plugins.amg.randomuser.android.hilt)
}

android {
    namespace = "com.amarinag.randomuser.core.testing"
    packaging {
        resources {
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    api(kotlin("test"))
    api(libs.androidx.compose.ui.test)
    api(libs.mockk)
    api(libs.mockk.android)
    api(libs.turbine)
    api(project(":core:data"))
    api(project(":core:model"))

    debugApi(libs.androidx.compose.ui.testManifest)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.test.rules)
    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.kotlinx.datetime)
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
}