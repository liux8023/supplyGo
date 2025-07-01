package io.github.supplygo.modules.auth.controller;

import io.github.supplygo.common.R;
import io.github.supplygo.modules.auth.co.AuthCO;
import io.github.supplygo.modules.auth.ro.AuthRO;
import io.github.supplygo.modules.auth.service.IAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "认证管理")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final IAuthService authService;

    @PostMapping("/login")
    public Mono<R<AuthRO.Login>> login(@RequestBody AuthCO.Login request) {
        return authService.login(request).map(R::ok);
    }
} 