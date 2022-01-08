package com.ssgclone.demo.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ssgclone.demo.vo.ProductParseVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils

@Component
class InitProductService(
    @Autowired private val productService: ProductService
): CommandLineRunner {
    override fun run(vararg args: String?) {
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

        vo.forEach { productService.saveProduct(it) }
    }
}