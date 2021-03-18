import com.egoriku.ext.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.withThirdPartyLibraries(vararg libs: String) {
    dependencies {
        libs.forEach {
            implementation(it)
        }
    }
}

fun Project.withProjectLibraries(vararg projects: String) {
    dependencies {
        projects.forEach {
            implementation(project(it))
        }
    }
}