package com.ssgclone.demo.repository

import com.ssgclone.demo.domain.Products
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository

interface IProductRepository: CrudRepository<Products, Long>, QuerydslPredicateExecutor<Products> {
    fun findProductsById(id: Long): Products?
}