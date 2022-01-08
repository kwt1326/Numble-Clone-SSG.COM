package com.ssgclone.demo.domain

import javax.persistence.*

@Entity
@Table(name = "product_review")
class ProductReview(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var review: String,

    var star: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    var product_id: ProductDetail
)