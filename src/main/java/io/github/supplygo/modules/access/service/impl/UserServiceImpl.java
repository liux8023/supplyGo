package io.github.supplygo.modules.access.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.supplygo.modules.access.assembly.UserAssembly;
import io.github.supplygo.modules.access.bo.UserBO;
import io.github.supplygo.modules.access.co.UserCO;
import io.github.supplygo.modules.access.entity.Dept;
import io.github.supplygo.modules.access.entity.Post;
import io.github.supplygo.modules.access.entity.Role;
import io.github.supplygo.modules.access.entity.User;
import io.github.supplygo.modules.access.mapper.UserMapper;
import io.github.supplygo.modules.access.service.IDeptService;
import io.github.supplygo.modules.access.service.IPostService;
import io.github.supplygo.modules.access.service.IRoleService;
import io.github.supplygo.modules.access.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserAssembly userAssembly;
    private final PasswordEncoder passwordEncoder;
    private final IDeptService deptService;
    private final IPostService postService;
    private final IRoleService roleService;

    @Override
    public Mono<Page<UserBO>> page(UserCO.Query query) {
        return Mono.fromCallable(() -> {
            Page<User> page = new Page<>(query.current(), query.size());
            Map<String, Object> params = new HashMap<>();
            params.put("account", query.account());
            params.put("name", query.name());
            params.put("realName", query.realName());
            params.put("status", query.status());
            params.put("deptId", query.deptId());

            Page<User> resultPage = mapper.selectUserPage(page, params);
            return resultPage.map(this::fillAssociatedDataToBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserBO> getUserById(Long id) {
        return Mono.fromCallable(() -> mapper.selectOneById(id))
                .map(this::fillAssociatedDataToBO)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private UserBO fillAssociatedDataToBO(User user) {
        if (user == null) {
            return null;
        }
        UserBO bo = userAssembly.toBO(user);

        // Fill Department Name
        if (user.getDeptId() != null) {
            Dept dept = deptService.getById(user.getDeptId());
            if (dept != null) {
                bo.setDeptName(dept.getName());
            }
        }

        // Fill Post Name
        if (user.getPostId() != null) {
            Post post = postService.getById(user.getPostId());
            if (post != null) {
                bo.setPostName(post.getName());
            }
        }
        
        // Fill Roles
        List<Role> roles = roleService.findRolesByUserId(user.getId());
        if(roles != null && !roles.isEmpty()){
            bo.setRoleIds(roles.stream().map(Role::getId).collect(Collectors.toList()));
            bo.setRoleNames(roles.stream().map(Role::getName).collect(Collectors.toList()));
        } else {
            bo.setRoleIds(Collections.emptyList());
            bo.setRoleNames(Collections.emptyList());
        }

        return bo;
    }

    @Override
    @Transactional
    public Mono<Void> createUser(UserCO.Create request) {
        User entity = userAssembly.toEntity(request);
        entity.setPassword(passwordEncoder.encode(request.password()));
        // TODO: 可以在这里校验用户名、邮箱、手机号是否唯一等
        // TODO: 处理角色关系
        return Mono.fromRunnable(() -> mapper.insert(entity))
                .subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> updateUser(UserCO.Update request) {
        User entity = userAssembly.toEntity(request);
        // TODO: 处理角色关系
        return Mono.fromRunnable(() -> mapper.update(entity))
                .subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> removeUser(Long id) {
        return Mono.fromRunnable(() -> mapper.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic()).then();
    }
} 