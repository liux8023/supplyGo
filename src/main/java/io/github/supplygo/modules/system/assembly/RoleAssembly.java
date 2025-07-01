package io.github.supplygo.modules.system.assembly;

import io.github.supplygo.modules.system.bo.RoleBO;
import io.github.supplygo.modules.system.co.RoleCO;
import io.github.supplygo.modules.system.entity.SysRole;
import io.github.supplygo.modules.system.ro.RoleRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleAssembly {

    // CO -> Entity
    SysRole toEntity(RoleCO.Create co);
    SysRole toEntity(RoleCO.Update co);

    // Entity -> BO
    RoleBO toBO(SysRole entity);

    // BO -> RO
    RoleRO.Detail toDetailRO(RoleBO bo);
    RoleRO.ListItem toListItemRO(RoleBO bo);
    List<RoleRO.ListItem> toListItemROList(List<RoleBO> bos);
} 