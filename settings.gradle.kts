pluginManagement {
    repositories {
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven("https://oss.sonatype.org/content/groups/public/") {
            name = "sonatype"
        }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven("https://oss.sonatype.org/content/groups/public/") {
            name = "sonatype"
        }
        google()
    }
}

rootProject.name = "MineRelay"
include(":app")
include(":backend")
include(":minecraft")
include(":minecraft:plugins")
include(":minecraft:plugins:mine-relay")
