package com.ssgclone.demo.service

import com.querydsl.core.BooleanBuilder
import com.ssgclone.demo.domain.*
import com.ssgclone.demo.dto.ResponseProductElement
import com.ssgclone.demo.dto.ResponseProductInfo
import com.ssgclone.demo.dto.ResponseProducts
import com.ssgclone.demo.enums.DeliveryType
import com.ssgclone.demo.enums.ManageCompany
import com.ssgclone.demo.enums.ProductType
import com.ssgclone.demo.repository.IProductRepository
import com.ssgclone.demo.repository.IProductReviewRepository
import com.ssgclone.demo.utils.ProductUtils
import com.ssgclone.demo.vo.ProductParseVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.jvm.Throws

@Service
class ProductService(
    @Autowired private val productRepository: IProductRepository,
    @Autowired private val productReviewRepository: IProductReviewRepository,
    @Autowired private val productUtils: ProductUtils,
) : IProductService {
    // Json Data Init save
    override fun saveProduct(dto: ProductParseVo) {
        productRepository.save(
            Products(
                dto.title, dto.company,
                ProductType.valueOf(dto.type),
                ManageCompany.valueOf(dto.manage_company),
                dto.delivery_type.stream().map {
                    DeliveryType.valueOf(it)
                }.collect(Collectors.toSet()),
                dto.starRate.rate, dto.starRate.rateNum,
                dto.product.stream().map {
                    ProductDetail(
                        it.title,
                        it.price.toInt(),
                        it.sale.boolean,
                        it.sale.sale_price.toInt(),
                        it.thumbnail_url,
                        it.detailpage_url,
                        it.clothes_option.boolean,
                        it.clothes_option.size,
                        it.clothes_option.color
                    )
                }.collect(Collectors.toList())
            )
        )
    }

    override fun getList(
        page: Int?,
        delivery: DeliveryType?,
        company: ManageCompany?,
        search: String?,
        check: List<String>?,
    ): ResponseProducts {
        val booleanBuilder = BooleanBuilder()
        val qEntity: QProducts = QProducts.products

        val sort = Sort.by("id").descending()
        val pageRequest: PageRequest = PageRequest.of(
            if (page != null) page - 1 else 0,
            20, sort
        )

        // 배송타입이 새벽배송일 경우 조건을 우선시 한다.
        if (delivery == DeliveryType.early_morning) {
            booleanBuilder.and(qEntity.delivery_type.contains(delivery))
        } else if (company != null) {
            // 새벽배송이 아니고 계열사만 있을 경우
            booleanBuilder.and(qEntity.manage_company.eq(company))
        }

        if (search != null) booleanBuilder.and(qEntity.title.contains(search))

        check?.forEach {
            when (it) {
                "emart_ssg" -> {
                    booleanBuilder.and(qEntity.manage_company.eq(ManageCompany.emart))
                    booleanBuilder.and(qEntity.delivery_type.contains(DeliveryType.ssg))
                }
                "early_morning" -> booleanBuilder.and(qEntity.delivery_type.contains(DeliveryType.early_morning))
                "department" -> booleanBuilder.and(qEntity.manage_company.eq(ManageCompany.shinsegae_departmentstore))
            }
        }

        val pageList: Page<Products> = productRepository.findAll(booleanBuilder, pageRequest)
        val totalPage: Int = pageList.totalPages

        val resultList: List<ResponseProductElement> = pageList.toList().stream().map {
            val minimumPrice: Int = it.product_details.minOf { detail -> detail.price }
            var deliveryTypeArr: MutableSet<DeliveryType> = it.delivery_type

            // 새벽배송일 경우 새벽배송 타입을 제일 앞으로 보낸다.
            if (delivery == DeliveryType.early_morning) {
                val types = deliveryTypeArr.toList();
                types.forEachIndexed {
                    index, deliveryType ->
                        if (deliveryType == DeliveryType.early_morning) {
                            types[0].also { types[index] }
                        }
                }

                deliveryTypeArr = types.toMutableSet()
            }

            val deliveryTypeParsedArr = deliveryTypeArr.stream().map { productUtils.parseDeliveryType(it) }.collect(Collectors.toList())

            ResponseProductElement(
                it.id, it.title, it.company, it.product_details[0].thumbnail_url,
                it.type,
                productUtils.parseManageCompanyType(it.manage_company),
                deliveryTypeParsedArr,
                it.star_rate, it.star_rate_num,
                productUtils.comma(minimumPrice),
                it.product_details.size > 1
            )
        }.collect(Collectors.toList())

        return ResponseProducts(
            resultList,
            totalPage,
            company?.toString() ?: "SSG.COM",
            search,
            check?: listOf(),
        )
    }

    override fun getDetail(id: Long): ResponseProductInfo? {
        val product: Products? = productRepository.findProductsById(id)
        println(product?.title)

        if (product != null) {
            val booleanBuilder = BooleanBuilder()
//            val qEntity: QProductReview = QProducts.products

//            productReviewRepository.findAll()

            val minimumPrice: Int = product.product_details.minOf { detail -> detail.price }
            val totalPrice: Int? = if (product.product_details.size == 1) {
                
            } else null

            return ResponseProductInfo(
                product.title,
                productUtils.parseManageCompanyType(product.manage_company),
                product.company,
                product.star_rate,
                product.star_rate_num,
                minimumPrice,
                pro
            )
        }
        return null
    }
}