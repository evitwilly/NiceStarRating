import org.jreleaser.model.Active

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
    id("org.jreleaser")
}

kotlin {
    jvmToolchain(17)
}

val githubUsername = "DmitryTsyvtsyn"
val libraryGroupId = "io.github.dmitrytsyvtsyn"
val libraryArtifactId = "nicestarratingview"
val libraryVersion = "1.0.3"

android {
    namespace = "io.github.dmitrytsyvtsyn.nicestarrating"
    compileSdk = libs.versions.compileSdk.get().toInt()

    version = libraryVersion

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

jreleaser {
    gitRootSearch = true
    signing {
        active.set(Active.ALWAYS)
        armored = true
        verify = true
    }
    project {
        inceptionYear = "2024"
        author("@$githubUsername")
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active.set(Active.ALWAYS)
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(layout.buildDirectory.dir("staging-deploy").get().toString())
                    setAuthorization("Basic")
                    applyMavenCentralRules = false // Wait for fix: https://github.com/kordamp/pomchecker/issues/21
                    sign = true
                    checksums = true
                    sourceJar = true
                    javadocJar = true
                    retryDelay = 60
                }
            }
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }

            groupId = libraryGroupId
            artifactId = libraryArtifactId
            version = libraryVersion

            pom {
                name.set(libraryArtifactId)
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

        repositories {
            maven {
                setUrl(layout.buildDirectory.file("staging-deploy"))
            }
        }
    }
}