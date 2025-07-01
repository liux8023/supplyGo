package io.github.supplygo.modules.system.assembly;

import io.github.supplygo.modules.system.bo.MenuBO;
import io.github.supplygo.modules.system.co.MenuCO;
import io.github.supplygo.modules.system.entity.SysMenu;
import io.github.supplygo.modules.system.ro.MenuRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuAssembly {

    // CO -> Entity
    SysMenu toEntity(MenuCO.Create co);
    SysMenu toEntity(MenuCO.Update co);

    // Entity -> BO
    MenuBO toBO(SysMenu entity);
    List<MenuBO> toBOList(List<SysMenu> entities);

    // BO -> RO
    MenuRO.ListItem toListItemRO(MenuBO bo);
    List<MenuRO.ListItem> toListItemROList(List<MenuBO> bos);
} 