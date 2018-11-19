import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

apply {
    plugin("com.github.ben-manes.versions")
}

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { setUrl("https://maven.fabric.io/public") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath(kotlin("gradle-plugin", version = "1.3.10"))
        classpath("com.google.gms:google-services:4.2.0")
        classpath("io.fabric.tools:gradle:1.26.1")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.20.0")

        // classpath "com.getkeepsafe.dexcount:dexcount-gradle-plugin:0.8.2"
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://maven.fabric.io/public") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

/**
 * Disable Dagger formatting of generated code
 */
tasks.withType(JavaCompile::class).all {
    options.compilerArgs.add("-Adagger.formatGeneratedSource=disabled")
}

afterEvaluate {
    extensions.findByName("kapt")?.let {
        (this as KaptExtension).arguments {
            arg("dagger.formatGeneratedSource", "disabled")
        }
    }
}

/**
 * Enable Dagger incremental annotation processing
 */
gradle.projectsEvaluated {
    tasks.withType(JavaCompile::class) {
        options.compilerArgs.add("-Adagger.gradle.incremental")
    }
}