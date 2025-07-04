package io.github.supplygo.modules.access.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.supplygo.modules.access.bo.RoleBO;
import io.github.supplygo.modules.access.co.RoleCO;
import io.github.supplygo.modules.access.entity.Role;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IRoleService extends IService<Role> {

    Mono<Page<RoleBO>> page(RoleCO.Query query);

    Mono<RoleBO> getRoleById(Long id);

    Mono<Void> createRole(RoleCO.Create request);

    Mono<Void> updateRole(RoleCO.Update request);

    Mono<Void> removeRole(Long id);
    
    Mono<Void> assignPermission(RoleCO.AssignPermission request);

    List<Role> findRolesByUserId(Long userId);
} 