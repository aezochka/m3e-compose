import org.gradle.api.publish.maven.MavenPublication

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
}

val m3eVersion = (findProperty("m3eVersion") as String?) ?: "0.1.0"

android {
    namespace = "dev.aezochka.m3e"
    compileSdk = 37

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures { compose = true }

    publishing {
        singleVariant("release") { withSourcesJar() }
    }
}

project.afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("m3e") {
                groupId = "dev.aezochka"
                artifactId = "m3e-compose"
                version = m3eVersion
                from(components["release"])
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/aezochka/m3e-compose")
                credentials {
                    username = (findProperty("gprUser") as String?) ?: System.getenv("GITHUB_ACTOR") ?: "aezochka"
                    password = (findProperty("gprToken") as String?) ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}

dependencies {
    api(platform(libs.compose.bom))
    api(libs.compose.material3)
    api("androidx.compose.material3:material3-ripple:1.5.0-beta01")
    api(libs.compose.material.icons.extended)
    api("androidx.graphics:graphics-shapes:1.0.1")
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.foundation)
}
