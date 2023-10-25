plugins {
    id("java")
    id("com.github.weave-mc.weave-gradle") version "fac948db7f"
}

group = "club.maxstats.weaveyoutube"
version = "1.0"

repositories {
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/maven/")
    mavenCentral()
}

dependencies {
    compileOnly("com.github.weave-mc:weave-loader:v0.2.4")
    compileOnly("org.spongepowered:mixin:0.8.5")
}

minecraft.version("1.8.9")