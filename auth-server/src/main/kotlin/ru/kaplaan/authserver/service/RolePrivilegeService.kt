package ru.kaplaan.authserver.service

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.entity.user.Privilege
import ru.kaplaan.authserver.domain.entity.user.Role

@Service
interface RolePrivilegeService {
    fun getAllPrivilegesByRole(role: Role): List<Privilege>
}