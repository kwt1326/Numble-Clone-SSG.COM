package com.ssgclone.demo.service

import com.ssgclone.demo.domain.Products
import com.ssgclone.demo.dto.RequestSaveProduct
import com.ssgclone.demo.dto.ResponseProducts
import com.ssgclone.demo.repository.IProductDetailRepository
import com.ssgclone.demo.repository.IProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired private val productRepository: IProductRepository,
    @Autowired private val productDetailRepository: IProductDetailRepository
): IProductService {
    override fun saveProduct(dto: RequestSaveProduct) {
        productRepository.save(Products(
            dto.title, dto.company, dto.type,
            dto.manage_company, dto.delivery_type,
            dto.star_rate, dto.star_rate_num
        ))
    }

    override fun getList(): List<ResponseProducts> {
        TODO("Not yet implemented")
    }
}