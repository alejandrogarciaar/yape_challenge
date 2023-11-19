// Config
mapOf(
    "defaultCompileSdk" to 34,
    "defaultMinSdk" to 24,
    "defaultTargetSdk" to 34,
).forEach { (name, version) ->
    project.extra.set(name, version)
}

// App Versions
mapOf(
    "yapeVersionCode" to 1,
    "yapeVersionName" to "1.0",
).forEach { (name, version) ->
    project.extra.set(name, version)
}