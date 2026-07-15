package com.zionfellowship.youthcamp.service;

import com.zionfellowship.youthcamp.dto.RegistrationStatisticsResponse;
import com.zionfellowship.youthcamp.enums.CampGroup;
import com.zionfellowship.youthcamp.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class RegistrationStatisticsService {

    private final RegistrationRepository registrationRepository;

    @Transactional(readOnly = true)
    public RegistrationStatisticsResponse getStatistics() {

        Instant startOfDay =
                LocalDate.now()
                        .atStartOfDay(
                                ZoneId.of("Asia/Kolkata")
                        )
                        .toInstant();

        return new RegistrationStatisticsResponse(

                registrationRepository.count(),

                registrationRepository.countByGroup(
                        CampGroup.EC
                ),

                registrationRepository.countByGroup(
                        CampGroup.DT
                ),

                registrationRepository.countByGroup(
                        CampGroup.LT
                ),

                registrationRepository.countByGroup(
                        CampGroup.SLT
                ),

                registrationRepository.countByCreatedAtGreaterThanEqual(
                        startOfDay
                )
        );
    }
}