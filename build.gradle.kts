import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.20"
	kotlin("plugin.spring") version "1.5.20"
	kotlin("plugin.jpa") version "1.5.20"
	id("war")

}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
	mavenCentral()

	maven {
		url = uri("https://jitpack.io")
	}
}

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-websocket")

	implementation ("org.webjars:webjars-locator-core")
	implementation ("org.webjars:sockjs-client:1.5.1")
	implementation ("org.webjars:stomp-websocket:2.3.3")
	implementation ("org.webjars:bootstrap:3.3.7")
	implementation ("org.webjars:jquery:3.1.1-1")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
	implementation("net.sf.supercsv:super-csv:2.4.0")
	implementation("org.springframework.boot:spring-boot-devtools:2.6.10")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("javax.xml.bind:jaxb-api:2.3.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("com.opencsv:opencsv:5.2")
	implementation("com.google.code.gson:gson:2.8.6")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.apache.commons:commons-csv:1.9.0")
	implementation("com.github.LibrePDF:OpenPDF:1.3.29")
	implementation("com.lowagie:itext:2.1.7")
	implementation("com.itextpdf:itext-asian:5.2.0")
	// https://mavenlibs.com/maven/dependency/com.itextpdf/font-asian
	implementation("com.itextpdf:font-asian:7.2.3")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("com.github.davidmoten:subethasmtp:6.0.4")
	implementation("org.apache.commons:commons-email:1.5")
	implementation("org.jsoup:jsoup:1.15.3")
//	implementation("commons-io:commons-io:2.11.0")
//	implementation("org.apache.poi:poi:5.0.0")
	implementation("org.apache.poi:poi-ooxml:5.1.0")
//	implementation("org.apache.poi:poi-ooxml:5.0.0")
//	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "16"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
tasks.withType<War>{
	enabled=true
}