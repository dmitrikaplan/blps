package ru.kaplaan.authserver.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.authserver.domain.entity.user.Privilege
import ru.kaplaan.authserver.domain.entity.user.RolePrivilege

@Repository
interface PrivilegeRepository: CrudRepository<RolePrivilege, Long> {

    @Query("select privilege from role_privilege where role = :roleName")
    fun findAllPrivilegesByRoleName(roleName: String): List<Privilege>
}