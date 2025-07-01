package io.github.supplygo.modules.system.assembly;

import io.github.supplygo.modules.system.bo.UserBO;
import io.github.supplygo.modules.system.co.UserCO;
import io.github.supplygo.modules.system.entity.SysUser;
import io.github.supplygo.modules.system.ro.UserRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAssembly {

    // CO -> Entity
    SysUser toEntity(UserCO.Create co);
    SysUser toEntity(UserCO.Update co);

    // Entity -> BO
    // This now only maps direct fields.
    // Associations (deptName, roles) are handled in the Service layer.
    UserBO toBO(SysUser entity);

    // BO -> RO
    UserRO.Detail toDetailRO(UserBO bo);
    UserRO.ListItem toListItemRO(UserBO bo);
    List<UserRO.ListItem> toListItemROList(List<UserBO> bos);

} 