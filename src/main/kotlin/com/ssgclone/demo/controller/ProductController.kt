package com.ssgclone.demo.controller

import com.ssgclone.demo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("product")
class ProductController(
    @Autowired private val productService: ProductService
) {
    @GetMapping("detail/{id}")
    fun detailPage(@PathVariable("id") id: Long): String {
        productService.getDetail(id)
        return "product_info"
    }
}