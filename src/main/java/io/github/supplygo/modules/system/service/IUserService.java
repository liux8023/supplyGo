package io.github.supplygo.modules.system.service;

import io.github.supplygo.modules.system.bo.UserBO;
import io.github.supplygo.modules.system.co.UserCO;
import io.github.supplygo.modules.system.entity.SysUser;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import reactor.core.publisher.Mono;

public interface IUserService extends IService<SysUser> {

    Mono<Page<UserBO>> page(UserCO.Query query);

    Mono<UserBO> getUserById(Long id);
    
    Mono<Void> createUser(UserCO.Create request);
    
    Mono<Void> updateUser(UserCO.Update request);
    
    Mono<Void> removeUser(Long id);
} 