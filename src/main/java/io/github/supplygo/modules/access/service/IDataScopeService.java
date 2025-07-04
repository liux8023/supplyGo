package io.github.supplygo.modules.access.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.supplygo.modules.access.bo.DataScopeBO;
import io.github.supplygo.modules.access.co.DataScopeCO;
import io.github.supplygo.modules.access.entity.DataScope;
import reactor.core.publisher.Mono;

public interface IDataScopeService extends IService<DataScope> {

    Mono<Page<DataScopeBO>> page(DataScopeCO.Query query);
} 