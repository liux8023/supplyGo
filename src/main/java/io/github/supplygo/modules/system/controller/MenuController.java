package io.github.supplygo.modules.system.controller;

import io.github.supplygo.common.R;
import io.github.supplygo.modules.system.assembly.MenuAssembly;
import io.github.supplygo.modules.system.co.MenuCO;
import io.github.supplygo.modules.system.ro.MenuRO;
import io.github.supplygo.modules.system.service.IMenuService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
@Tag(name = "菜单管理")
public class MenuController {

    private final IMenuService menuService;
    private final MenuAssembly menuAssembly;

    @GetMapping("/list")
    @Operation(summary = "获取菜单列表")
    public Mono<R<List<MenuRO.ListItem>>> list(MenuCO.Query query) {
        return menuService.listMenus(query)
                .map(menuAssembly::toListItemROList)
                .map(R::ok);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜单")
    public Mono<R<Page<MenuRO.ListItem>>> page(MenuCO.Query query) {
        return menuService.page(query)
                .map(page -> page.map(menuAssembly::toListItemRO))
                .map(R::ok);
    }

    @PostMapping
    @Operation(summary = "创建菜单")
    public Mono<R<Void>> create(@RequestBody MenuCO.Create request) {
        return menuService.createMenu(request).thenReturn(R.okMessage("创建菜单成功"));
    }

    @PutMapping
    @Operation(summary = "更新菜单")
    public Mono<R<Void>> update(@RequestBody MenuCO.Update request) {
        return menuService.updateMenu(request).thenReturn(R.okMessage("更新菜单成功"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜单")
    public Mono<R<Void>> remove(@PathVariable Long id) {
        return menuService.removeMenu(id).thenReturn(R.okMessage("删除菜单成功"));
    }
} 