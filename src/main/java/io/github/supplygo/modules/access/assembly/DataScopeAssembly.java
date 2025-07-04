package io.github.supplygo.modules.access.assembly;

import io.github.supplygo.modules.access.bo.DataScopeBO;
import io.github.supplygo.modules.access.entity.DataScope;
import io.github.supplygo.modules.access.ro.DataScopeRO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataScopeAssembly {

    DataScopeBO toBO(DataScope entity);

    DataScopeRO.ListItem toItemRO(DataScopeBO bo);
} 