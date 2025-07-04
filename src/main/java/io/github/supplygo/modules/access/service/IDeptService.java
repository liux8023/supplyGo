package io.github.supplygo.modules.access.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.supplygo.modules.access.bo.DeptBO;
import io.github.supplygo.modules.access.co.DeptCO;
import io.github.supplygo.modules.access.entity.Dept;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IDeptService extends IService<Dept> {

    Mono<List<DeptBO>> listDepts(DeptCO.Query query);

    Mono<Page<DeptBO>> page(DeptCO.Query query);

    Mono<Void> createDept(DeptCO.Create request);

    Mono<Void> updateDept(DeptCO.Update request);

    Mono<Void> removeDept(Long id);
} 