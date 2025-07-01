package io.github.supplygo.modules.system.controller;

import io.github.supplygo.common.R;
import io.github.supplygo.modules.system.assembly.DeptAssembly;
import io.github.supplygo.modules.system.co.DeptCO;
import io.github.supplygo.modules.system.ro.DeptRO;
import io.github.supplygo.modules.system.service.IDeptService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/system/dept")
@RequiredArgsConstructor
@Tag(name = "部门管理")
public class DeptController {

    private final IDeptService deptService;
    private final DeptAssembly deptAssembly;

    @GetMapping("/list")
    @Operation(summary = "获取部门列表")
    public Mono<R<List<DeptRO.ListItem>>> list(DeptCO.Query query) {
        return deptService.listDepts(query)
                .map(deptAssembly::toListItemROList)
                .map(R::ok);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询部门")
    public Mono<R<Page<DeptRO.ListItem>>> page(DeptCO.Query query) {
        return deptService.page(query)
                .map(page -> page.map(deptAssembly::toListItemRO))
                .map(R::ok);
    }

    @PostMapping
    @Operation(summary = "创建部门")
    public Mono<R<Void>> create(@RequestBody DeptCO.Create request) {
        return deptService.createDept(request).thenReturn(R.okMessage("创建部门成功"));
    }

    @PutMapping
    @Operation(summary = "更新部门")
    public Mono<R<Void>> update(@RequestBody DeptCO.Update request) {
        return deptService.updateDept(request).thenReturn(R.okMessage("更新部门成功"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门")
    public Mono<R<Void>> remove(@PathVariable Long id) {
        return deptService.removeDept(id).thenReturn(R.okMessage("删除部门成功"));
    }
} 