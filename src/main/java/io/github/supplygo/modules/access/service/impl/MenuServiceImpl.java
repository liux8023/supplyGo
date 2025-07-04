package io.github.supplygo.modules.access.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.supplygo.common.Status;
import io.github.supplygo.exception.ServiceException;
import io.github.supplygo.modules.access.assembly.MenuAssembly;
import io.github.supplygo.modules.access.bo.MenuBO;
import io.github.supplygo.modules.access.co.MenuCO;
import io.github.supplygo.modules.access.entity.Menu;
import io.github.supplygo.modules.access.mapper.MenuMapper;
import io.github.supplygo.modules.access.service.IMenuService;
import io.github.supplygo.utils.TreeUtils;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    private final MenuAssembly menuAssembly;

    @Override
    public Mono<List<MenuBO>> listMenus(MenuCO.Query query) {
        return Mono.fromCallable(() -> {
            QueryWrapper qw = QueryWrapper.create()
                    .where(Menu::getName).like(query.keyword(), StringUtils.hasText(query.keyword()))
                    .or(Menu::getCode).like(query.keyword(), StringUtils.hasText(query.keyword()))
                    .orderBy(Menu::getSort, true);
            List<Menu> menus = mapper.selectListByQuery(qw);
            //将结果构建树结构返回
            return TreeUtils.buildTree(menuAssembly.toBOList(menus),
                    MenuBO::getId,
                    MenuBO::getParentId,
                    (parent, child) -> parent.getChildren().add(child));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Void> createMenu(MenuCO.Create request) {
        Menu entity = menuAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.insert(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> updateMenu(MenuCO.Update request) {
        Menu entity = menuAssembly.toEntity(request);
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