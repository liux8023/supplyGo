package io.github.supplygo.config;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods=false)
public class MyBatisFlexConfig {

    @Bean
    public MyBatisFlexCustomizer myBatisFlexCustomizer() {
        return new MyBatisFlexCustomizer() {
            @Override
            public void customize(FlexGlobalConfig globalConfig) {
                // 添加拦截器
//                globalConfig.addInterceptor(new GlobalFilterInterceptor());
//                globalConfig.addInterceptor(new DataPermissionInterceptor());
            }
        };
    }
} 