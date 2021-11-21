package com.ssgclone.demo.controller

import com.ssgclone.demo.dto.RequestSaveProduct
import com.ssgclone.demo.enums.DeliveryType
import com.ssgclone.demo.enums.ManageCompany
import com.ssgclone.demo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("product")
class ProductController(
    @Autowired private val productService: ProductService
) {
    @PostMapping
    fun create(@ModelAttribute requestDto: RequestSaveProduct) {
        productService.saveProduct(requestDto)
    }

    @GetMapping("list")
    fun getList(
        @PathVariable(required = false) page: Int,
        @PathVariable(required = false) delivery: DeliveryType,
        @PathVariable(required = false) company: ManageCompany,
        @PathVariable(required = false) search: String,
    ) {
//        productService.
    }
}