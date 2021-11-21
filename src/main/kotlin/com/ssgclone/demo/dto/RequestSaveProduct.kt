package com.ssgclone.demo.dto

import com.ssgclone.demo.enums.DeliveryType
import com.ssgclone.demo.enums.ManageCompany
import com.ssgclone.demo.enums.ProductType

data class RequestSaveProduct(
    var title: String,
    var company: String,
    var type: ProductType,
    var manage_company: ManageCompany,
    var delivery_type: DeliveryType,
    var star_rate: Int,
    var star_rate_num: Int,
)