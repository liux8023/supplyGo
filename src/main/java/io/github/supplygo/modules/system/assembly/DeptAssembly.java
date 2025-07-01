package io.github.supplygo.modules.system.assembly;

import io.github.supplygo.modules.system.bo.DeptBO;
import io.github.supplygo.modules.system.co.DeptCO;
import io.github.supplygo.modules.system.entity.SysDept;
import io.github.supplygo.modules.system.ro.DeptRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeptAssembly {

    // CO -> Entity
    SysDept toEntity(DeptCO.Create co);
    SysDept toEntity(DeptCO.Update co);

    // Entity -> BO
    DeptBO toBO(SysDept entity);
    List<DeptBO> toBOList(List<SysDept> entities);

    // BO -> RO
    DeptRO.ListItem toListItemRO(DeptBO bo);
    List<DeptRO.ListItem> toListItemROList(List<DeptBO> bos);
} 