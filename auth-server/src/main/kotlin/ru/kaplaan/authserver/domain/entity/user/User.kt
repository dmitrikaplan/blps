package ru.kaplaan.authserver.domain.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Table(name = "users")
class User(): UserDetails{

    @Id
    @Column("user_id")
    var id: Long? = null

    lateinit var email: String

    private lateinit var username: String

    private lateinit var password: String

    var activated: Boolean = false

    var activationCode: String? = null

    lateinit var role: Role

    lateinit var privileges: List<Privilege>

    constructor(
        email: String,
        username: String,
        password: String,
        role: Role
    ) :this() {
        this.email = email
        this.username = username
        this.password = password
        this.role = role
    }


    override fun getAuthorities(): List<GrantedAuthority> {
        return listOf(role) + privileges
    }

    override fun getPassword(): String =
        password

    fun setPassword(password: String){
        this.password = password
    }

    override fun getUsername(): String =
        username

    fun setUsername(username: String){
        this.username = username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean =
        true

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return activated
    }

    override fun toString(): String =
        "User(id = $id, email = $email, username = $username, password = $password, role = $role)"
}