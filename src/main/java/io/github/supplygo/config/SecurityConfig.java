package io.github.supplygo.config;

import io.github.supplygo.common.R;
import io.github.supplygo.common.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 安全配置类
 */
@Configuration(proxyBeanMethods=false)
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${spring.security.oauth2.resource-server.jwt.secret-key}")
    private String secretKeyString;

    private final Environment env;
    private final ObjectMapper objectMapper;

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        byte[] keyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        return NimbusReactiveJwtDecoder.withSecretKey(
                new SecretKeySpec(keyBytes, "HmacSHA256")
        ).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 设置前缀，默认是 "SCOPE_"
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // 如果你的角色是以 ROLE_ 开头
        // 或者不要前缀，调用 grantedAuthoritiesConverter.setAuthorityPrefix("");
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        boolean isDev = Arrays.asList(env.getActiveProfiles()).contains("dev");
        if (isDev) {
            return http.cors(Customizer.withDefaults())
                    .csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .authorizeExchange(e -> e.anyExchange().permitAll())
                    .build();
        }

        http.cors(Customizer.withDefaults()).csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(this::handleUnauthorized)
                        .accessDeniedHandler(this::handleAccessDenied)
                )
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/",
                                "/auth/login",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/webjars/**",
                                "/doc.html"
                        ).permitAll()
                        .pathMatchers("/api/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    //  未认证处理（401）
    private Mono<Void> handleUnauthorized(ServerWebExchange exchange, Throwable ex) {
        return writeJson(exchange, R.fail(Status.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    // 权限不足处理（403）
    private Mono<Void> handleAccessDenied(ServerWebExchange exchange, Throwable ex) {
        return writeJson(exchange, R.fail(Status.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    // 通用 JSON 响应写入方法
    private Mono<Void> writeJson(ServerWebExchange exchange, Object body, HttpStatus status) {
        var response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().set(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name());

        return Mono.fromCallable(() -> objectMapper.writeValueAsBytes(body))
                .flatMap(jsonBytes -> {
                    var buffer = response.bufferFactory().wrap(jsonBytes);
                    return response.writeWith(Mono.just(buffer));
                });
    }

}