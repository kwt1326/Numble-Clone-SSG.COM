package com.ssgclone.demo.domain

import com.ssgclone.demo.enums.Roles
import lombok.Getter
import lombok.Setter
import javax.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.stream.Collectors

@Getter
@Setter
@Entity
@Table(name = "users")
class Users (

    @Column(unique = true)
    var username: String,

    @Column(unique = true)
    var address: String,

    @Column(unique = true, name = "phone_number")
    var phoneNumber: String,

    var realname: String,

    var password: String,

    var email: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<Roles>

) : BaseEntity() {

    internal fun getAuthorities(): User {
        val authorities = this.roles.stream().map {
                role -> SimpleGrantedAuthority("ROLE_$role")
        }.collect(Collectors.toSet())

        return User(
            this.username,
            this.password,
            authorities
        )
    }
}