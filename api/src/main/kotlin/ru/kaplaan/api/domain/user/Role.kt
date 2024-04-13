package ru.kaplaan.api.domain.user

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority{
    ROLE_USER, ROLE_COMPANY;
    override fun getAuthority(): String {
        return this.name
    }
}