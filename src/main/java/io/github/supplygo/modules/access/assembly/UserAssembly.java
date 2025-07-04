package io.github.supplygo.modules.access.assembly;

import io.github.supplygo.modules.access.bo.UserBO;
import io.github.supplygo.modules.access.co.UserCO;
import io.github.supplygo.modules.access.entity.User;
import io.github.supplygo.modules.access.ro.UserRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAssembly {

    // CO -> Entity
    User toEntity(UserCO.Create co);
    User toEntity(UserCO.Update co);

    // Entity -> BO
    // This now only maps direct fields.
    // Associations (deptName, roles) are handled in the Service layer.
    UserBO toBO(User entity);

    // BO -> RO
    UserRO.Detail toDetailRO(UserBO bo);
    UserRO.ListItem toListItemRO(UserBO bo);
    List<UserRO.ListItem> toListItemROList(List<UserBO> bos);

} 