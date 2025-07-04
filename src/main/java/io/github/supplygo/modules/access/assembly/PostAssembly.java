package io.github.supplygo.modules.access.assembly;

import io.github.supplygo.modules.access.bo.PostBO;
import io.github.supplygo.modules.access.co.PostCO;
import io.github.supplygo.modules.access.entity.Post;
import io.github.supplygo.modules.access.ro.PostRO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostAssembly {

    // CO -> Entity
    Post toEntity(PostCO.Create co);
    Post toEntity(PostCO.Update co);

    // Entity -> BO
    PostBO toBO(Post entity);

    // BO -> RO
    PostRO.ListItem toItemRO(PostBO bo);
} 