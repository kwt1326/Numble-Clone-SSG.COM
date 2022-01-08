package com.ssgclone.demo.vo

import lombok.Data

@Data
class ProductParseVo(
    val id: Int,
    val manage_company: String,
    val title: String,
    val company: String,
    val delivery_type: List<String>,
    val product: List<ProductDetailParseVo>,
    val starRate: StarRateParseVo,
    val type: String,
)