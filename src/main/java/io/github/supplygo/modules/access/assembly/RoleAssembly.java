package io.github.supplygo.modules.access.assembly;

import io.github.supplygo.modules.access.bo.RoleBO;
import io.github.supplygo.modules.access.co.RoleCO;
import io.github.supplygo.modules.access.entity.Role;
import io.github.supplygo.modules.access.ro.RoleRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleAssembly {

    // CO -> Entity
    Role toEntity(RoleCO.Create co);
    Role toEntity(RoleCO.Update co);

    // Entity -> BO
    RoleBO toBO(Role entity);

    // BO -> RO
    RoleRO.Detail toDetailRO(RoleBO bo);
    RoleRO.ListItem toListItemRO(RoleBO bo);
    List<RoleRO.ListItem> toListItemROList(List<RoleBO> bos);
} 