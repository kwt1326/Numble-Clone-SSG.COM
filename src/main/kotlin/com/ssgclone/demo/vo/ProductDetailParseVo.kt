package com.ssgclone.demo.vo

import lombok.Data

@Data
class ProductDetailParseVo(
    val id: Int,
    val title: String,
    val price: String,
    val sale: SaleParseVo,
    val thumbnail_url: String,
    val detailpage_url: String,
    val clothes_option: ClothesOptionParseVo,
) {}