package com.example.cardatabase;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 구성 관련 애너테이션
public class OpenApiConfig {

    @Bean
    public OpenAPI carDatabaseOpenApi(){ // 메서드
        return new OpenAPI()
                .info(new Info()
                        .title("Car Rest API")
                        .description("My car stock")
                        .version("1.0")
                ); // OpenAPI 객체 반환
    }
}
