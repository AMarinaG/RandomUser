plugins {
    alias(libs.plugins.amg.randomuser.android.library)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
    alias(libs.plugins.amg.randomuser.android.room)
    alias(libs.plugins.amg.randomuser.android.hilt)
}

android {
    namespace = "com.amarinag.core.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.paging.runtime)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}