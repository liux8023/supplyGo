package io.github.supplygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SupplyGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplyGoApplication.class, args);
    }


}





