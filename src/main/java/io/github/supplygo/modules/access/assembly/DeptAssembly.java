package io.github.supplygo.modules.access.assembly;

import io.github.supplygo.modules.access.bo.DeptBO;
import io.github.supplygo.modules.access.co.DeptCO;
import io.github.supplygo.modules.access.entity.Dept;
import io.github.supplygo.modules.access.ro.DeptRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeptAssembly {

    // CO -> Entity
    Dept toEntity(DeptCO.Create co);
    Dept toEntity(DeptCO.Update co);

    // Entity -> BO
    DeptBO toBO(Dept entity);
    List<DeptBO> toBOList(List<Dept> entities);

    // BO -> RO
    DeptRO.ListItem toListItemRO(DeptBO bo);
    List<DeptRO.ListItem> toListItemROList(List<DeptBO> bos);
} 