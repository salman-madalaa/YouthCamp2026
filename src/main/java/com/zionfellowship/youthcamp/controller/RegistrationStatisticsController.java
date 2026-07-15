package com.zionfellowship.youthcamp.controller;

import com.zionfellowship.youthcamp.dto.RegistrationStatisticsResponse;
import com.zionfellowship.youthcamp.service.RegistrationStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registrations/statistics")
@RequiredArgsConstructor
public class RegistrationStatisticsController {

    private final RegistrationStatisticsService statisticsService;

    @GetMapping
    public RegistrationStatisticsResponse getStatistics() {

        return statisticsService.getStatistics();
    }
}