package com.ssgclone.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ssgclone.demo.vo.ProductParseVo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.util.ResourceUtils

@SpringBootApplication
class NumbleSsgcloneKwt1326Application

fun main(args: Array<String>) {
	runApplication<NumbleSsgcloneKwt1326Application>(*args)

	// local
	parseInitLocalJSON()
}

fun parseInitLocalJSON() {
	val mapper: ObjectMapper = ObjectMapper().registerKotlinModule()
	val vo: List<ProductParseVo> = mapper.readValue(
		ResourceUtils.getFile("classpath:static/json/dummy1.json"),
		mapper.typeFactory.constructCollectionType(
			List::class.java,
			ProductParseVo::class.java
		)
	)
}
