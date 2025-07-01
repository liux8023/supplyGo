package io.github.supplygo.modules.system.assembly;

import io.github.supplygo.modules.system.bo.PostBO;
import io.github.supplygo.modules.system.co.PostCO;
import io.github.supplygo.modules.system.entity.SysPost;
import io.github.supplygo.modules.system.ro.PostRO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostAssembly {

    // CO -> Entity
    SysPost toEntity(PostCO.Create co);
    SysPost toEntity(PostCO.Update co);

    // Entity -> BO
    PostBO toBO(SysPost entity);

    // BO -> RO
    PostRO.ListItem toItemRO(PostBO bo);
} 