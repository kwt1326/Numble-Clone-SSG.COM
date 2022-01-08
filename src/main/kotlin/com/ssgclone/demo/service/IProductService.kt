package com.ssgclone.demo.service

import com.ssgclone.demo.dto.ResponseProductInfo
import com.ssgclone.demo.dto.ResponseProducts
import com.ssgclone.demo.enums.DeliveryType
import com.ssgclone.demo.enums.ManageCompany
import com.ssgclone.demo.vo.ProductParseVo
import kotlin.jvm.Throws

interface IProductService {
    @Throws(Exception::class)
    fun saveProduct(dto: ProductParseVo)

    @Throws(Exception::class)
    fun getList(
        page: Int?,
        delivery: DeliveryType?,
        company: ManageCompany?,
        search: String?,
        check: List<String>?,
    ): ResponseProducts

    @Throws(Exception::class)
    fun getDetail(id: Long): ResponseProductInfo?
}