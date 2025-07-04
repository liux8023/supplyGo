package io.github.supplygo.modules.access.service;

import com.mybatisflex.core.service.IService;
import io.github.supplygo.modules.access.bo.MenuBO;
import io.github.supplygo.modules.access.co.MenuCO;
import io.github.supplygo.modules.access.entity.Menu;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMenuService extends IService<Menu> {

    Mono<List<MenuBO>> listMenus(MenuCO.Query query);

    Mono<Void> createMenu(MenuCO.Create request);

    Mono<Void> updateMenu(MenuCO.Update request);

    Mono<Void> removeMenu(Long id);
} 