package io.github.supplygo.modules.system.service;

import io.github.supplygo.modules.system.bo.DataScopeBO;
import io.github.supplygo.modules.system.co.DataScopeCO;
import io.github.supplygo.modules.system.entity.SysDataScope;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import reactor.core.publisher.Mono;

public interface IDataScopeService extends IService<SysDataScope> {

    Mono<Page<DataScopeBO>> page(DataScopeCO.Query query);
} 