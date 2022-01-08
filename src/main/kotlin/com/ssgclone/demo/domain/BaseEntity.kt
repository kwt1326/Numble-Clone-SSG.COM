package com.ssgclone.demo.domain

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Getter
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @JsonFormat(pattern = "yyyy-MM-dd")
    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = createdAt

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "removed_at")
    var removedAt: LocalDateTime? = null
}