package io.github.supplygo.modules.access.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.supplygo.modules.access.assembly.DeptAssembly;
import io.github.supplygo.modules.access.bo.DeptBO;
import io.github.supplygo.modules.access.co.DeptCO;
import io.github.supplygo.modules.access.entity.Dept;
import io.github.supplygo.modules.access.mapper.DeptMapper;
import io.github.supplygo.modules.access.service.IDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    private final DeptAssembly deptAssembly;

    @Override
    public Mono<List<DeptBO>> listDepts(DeptCO.Query query) {
        // TODO: [无法处理] listDepts 应该查询并构建部门树，而不是分页列表。
        //  这通常需要一个递归的SQL查询（如使用WITH RECURSIVE）在Mapper.xml中定义，
        //  然后在Service中调用并将结果组装成树形结构的BO列表。
//        return Mono.fromCallable(Collections::emptyList).subscribeOn(Schedulers.boundedElastic());
        return Mono.empty();
    }

    @Override
    public Mono<Page<DeptBO>> page(DeptCO.Query query) {
        return Mono.fromCallable(() -> {
            Page<Dept> page = new Page<>(query.current(), query.size());
            Map<String, Object> params = new HashMap<>();
            params.put("name", query.name());
            params.put("status", query.status());

            Page<Dept> resultPage = mapper.selectDeptPage(page, params);

            return resultPage.map(deptAssembly::toBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Void> createDept(DeptCO.Create request) {
        Dept entity = deptAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.insert(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> updateDept(DeptCO.Update request) {
        Dept entity = deptAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.update(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> removeDept(Long id) {
        // TODO: [无法处理] 删除部门前，需要检查是否存在子部门或关联用户。
        return Mono.fromRunnable(() -> mapper.deleteById(id)).subscribeOn(Schedulers.boundedElastic()).then();
    }
} 