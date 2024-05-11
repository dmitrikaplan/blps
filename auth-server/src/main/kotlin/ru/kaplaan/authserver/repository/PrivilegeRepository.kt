package ru.kaplaan.authserver.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.authserver.domain.entity.user.Privilege

@Repository
interface PrivilegeRepository: CrudRepository<Privilege, Long> {

    @Query("select * from role_privilege where role_name = :roleName")
    fun findAllPrivilegesByRoleName(roleName: String): List<Privilege>
}