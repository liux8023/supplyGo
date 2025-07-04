package io.github.supplygo.modules.auth.service.impl;

import io.github.supplygo.common.Status;
import io.github.supplygo.exception.ServiceException;
import io.github.supplygo.modules.access.mapper.UserMapper;
import io.github.supplygo.modules.auth.co.AuthCO;
import io.github.supplygo.modules.auth.ro.AuthRO;
import io.github.supplygo.modules.auth.service.IAuthService;
import io.github.supplygo.modules.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<AuthRO.Login> login(AuthCO.Login request) {
        return Mono.fromCallable(() -> userMapper.selectByAccount(request.account()))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(userOpt -> Mono.justOrEmpty(userOpt)
                        .filter(user -> passwordEncoder.matches(request.password(), user.getPassword()))
                        .map(user -> new AuthRO.Login(jwtService.generateToken(user)))
                        .switchIfEmpty(Mono.error(new ServiceException(Status.LOGIN_VALID_ERROR)))
                );
    }
} 