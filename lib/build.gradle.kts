import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
    id("signing")
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "io.github.dmitrytsyvtsyn.nicestarrating"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    testOptions {
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.core.ktx)
}

val localProperties = fetchProperties("local.properties")
val publishArtifactId = "nicestarratingview"
val githubUsername = "DmitryTsyvtsyn"

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])

                groupId = "io.github.dmitrytsyvtsyn.nicestarratingview"
                artifactId = publishArtifactId
                version = "1.0.3"

                repositories {
                    maven {
                        val releasesUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                        val snapshotsUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                        url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
                        credentials {
                            username = localProperties.getProperty("ossrhUsername")
                            password = localProperties.getProperty("ossrhPassword")
                        }
                    }
                }

                pom {
                    name.set(publishArtifactId)
                    description.set("A simple view to display the rating with stars")
                    url.set("https://github.com/$githubUsername/NiceStarRatingView")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://github.com/$githubUsername/NiceStarRatingView/blob/develop/LICENSE.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("dmitry_tsyvtsyn")
                            name.set("Dmitry Tsyvtsyn")
                            email.set("dmitry.kind.2@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:github.com/$githubUsername/NiceStarRatingView.git")
                        developerConnection.set("scm:git:ssh://github.com/$githubUsername/NiceStarRatingView.git")
                        url.set("https://github.com/$githubUsername/NiceStarRatingView")
                    }
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        localProperties.getProperty("signing.keyId"),
        localProperties.getProperty("signing.key"),
        localProperties.getProperty("signing.password")
    )
    sign(publishing.publications)
}

fun fetchProperties(filename: String = "local.properties"): Properties {
    val localProperties = Properties()
    val localPropertiesFile = project.rootProject.file(filename)
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use {
            localProperties.load(it)
        }
    }
    return localProperties
}