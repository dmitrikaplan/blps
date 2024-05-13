package ru.kaplaan.authserver.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.entity.user.Privilege
import ru.kaplaan.authserver.domain.entity.user.Role
import ru.kaplaan.authserver.repository.PrivilegeRepository
import ru.kaplaan.authserver.service.RolePrivilegeService

@Service
class RolePrivilegeServiceImpl(
    private val privilegeRepository: PrivilegeRepository
): RolePrivilegeService {
    override fun getAllPrivilegesByRole(role: Role): List<Privilege> {
        return privilegeRepository.findAllPrivilegesByRoleName(role.name)
    }
}