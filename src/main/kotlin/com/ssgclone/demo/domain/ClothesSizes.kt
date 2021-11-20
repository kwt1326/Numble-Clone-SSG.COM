package com.ssgclone.demo.domain

import org.intellij.lang.annotations.Identifier
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ClothesSizes(
    @Id @Identifier
    val id: Long,

    val size: String
)
