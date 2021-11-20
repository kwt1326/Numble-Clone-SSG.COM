package com.ssgclone.demo.domain

import org.intellij.lang.annotations.Identifier
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ClothesColors(
    @Id @Identifier
    val id: Long,

    val color: String
)
