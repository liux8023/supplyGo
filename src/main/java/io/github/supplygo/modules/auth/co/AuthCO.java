package io.github.supplygo.modules.auth.co;

import jakarta.validation.constraints.NotBlank;

public class AuthCO {
    
    public record Login(
            @NotBlank(message = "账户不能为空") String account,
            @NotBlank(message = "密码不能为空") String password
    ) {}
} 