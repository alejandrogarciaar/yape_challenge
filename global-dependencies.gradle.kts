// AndroidX
mapOf(
    "androidxCore" to "1.12.0",
    "androidxAppCompat" to "1.6.1",
    "androidxLiveData" to "2.6.2",
    "androidxViewModel" to "2.6.2",
    "androidxFragment" to "1.6.2"
).forEach { (name, version) ->
    project.extra.set(name, version)
}

// Google
mapOf(
    "googleMaterial" to "1.10.0",
).forEach { (name, version) ->
    project.extra.set(name, version)
}

// Dagger Hilt
mapOf(
    "daggerHilt" to "2.48.1",
).forEach { (name, version) ->
    project.extra.set(name, version)
}

// Picasso
mapOf(
    "picasso" to "2.71828",
).forEach { (name, version) ->
    project.extra.set(name, version)
}

// Retrofit
mapOf(
    "retrofit" to "2.9.0",
).forEach { (name, version) ->
    project.extra.set(name, version)
}

// Unit Testing
mapOf(
    "junitVersion" to "4.13.2",
    "mockitoKotlin" to "3.2.0",
    "mockitoCore" to "3.9.0",
    "mockitoInline" to "3.9.0",
    "kotlinCoroutines" to "1.2.0",
    "androidCoreTesting" to "2.2.0"
).forEach { (name, version) ->
    project.extra.set(name, version)
}

// Instrumentation testing
mapOf(
    "androidJunit" to "1.1.5",
    "espressoCore" to "3.5.1",
).forEach { (name, version) ->
    project.extra.set(name, version)
}