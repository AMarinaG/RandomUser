plugins {
    alias(libs.plugins.amg.randomuser.android.library)
    alias(libs.plugins.amg.randomuser.android.library.jacoco)
    alias(libs.plugins.amg.randomuser.android.hilt)
}

android {
    namespace = "com.amarinag.randomuser.core.common"

}
reporting {
    baseDir = File("${rootProject.buildDir.path}/reports/${project.name}")
}
dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}