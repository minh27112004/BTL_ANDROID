pluginManagement {
    repositories {
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
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT) // Tránh lỗi khi có repo trong module
    repositories {
        google()
        mavenCentral()
         jcenter() // Chỉ bật nếu thực sự cần thiết
        maven("https://jitpack.io")
    }
}


rootProject.name = "BTL_ANDROID"
include(":app")
 