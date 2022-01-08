package com.ssgclone.demo.domain

import com.ssgclone.demo.enums.DeliveryType
import com.ssgclone.demo.enums.ManageCompany
import com.ssgclone.demo.enums.ProductType
import javax.persistence.*

@Entity
@Table(name = "products")
class Products (
    var title: String,

    var company: String,

    var type: ProductType,

    @Column(name = "manage_company")
    var manage_company: ManageCompany,

    @Column(name = "delivery_type")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var delivery_type: MutableSet<DeliveryType>,

    @Column(name = "star_rate")
    var star_rate: Float,

    @Column(name = "star_rate_num")
    var star_rate_num: Int,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_detail_id")
    var product_details: List<ProductDetail>,
): BaseEntity()