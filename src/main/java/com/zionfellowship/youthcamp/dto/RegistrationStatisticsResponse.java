package com.zionfellowship.youthcamp.dto;

public record RegistrationStatisticsResponse(

        long totalRegistrations,

        long ecCount,

        long dtCount,

        long ltCount,

        long sltCount,

        long todayRegistrations

) {
}