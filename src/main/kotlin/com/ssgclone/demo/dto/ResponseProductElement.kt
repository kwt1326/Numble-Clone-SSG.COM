package com.ssgclone.demo.dto

import com.ssgclone.demo.enums.ProductType

data class ResponseProductElement(
    val id: Long,
    var title: String,
    var company: String,
    var first_thumbnail_url: String,
    var type: ProductType,
    var manage_company: String,
    var delivery_type: List<String>,
    var star_rate: Float,
    var star_rate_num: Int,
    var minimum_price: String,
    var is_multi_options: Boolean
)