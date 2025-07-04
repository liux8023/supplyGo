package io.github.supplygo.modules.access.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.supplygo.modules.access.assembly.RoleAssembly;
import io.github.supplygo.modules.access.bo.RoleBO;
import io.github.supplygo.modules.access.co.RoleCO;
import io.github.supplygo.modules.access.entity.Role;
import io.github.supplygo.modules.access.mapper.RoleMapper;
import io.github.supplygo.modules.access.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final RoleAssembly roleAssembly;

    @Override
    public Mono<Page<RoleBO>> page(RoleCO.Query query) {
        return Mono.fromCallable(() -> {
            Page<Role> page = new Page<>(query.current(), query.size());
            Map<String, Object> params = new HashMap<>();
            params.put("name", query.name());
            params.put("code", query.code());

            Page<Role> resultPage = mapper.selectRolePage(page, params);
            return resultPage.map(this::fillAssociatedDataToBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<RoleBO> getRoleById(Long id) {
        return Mono.fromCallable(() -> mapper.selectOneById(id))
                .map(this::fillAssociatedDataToBO)
                .subscribeOn(Schedulers.boundedElastic());
    }
    
    private RoleBO fillAssociatedDataToBO(Role role) {
        if (role == null) {
            return null;
        }
        RoleBO bo = roleAssembly.toBO(role);

        // TODO: [无法处理] 需要实现查询角色关联的用户数量的逻辑.
        // bo.setUserCount(userCount);
        
        // TODO: [无法处理] 需要实现查询角色关联的菜单ID列表和部门ID列表的逻辑.
        // bo.setMenuIds(menuIds);
        // bo.setDeptIds(deptIds);
        
        // TODO: [无法处理] 需要实现根据 dataScope 枚举值获取其名称的逻辑.
        // bo.setDataScopeName(dataScopeName);

        return bo;
    }

    @Override
    @Transactional
    public Mono<Void> createRole(RoleCO.Create request) {
        Role entity = roleAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.insert(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> updateRole(RoleCO.Update request) {
        Role entity = roleAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.update(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> removeRole(Long id) {
        // TODO: [无法处理] 删除角色前，需要检查并处理与用户的关联关系。
        return Mono.fromRunnable(() -> mapper.deleteById(id)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> assignPermission(RoleCO.AssignPermission request) {
        // TODO: [无法处理] 需要实现角色权限分配的完整逻辑.
        // 这通常涉及删除旧的关联，然后插入新的关联（角色-菜单，角色-部门）。
        return Mono.empty();
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return mapper.selectRolesByUserId(userId);
    }
} 