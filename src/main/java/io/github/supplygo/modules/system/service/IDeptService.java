package io.github.supplygo.modules.system.service;

import io.github.supplygo.modules.system.bo.DeptBO;
import io.github.supplygo.modules.system.co.DeptCO;
import io.github.supplygo.modules.system.entity.SysDept;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IDeptService extends IService<SysDept> {

    Mono<List<DeptBO>> listDepts(DeptCO.Query query);

    Mono<Page<DeptBO>> page(DeptCO.Query query);

    Mono<Void> createDept(DeptCO.Create request);

    Mono<Void> updateDept(DeptCO.Update request);

    Mono<Void> removeDept(Long id);
} 