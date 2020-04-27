import com.egoriku.dependencies.versions.ClasspathVersion
import com.egoriku.dependencies.versions.LibrariesVersion

object GradlePlugins {
    const val fabric = "io.fabric.tools:gradle:${ClasspathVersion.fabric}"
    const val googleServices = "com.google.gms:google-services:${ClasspathVersion.googleServices}"
    const val gradleTools = "com.android.tools.build:gradle:${ClasspathVersion.gradleTools}"
    const val gradleLibrariesVersion = "com.github.ben-manes:gradle-versions-plugin:${ClasspathVersion.gradleLibrariesVersion}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibrariesVersion.kotlin}"
}