package io.github.supplygo.modules.access.assembly;

import io.github.supplygo.modules.access.bo.MenuBO;
import io.github.supplygo.modules.access.co.MenuCO;
import io.github.supplygo.modules.access.entity.Menu;
import io.github.supplygo.modules.access.ro.MenuRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuAssembly {

    // CO -> Entity
    Menu toEntity(MenuCO.Create co);
    Menu toEntity(MenuCO.Update co);

    // Entity -> BO
    MenuBO toBO(Menu entity);
    List<MenuBO> toBOList(List<Menu> entities);

    // BO -> RO
    MenuRO.ListItem toListItemRO(MenuBO bo);
    List<MenuRO.ListItem> toListItemROList(List<MenuBO> bos);
} 