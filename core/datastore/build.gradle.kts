plugins {
    alias(libs.plugins.amg.randomuser.android.library)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
    alias(libs.plugins.amg.randomuser.android.hilt)
}

android {
    namespace = "com.amarinag.core.datastore"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)
    implementation(libs.androidx.datastore.preferences)

    testImplementation(libs.kotlinx.coroutines.test)
}