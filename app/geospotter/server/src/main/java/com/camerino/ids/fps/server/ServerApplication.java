package com.camerino.ids.fps.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.camerino.ids.fps.server",
                "com.camerino.ids.core"
        }
)
/*@EnableJpaRepositories(basePackages = {
        "com.camerino.ids.core",
        "com.camerino.ids.fps.server.api.v1.persistence.repositories"
})*/
@EntityScan(basePackages = {
        "com.camerino.ids.fps.server.**",
        "com.camerino.ids.core.**"
})
@EnableJpaRepositories(basePackages = {
        "com.camerino.ids.fps.server.**"
})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
