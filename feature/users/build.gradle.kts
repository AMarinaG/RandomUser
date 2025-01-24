@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.amg.randomuser.android.feature)
    alias(libs.plugins.amg.randomuser.android.library.compose)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
}

android {
    namespace = "com.amarinag.randomuser.feature.users"
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    testImplementation(projects.core.testing)
    androidTestImplementation(projects.core.testing)
}