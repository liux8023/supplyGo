package io.github.supplygo.modules.system.assembly;

import io.github.supplygo.modules.system.bo.DataScopeBO;
import io.github.supplygo.modules.system.entity.SysDataScope;
import io.github.supplygo.modules.system.ro.DataScopeRO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataScopeAssembly {

    DataScopeBO toBO(SysDataScope entity);

    DataScopeRO.ListItem toItemRO(DataScopeBO bo);
} 