import com.egoriku.dependencies.Modules
import com.egoriku.ext.registerModules

registerModules(
        *Modules.applications,
        *Modules.dynamicFeatures,
        *Modules.features,
        *Modules.libraries
)