plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    viewBindingEnabled = true
    composeEnabled = true
}

dependencies {
    implementation(projects.app)
}