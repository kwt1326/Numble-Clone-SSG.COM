package com.ssgclone.demo.utils

import com.ssgclone.demo.enums.DeliveryType
import com.ssgclone.demo.enums.ManageCompany
import org.springframework.stereotype.Component

@Component
class ProductUtils {
    fun parseManageCompanyType(type: ManageCompany): String {
        return when (type) {
            ManageCompany.emart -> "이마트"
            ManageCompany.shinsegae_mall -> "신세계몰"
            ManageCompany.shinsegae_departmentstore -> "신세계백화점"
        }
    }

    fun parseDeliveryType(type: DeliveryType): String {
        return when (type) {
            DeliveryType.normal -> "일반배송"
            DeliveryType.ssg -> "쓱배송"
            DeliveryType.early_morning -> "새벽배송"
        }
    }

    fun comma(price: Int): String {
        return "%,d".format(price)
    }
}