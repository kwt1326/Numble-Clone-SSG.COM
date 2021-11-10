package com.ssgclone.demo.domain

import javax.persistence.*

@Entity
@Table(name = "users")
class Users: BaseEntity() {

    @Column(unique = true, nullable = false)
    var username: String? = null;

    @Column(nullable = false)
    var realname: String? = null;

    @Column(nullable = false)
    var password: String? = null;

    @Column
    var email: String? = null;

    @Column(nullable = false, name = "phone_number")
    var phoneNumber: String? = null;


}