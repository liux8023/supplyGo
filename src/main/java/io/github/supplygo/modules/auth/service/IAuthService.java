package io.github.supplygo.modules.auth.service;

import io.github.supplygo.modules.auth.co.AuthCO;
import io.github.supplygo.modules.auth.ro.AuthRO;
import reactor.core.publisher.Mono;

public interface IAuthService {

    Mono<AuthRO.Login> login(AuthCO.Login request);
} 