package io.github.supplygo.modules.access.controller;

import com.mybatisflex.core.paginate.Page;
import io.github.supplygo.common.R;
import io.github.supplygo.modules.access.assembly.DataScopeAssembly;
import io.github.supplygo.modules.access.co.DataScopeCO;
import io.github.supplygo.modules.access.ro.DataScopeRO;
import io.github.supplygo.modules.access.service.IDataScopeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system/data-scope")
@RequiredArgsConstructor
@Tag(name = "数据权限管理")
public class DataScopeController {

    private final IDataScopeService dataScopeService;
    private final DataScopeAssembly dataScopeAssembly;

    @GetMapping("/page")
    @Operation(summary = "分页查询数据权限")
    public Mono<R<Page<DataScopeRO.ListItem>>> page(DataScopeCO.Query query) {
        return dataScopeService.page(query)
                .map(page -> page.map(dataScopeAssembly::toItemRO))
                .map(R::ok);
    }
} 