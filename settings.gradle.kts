import com.egoriku.dependencies.SettingsProject
import com.egoriku.ext.registerModules

include(":app")

registerModules(
        *SettingsProject.features,
        *SettingsProject.libraries
)