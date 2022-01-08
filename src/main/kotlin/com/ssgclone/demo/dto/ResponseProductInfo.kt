package com.ssgclone.demo.dto

data class ResponseProductInfo (
    val title: String,
    val manageCompany: String,
    val company: String,
    val starLate: Float,
    val starLateNum: Int,
    val minimumPrice: Int, // 상품 여러개인 경우에 사용
    val totalPrice: Int, // 세일가 적용가, 상품이 한개일 경우에만 사용
    val price: Int?, // 할인할 경우에만 전달, 상품이 한개일 경우에만 사용
    val deliveryType: String?, // 배송정보, 쓱 & 새벽일 경우 새벽배송, 쓱만 가능 -> 쓱배송, 일반배송 표시 -> null
    val detailImg: String?,
    val detail: List<ResponseProductDetail>?,
)