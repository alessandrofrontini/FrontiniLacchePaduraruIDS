package com.camerino.ids.fps.server;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        Version version = com.fasterxml.jackson.databind.cfg.PackageVersion.VERSION;
        System.out.println("databind-package:" + version);
        version = com.fasterxml.jackson.core.json.PackageVersion.VERSION;
        System.out.println("core package:" + version);
        ObjectMapper mapper = new ObjectMapper();
        version = mapper.version();
        System.out.println("om: "+version);
        SpringApplication.run(ServerApplication.class, args);
    }

}
