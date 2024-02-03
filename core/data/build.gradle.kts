@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.amg.randomuser.android.library)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
    alias(libs.plugins.amg.randomuser.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.amarinag.randomuser.core.data"
}

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))
    api(project(":core:network"))

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
}