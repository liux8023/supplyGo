package io.github.supplygo.modules.system.service;

import io.github.supplygo.modules.system.bo.RoleBO;
import io.github.supplygo.modules.system.co.RoleCO;
import io.github.supplygo.modules.system.entity.SysRole;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IRoleService extends IService<SysRole> {

    Mono<Page<RoleBO>> page(RoleCO.Query query);

    Mono<RoleBO> getRoleById(Long id);

    Mono<Void> createRole(RoleCO.Create request);

    Mono<Void> updateRole(RoleCO.Update request);

    Mono<Void> removeRole(Long id);
    
    Mono<Void> assignPermission(RoleCO.AssignPermission request);

    List<SysRole> findRolesByUserId(Long userId);
} 