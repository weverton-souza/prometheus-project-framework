rootProject.name = "prometheus-project-root-service"

include(":prometheus-project-config-service")
include(":prometheus-project-core-service")
include(":prometheus-project-app-service")
include(":prometheus-project-property")


File(settingsDir, "../prometheus-project-app-service").also {
    project(":prometheus-project-app-service").projectDir = it
}

File(settingsDir, "../prometheus-project-config-service").also {
    project(":prometheus-project-config-service").projectDir = it
}

File(settingsDir, "../prometheus-project-core-service").also {
    project(":prometheus-project-core-service").projectDir = it
}

File(settingsDir, "../prometheus-project-property").also {
    project(":prometheus-project-property").projectDir = it
}
