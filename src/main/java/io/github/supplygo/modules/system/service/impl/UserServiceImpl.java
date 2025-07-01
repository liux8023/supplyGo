package io.github.supplygo.modules.system.service.impl;

import io.github.supplygo.modules.system.assembly.UserAssembly;
import io.github.supplygo.modules.system.bo.UserBO;
import io.github.supplygo.modules.system.co.UserCO;
import io.github.supplygo.modules.system.entity.SysDept;
import io.github.supplygo.modules.system.entity.SysPost;
import io.github.supplygo.modules.system.entity.SysRole;
import io.github.supplygo.modules.system.entity.SysUser;
import io.github.supplygo.modules.system.mapper.SysUserMapper;
import io.github.supplygo.modules.system.service.IDeptService;
import io.github.supplygo.modules.system.service.IPostService;
import io.github.supplygo.modules.system.service.IRoleService;
import io.github.supplygo.modules.system.service.IUserService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.spring.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IUserService {

    private final UserAssembly userAssembly;
    private final PasswordEncoder passwordEncoder;
    private final IDeptService deptService;
    private final IPostService postService;
    private final IRoleService roleService;

    @Override
    public Mono<Page<UserBO>> page(UserCO.Query query) {
        return Mono.fromCallable(() -> {
            Page<SysUser> page = new Page<>(query.current(), query.size());
            Map<String, Object> params = new HashMap<>();
            params.put("account", query.account());
            params.put("name", query.name());
            params.put("realName", query.realName());
            params.put("status", query.status());
            params.put("deptId", query.deptId());

            Page<SysUser> resultPage = mapper.selectUserPage(page, params);
            return resultPage.map(this::fillAssociatedDataToBO);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserBO> getUserById(Long id) {
        return Mono.fromCallable(() -> mapper.selectOneById(id))
                .map(this::fillAssociatedDataToBO)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private UserBO fillAssociatedDataToBO(SysUser user) {
        if (user == null) {
            return null;
        }
        UserBO bo = userAssembly.toBO(user);

        // Fill Department Name
        if (user.getDeptId() != null) {
            SysDept dept = deptService.getById(user.getDeptId());
            if (dept != null) {
                bo.setDeptName(dept.getName());
            }
        }

        // Fill Post Name
        if (user.getPostId() != null) {
            SysPost post = postService.getById(user.getPostId());
            if (post != null) {
                bo.setPostName(post.getName());
            }
        }
        
        // Fill Roles
        List<SysRole> roles = roleService.findRolesByUserId(user.getId());
        if(roles != null && !roles.isEmpty()){
            bo.setRoleIds(roles.stream().map(SysRole::getId).collect(Collectors.toList()));
            bo.setRoleNames(roles.stream().map(SysRole::getName).collect(Collectors.toList()));
        } else {
            bo.setRoleIds(Collections.emptyList());
            bo.setRoleNames(Collections.emptyList());
        }

        return bo;
    }

    @Override
    @Transactional
    public Mono<Void> createUser(UserCO.Create request) {
        SysUser entity = userAssembly.toEntity(request);
        entity.setPassword(passwordEncoder.encode(request.password()));
        // TODO: 可以在这里校验用户名、邮箱、手机号是否唯一等
        // TODO: 处理角色关系
        return Mono.fromRunnable(() -> mapper.insert(entity))
                .subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    @Transactional
    public Mono<Void> updateUser(UserCO.Update request) {
        SysUser entity = userAssembly.toEntity(request);
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