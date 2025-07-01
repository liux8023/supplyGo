package io.github.supplygo.modules.system.service.impl;

import io.github.supplygo.modules.system.assembly.DataScopeAssembly;
import io.github.supplygo.modules.system.bo.DataScopeBO;
import io.github.supplygo.modules.system.co.DataScopeCO;
import io.github.supplygo.modules.system.entity.SysDataScope;
import io.github.supplygo.modules.system.mapper.SysDataScopeMapper;
import io.github.supplygo.modules.system.service.IDataScopeService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataScopeServiceImpl extends ServiceImpl<SysDataScopeMapper, SysDataScope> implements IDataScopeService {

    private final DataScopeAssembly dataScopeAssembly;

    @Override
    public Mono<Page<DataScopeBO>> page(DataScopeCO.Query query) {
        return Mono.fromCallable(() -> {
            Page<SysDataScope> page = new Page<>(query.current(), query.size());
            Map<String, Object> params = new HashMap<>();
            params.put("menuId", query.menuId());
            params.put("resourceCode", query.resourceCode());
            params.put("scopeName", query.scopeName());
            params.put("scopeType", query.scopeType());
            params.put("status", query.status());

            Page<SysDataScope> resultPage = mapper.selectDataScopePage(page, params);

            return resultPage.map(dataScopeAssembly::toBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }
} 