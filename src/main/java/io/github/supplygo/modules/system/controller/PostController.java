package io.github.supplygo.modules.system.controller;

import io.github.supplygo.common.R;
import io.github.supplygo.modules.system.assembly.PostAssembly;
import io.github.supplygo.modules.system.co.PostCO;
import io.github.supplygo.modules.system.ro.PostRO;
import io.github.supplygo.modules.system.service.IPostService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system/post")
@RequiredArgsConstructor
@Tag(name = "岗位管理")
public class PostController {

    private final IPostService postService;
    private final PostAssembly postAssembly;

    @GetMapping("/page")
    @Operation(summary = "分页查询岗位")
    public Mono<R<Page<PostRO.ListItem>>> page(PostCO.Query query) {
        return postService.page(query)
                .map(page -> page.map(postAssembly::toItemRO))
                .map(R::ok);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id获取岗位")
    public Mono<R<PostRO.ListItem>> getById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(postAssembly::toItemRO)
                .map(R::ok);
    }

    @PostMapping
    @Operation(summary = "创建岗位")
    public Mono<R<Void>> create(@RequestBody PostCO.Create request) {
        return postService.createPost(request).thenReturn(R.okMessage("创建岗位成功"));
    }

    @PutMapping
    @Operation(summary = "更新岗位")
    public Mono<R<Void>> update(@RequestBody PostCO.Update request) {
        return postService.updatePost(request).thenReturn(R.okMessage("更新岗位成功"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除岗位")
    public Mono<R<Void>> remove(@PathVariable Long id) {
        return postService.removePost(id).thenReturn(R.okMessage("删除岗位成功"));
    }
} 