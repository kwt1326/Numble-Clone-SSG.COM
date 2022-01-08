package com.ssgclone.demo.repository

import com.ssgclone.demo.domain.ProductReview
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository

interface IProductReviewRepository: CrudRepository<ProductReview, Long>, QuerydslPredicateExecutor<ProductReview> {
}