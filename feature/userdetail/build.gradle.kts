@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.amg.randomuser.android.feature)
    alias(libs.plugins.amg.randomuser.android.library.compose)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
    alias(libs.plugins.secrets)
    id("kotlinx-serialization")

}

android {
    namespace = "com.amarinag.randomuser.feature.userdetail"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.coil.kt.compose)
    implementation(libs.google.maps.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)

    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))

}