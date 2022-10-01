package com.github.dhslrl321;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServerApplication {

    @RestController
    public static class HealthCheck {
        @GetMapping("/health")
        public String health() {
            return "ok";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class);
    }
}
