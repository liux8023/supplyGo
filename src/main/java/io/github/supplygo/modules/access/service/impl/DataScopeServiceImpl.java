package io.github.supplygo.modules.access.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.supplygo.modules.access.assembly.DataScopeAssembly;
import io.github.supplygo.modules.access.bo.DataScopeBO;
import io.github.supplygo.modules.access.co.DataScopeCO;
import io.github.supplygo.modules.access.entity.DataScope;
import io.github.supplygo.modules.access.mapper.DataScopeMapper;
import io.github.supplygo.modules.access.service.IDataScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataScopeServiceImpl extends ServiceImpl<DataScopeMapper, DataScope> implements IDataScopeService {

    private final DataScopeAssembly dataScopeAssembly;

    @Override
    public Mono<Page<DataScopeBO>> page(DataScopeCO.Query query) {
        return Mono.fromCallable(() -> {
            Page<DataScope> page = new Page<>(query.current(), query.size());
            Map<String, Object> params = new HashMap<>();
            params.put("menuId", query.menuId());
            params.put("resourceCode", query.resourceCode());
            params.put("scopeName", query.scopeName());
            params.put("scopeType", query.scopeType());
            params.put("status", query.status());

            Page<DataScope> resultPage = mapper.selectDataScopePage(page, params);

            return resultPage.map(dataScopeAssembly::toBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }
} 