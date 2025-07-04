package io.github.supplygo.modules.access.controller;

import com.mybatisflex.core.paginate.Page;
import io.github.supplygo.common.R;
import io.github.supplygo.modules.access.assembly.RoleAssembly;
import io.github.supplygo.modules.access.co.RoleCO;
import io.github.supplygo.modules.access.ro.RoleRO;
import io.github.supplygo.modules.access.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
@Tag(name = "角色管理")
public class RoleController {

    private final IRoleService roleService;
    private final RoleAssembly roleAssembly;

    @GetMapping("/page")
    @Operation(summary = "分页查询角色")
    public Mono<R<Page<RoleRO.ListItem>>> page(RoleCO.Query query) {
        return roleService.page(query)
                .map(page -> page.map(roleAssembly::toListItemRO))
                .map(R::ok);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询角色")
    public Mono<R<RoleRO.Detail>> getById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(roleAssembly::toDetailRO)
                .map(R::ok);
    }

    @PostMapping
    @Operation(summary = "创建角色")
    public Mono<R<Void>> create(@RequestBody RoleCO.Create request) {
        return roleService.createRole(request).thenReturn(R.okMessage("创建角色成功"));
    }

    @PutMapping
    @Operation(summary = "更新角色")
    public Mono<R<Void>> update(@RequestBody RoleCO.Update request) {
        return roleService.updateRole(request).thenReturn(R.okMessage("更新角色成功"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色")
    public Mono<R<Void>> remove(@PathVariable Long id) {
        return roleService.removeRole(id).thenReturn(R.okMessage("删除角色成功"));
    }
    
    @PostMapping("/permission")
    @Operation(summary = "为角色分配权限")
    public Mono<R<Void>> assignPermission(@RequestBody RoleCO.AssignPermission request) {
        return roleService.assignPermission(request).thenReturn(R.okMessage("分配权限成功"));
    }
} 