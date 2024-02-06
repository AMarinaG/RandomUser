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
    api(project(":core:data"))
    api(project(":core:model"))

    testImplementation(project(":core:testing"))

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.guava)
}