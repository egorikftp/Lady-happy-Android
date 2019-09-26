import com.egoriku.dependencies.Modules
import com.egoriku.ext.registerModules

include(":app")

registerModules(
        *Modules.features,
        *Modules.libraries
)