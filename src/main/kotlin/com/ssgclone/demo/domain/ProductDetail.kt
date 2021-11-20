package com.ssgclone.demo.domain

import javax.persistence.*

@Entity
@Table(name = "product_detail")
class ProductDetail(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    var product_id: Products,

    var title: String,

    var price: Int,

    @Column(name = "sale_active")
    var sale_active: Boolean,

    @Column(name = "sale_price")
    var sale_price: Int,

    @Column(name = "thumbnail_url")
    var thumbnail_url: String,

    @Column(name = "detailpage_url")
    var detailpage_url: String,

    @Column(name = "is_clothes")
    var is_clothes: Boolean,

    @Column(name = "clothes_size")
    var clothes_size: String,

    @Column(name = "clothes_color")
    var clothes_color: String,

): BaseEntity()
