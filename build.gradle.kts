plugins {
    kotlin("multiplatform") version "1.4.21"
}

group = "dev.einsjannis"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(LEGACY) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    mingwX64()
    mingwX86()
    linuxX64()

    
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val mingwX64Main by getting
        val mingwX64Test by getting
        val mingwX86Main by getting
        val mingwX86Test by getting
        val linuxX64Main by getting
        val linuxX64Test by getting
        val nativeCommonMain by creating {
            dependsOn(commonMain)
            mingwX64Main.dependsOn(this)
            mingwX86Main.dependsOn(this)
            linuxX64Main.dependsOn(this)
        }
        val nativeCommonTest by creating {
            dependsOn(commonTest)
            mingwX64Test.dependsOn(this)
            mingwX86Test.dependsOn(this)
            linuxX64Test.dependsOn(this)
        }
        all {
            languageSettings.useExperimentalAnnotation("kotlin.ExperimentalUnsignedTypes")
        }
    }
}
