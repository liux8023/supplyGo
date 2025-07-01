package io.github.supplygo.modules.system.service;

import io.github.supplygo.modules.system.bo.MenuBO;
import io.github.supplygo.modules.system.co.MenuCO;
import io.github.supplygo.modules.system.entity.SysMenu;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMenuService extends IService<SysMenu> {

    Mono<List<MenuBO>> listMenus(MenuCO.Query query);

    Mono<Page<MenuBO>> page(MenuCO.Query query);

    Mono<Void> createMenu(MenuCO.Create request);

    Mono<Void> updateMenu(MenuCO.Update request);

    Mono<Void> removeMenu(Long id);
} 