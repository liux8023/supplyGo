package io.github.supplygo.modules.access.controller;

import com.mybatisflex.core.paginate.Page;
import io.github.supplygo.common.R;
import io.github.supplygo.modules.access.assembly.UserAssembly;
import io.github.supplygo.modules.access.co.UserCO;
import io.github.supplygo.modules.access.ro.UserRO;
import io.github.supplygo.modules.access.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
@Tag(name = "用户管理")
public class UserController {

    private final IUserService userService;
    private final UserAssembly userAssembly;

    @GetMapping("/page")
    @Operation(summary = "分页查询用户")
    public Mono<R<Page<UserRO.ListItem>>> page(UserCO.Query query) {
        return userService.page(query)
                .map(page -> page.map(userAssembly::toListItemRO))
                .map(R::ok);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户")
    public Mono<R<UserRO.Detail>> getById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(userAssembly::toDetailRO)
                .map(R::ok);
    }

    @PostMapping
    @Operation(summary = "创建用户")
    public Mono<R<Void>> create(@RequestBody UserCO.Create request) {
        return userService.createUser(request).thenReturn(R.okMessage("创建用户成功"));
    }

    @PutMapping
    @Operation(summary = "更新用户")
    public Mono<R<Void>> update(@RequestBody UserCO.Update request) {
        return userService.updateUser(request).thenReturn(R.okMessage("更新用户成功"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public Mono<R<Void>> remove(@PathVariable Long id) {
        return userService.removeUser(id).thenReturn(R.okMessage("删除用户成功"));
    }
} 