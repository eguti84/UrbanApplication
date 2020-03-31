buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(GradlePlugins.kotlin)
        classpath(GradlePlugins.android)
        classpath(GradlePlugins.safeArgs)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}