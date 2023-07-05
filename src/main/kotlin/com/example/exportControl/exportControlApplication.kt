
//package com.example.exportControl
//
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
//import org.springframework.boot.runApplication
//
//@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
//class ExportControlApplication
//
//fun main(args: Array<String>) {
//	System.setProperty("spring.devtools.restart.enabled", "false");
//	runApplication<ExportControlApplication>(*args)
//}


package com.example.exportControl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ExportControlApplication:SpringBootServletInitializer(){
	override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
		return builder.sources(ExportControlApplication::class.java)
	}
}

fun main(args: Array<String>) {
	System.setProperty("spring.devtools.restart.enabled", "false");
	runApplication<ExportControlApplication>(*args)
}

