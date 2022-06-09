group = "us.tohka"
version = "1.0-SNAPSHOT"

plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:+")
}

val fatJar = task("fatJar", type = Jar::class) {
    manifest {
        attributes["Main-Class"] = "us.tohka.aviz.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}