package com.ssgclone.demo.service

import com.ssgclone.demo.dto.RequestSaveProduct
import com.ssgclone.demo.dto.ResponseProducts
import kotlin.jvm.Throws

interface IProductService {
    @Throws(Exception::class)
    fun saveProduct(dto: RequestSaveProduct)

    @Throws(Exception::class)
    fun getList(): List<ResponseProducts>
}