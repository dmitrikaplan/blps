package ru.kaplaan.authserver.domain.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("role_privilege")
class RolePrivilege() {

    @Id
    lateinit var pk: PK

    constructor(role: Role, privilege: Privilege): this(){
        pk = PK(role, privilege)
    }

    data class PK(val role: Role, val privilege: Privilege)
}