package io.github.supplygo.modules.access.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.supplygo.modules.access.bo.PostBO;
import io.github.supplygo.modules.access.co.PostCO;
import io.github.supplygo.modules.access.entity.Post;
import reactor.core.publisher.Mono;

public interface IPostService extends IService<Post> {

    Mono<Page<PostBO>> page(PostCO.Query query);

    Mono<PostBO> getPostById(Long id);

    Mono<Void> createPost(PostCO.Create request);

    Mono<Void> updatePost(PostCO.Update request);

    Mono<Void> removePost(Long id);
} 