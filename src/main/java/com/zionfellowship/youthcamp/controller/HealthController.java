package com.zionfellowship.youthcamp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, Object> health() {

        return Map.of(
                "status", "UP",
                "application", "Youth Camp API",
                "timestamp", LocalDateTime.now()
        );
    }
}