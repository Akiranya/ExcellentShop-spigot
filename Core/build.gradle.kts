/*
 * This file was generated by the Gradle 'init' task.
 */

import io.papermc.paperweight.util.path

plugins {
    id("su.nightexpress.excellentshop.java-conventions")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("net.kyori.indra.git") version "2.1.1"
}

dependencies {
    // NMS modules
    implementation(project(":NMS"))
    runtimeOnly(project(":V1_17_R1", configuration = "reobf"))
    runtimeOnly(project(":V1_18_R2", configuration = "reobf"))
    runtimeOnly(project(":V1_19", configuration = "reobf"))
    runtimeOnly(project(":V1_19_R2", configuration = "reobf"))

    // To be shadowed
    compileOnly("com.github.justisr:BrokerAPI:master-SNAPSHOT")

    // The server API
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")

    // 3rd party plugins
    compileOnly("su.nightexpress.gamepoints:GamePoints:1.3.4")
    compileOnly("me.xanium.gemseconomy:GemsEconomy:1.3.3")
    compileOnly("com.github.TechFortress:GriefPrevention:16.17.1")
    compileOnly("com.github.angeschossen:LandsAPI:6.20.0")
    compileOnly("org.black_ixx:playerpoints:3.0.0")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.6") {
        exclude("org.bukkit")
    }
    compileOnly("net.citizensnpcs:citizensapi:2.0.29-SNAPSHOT") {
        exclude("ch.ethz.globis.phtree")
    }
}

description = "Core"
version = "$version".decorateVersion()

fun lastCommitHash(): String = indraGit.commit()?.name?.substring(0, 7) ?: error("Could not determine commit hash")
fun String.decorateVersion(): String = if (endsWith("-SNAPSHOT")) "$this-${lastCommitHash()}" else this

bukkit {
    main = "su.nightexpress.nexshop.ExcellentShop"
    name = "ExcellentShop"
    version = "${project.version}"
    description = "Advanced 3 in 1 shop plugin with many features!"
    apiVersion = "1.17"
    authors = listOf("NightExpress")
    depend = listOf("NexEngine")
    softDepend = listOf(
        "Vault",
        "Citizens",
        "PlayerPoints",
        "WorldGuard",
        "Lands",
        "PlotSquared",
        "GamePoints",
        "GriefPrevention",
        "Broker"
    )
}

tasks {
    val outputFileName = "ExcellentShop-${project.version}"

    // Shadow settings
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        minimize {
            exclude(dependency("su.nightexpress.excellentshop:.*:.*"))
        }
        archiveFileName.set("$outputFileName.jar")
        archiveClassifier.set("")
        destinationDirectory.set(file("$rootDir"))
    }
    jar {
        archiveClassifier.set("noshade")
    }

    // Copy the output jar to the dev server
    register("deployToServer") {
        doLast {
            exec {
                commandLine("rsync", "${shadowJar.get().archiveFile.path}", "dev:data/dev/jar")
            }
        }
    }
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
