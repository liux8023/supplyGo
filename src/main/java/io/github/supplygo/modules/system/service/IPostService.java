package io.github.supplygo.modules.system.service;

import io.github.supplygo.modules.system.bo.PostBO;
import io.github.supplygo.modules.system.co.PostCO;
import io.github.supplygo.modules.system.entity.SysPost;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import reactor.core.publisher.Mono;

public interface IPostService extends IService<SysPost> {

    Mono<Page<PostBO>> page(PostCO.Query query);

    Mono<PostBO> getPostById(Long id);

    Mono<Void> createPost(PostCO.Create request);

    Mono<Void> updatePost(PostCO.Update request);

    Mono<Void> removePost(Long id);
} 