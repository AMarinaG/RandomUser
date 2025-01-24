@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.amg.randomuser.android.library)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.amarinag.randomuser.core.domain"
}

dependencies {
    api(projects.core.common)
    api(projects.core.data)
    api(projects.core.model)
    implementation(libs.androidx.paging.common)
    testImplementation(projects.core.testing)

    implementation(libs.javax.inject)
}