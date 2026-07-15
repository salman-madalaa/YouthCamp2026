package com.zionfellowship.youthcamp.dto;

public record DashboardStatisticsResponse(

        long totalRegistrations,

        long checkedIn,

        long notCheckedIn,

        long ec,

        long dt,

        long lt,

        long slt

) {
}