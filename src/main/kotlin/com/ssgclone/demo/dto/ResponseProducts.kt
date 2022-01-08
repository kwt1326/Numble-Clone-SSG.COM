package com.ssgclone.demo.dto

import com.ssgclone.demo.enums.ManageCompany

data class ResponseProducts (
    var list: List<ResponseProductElement>,
    var totalPage: Int,
    var selectCompany: String,
    var searchValue: String?,
    var check: List<String>,
)