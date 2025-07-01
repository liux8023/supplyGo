package io.github.supplygo.modules.system.service.impl;

import io.github.supplygo.modules.system.assembly.PostAssembly;
import io.github.supplygo.modules.system.bo.PostBO;
import io.github.supplygo.modules.system.co.PostCO;
import io.github.supplygo.modules.system.entity.SysPost;
import io.github.supplygo.modules.system.mapper.SysPostMapper;
import io.github.supplygo.modules.system.service.IPostService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements IPostService {

    private final PostAssembly postAssembly;

    @Override
    public Mono<Page<PostBO>> page(PostCO.Query query) {
        return Mono.fromCallable(() -> {
            Page<SysPost> page = new Page<>(query.current(), query.size());
            Map<String, Object> params = new HashMap<>();
            params.put("name", query.name());
            params.put("code", query.code());
            params.put("status", query.status());

            Page<SysPost> resultPage = mapper.selectPostPage(page, params);
            return resultPage.map(postAssembly::toBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<PostBO> getPostById(Long id) {
        return Mono.fromCallable(() -> mapper.selectOneById(id))
                .map(postAssembly::toBO)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Void> createPost(PostCO.Create request) {
        SysPost entity = postAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.insert(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> updatePost(PostCO.Update request) {
        SysPost entity = postAssembly.toEntity(request);
        return Mono.fromRunnable(() -> mapper.update(entity)).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> removePost(Long id) {
        // TODO: [无法处理] 删除岗位前，需要检查并处理与用户的关联关系。
        return Mono.fromRunnable(() -> mapper.deleteById(id)).subscribeOn(Schedulers.boundedElastic()).then();
    }
} 