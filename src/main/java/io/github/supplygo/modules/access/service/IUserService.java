package io.github.supplygo.modules.access.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.supplygo.modules.access.bo.UserBO;
import io.github.supplygo.modules.access.co.UserCO;
import io.github.supplygo.modules.access.entity.User;
import reactor.core.publisher.Mono;

public interface IUserService extends IService<User> {

    Mono<Page<UserBO>> page(UserCO.Query query);

    Mono<UserBO> getUserById(Long id);
    
    Mono<Void> createUser(UserCO.Create request);
    
    Mono<Void> updateUser(UserCO.Update request);
    
    Mono<Void> removeUser(Long id);
} 