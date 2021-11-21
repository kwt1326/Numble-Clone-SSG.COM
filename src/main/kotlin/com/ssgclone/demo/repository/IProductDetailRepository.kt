package com.ssgclone.demo.repository

import com.ssgclone.demo.domain.ProductDetail
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository

interface IProductDetailRepository: CrudRepository<ProductDetail, Long>, QuerydslPredicateExecutor<ProductDetail> {
}