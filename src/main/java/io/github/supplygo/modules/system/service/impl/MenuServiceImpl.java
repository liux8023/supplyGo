package io.github.supplygo.modules.system.service.impl;

import io.github.supplygo.common.Status;
import io.github.supplygo.exception.ServiceException;
import io.github.supplygo.modules.system.assembly.MenuAssembly;
import io.github.supplygo.modules.system.bo.MenuBO;
import io.github.supplygo.modules.system.co.MenuCO;
import io.github.supplygo.modules.system.entity.SysMenu;
import io.github.supplygo.modules.system.mapper.SysMenuMapper;
import io.github.supplygo.modules.system.service.IMenuService;
import io.github.supplygo.utils.TreeUtils;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements IMenuService {

    private final MenuAssembly menuAssembly;

    @Override
    public Mono<List<MenuBO>> listMenus(MenuCO.Query query) {
        return Mono.fromCallable(() -> {
            QueryWrapper qw = QueryWrapper.create()
                    .where(SysMenu::getName).like(query.name(), StringUtils.hasText(query.name()))
                    .and(SysMenu::getStatus).eq(query.status(), query.status() != null)
                    .and(SysMenu::getParentId).eq(query.parentId(), query.parentId() != null)
                    .orderBy(SysMenu::getSort, true);
            List<SysMenu> menus = mapper.selectListByQuery(qw);
            //将结果构建树结构返回
            return TreeUtils.buildTree(menuAssembly.toBOList(menus),
                    MenuBO::getId,
                    MenuBO::getParentId,
                    (parent, child) -> parent.getChildren().add(child));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Page<MenuBO>> page(MenuCO.Query query) {
        return Mono.fromCallable(() -> {
            QueryWrapper qw = QueryWrapper.create()
                    .where(SysMenu::getName).like(query.name(), StringUtils.hasText(query.name()))
                    .and(SysMenu::getStatus).eq(query.status(), query.status() != null)
                    .and(SysMenu::getParentId).eq(query.parentId(), query.parentId() != null)
                    .orderBy(SysMenu::getSort, true);
            Page<SysMenu> page = new Page<>(query.current(), query.size());
            Page<SysMenu> resultPage = mapper.paginate(page, qw);
            return resultPage.map(menuAssembly::toBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Void> createMenu(MenuCO.Create request) {
        SysMenu entity = menuAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.insert(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> updateMenu(MenuCO.Update request) {
        SysMenu entity = menuAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.update(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> removeMenu(Long id) {
        if (!CollectionUtils.isEmpty(mapper.selectMenusByParentId(id))) {
            throw new ServiceException(Status.DELETE_WITH_CHILDREN_EXIST);
        }
        return Mono.fromRunnable(() -> mapper.deleteById(id)).subscribeOn(Schedulers.boundedElastic()).then();
    }


}