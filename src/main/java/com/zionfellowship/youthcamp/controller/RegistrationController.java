package com.zionfellowship.youthcamp.controller;

import com.zionfellowship.youthcamp.dto.DashboardStatisticsResponse;
import com.zionfellowship.youthcamp.dto.RegistrationCreateRequest;
import com.zionfellowship.youthcamp.dto.RegistrationResponse;
import com.zionfellowship.youthcamp.service.RegistrationService;
import com.zionfellowship.youthcamp.dto.RegistrationUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.zionfellowship.youthcamp.enums.CampGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponse create(
            @Valid @RequestBody RegistrationCreateRequest request
    ) {
        return registrationService.create(request);
    }

    @GetMapping
    public Page<RegistrationResponse> findAll(

            @RequestParam(required = false)
            String search,

            @RequestParam(required = false)
            CampGroup group,

            @PageableDefault(
                    size = 20,
                    sort = "createdAt"
            )
            Pageable pageable

    ) {
        return registrationService.findAll(
                search,
                group,
                pageable
        );
    }

    @GetMapping("/{id}")
    public RegistrationResponse findById(
            @PathVariable Long id
    ) {
        return registrationService.findById(id);
    }

    @PutMapping("/{id}")
    public RegistrationResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RegistrationUpdateRequest request
    ) {
        return registrationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        registrationService.delete(id);
    }

    @PostMapping("/{id}/check-in")
    public RegistrationResponse checkIn(
            @PathVariable Long id
    ) {
        return registrationService.checkIn(id);
    }

    @GetMapping("/dashboard/statistics")
    public DashboardStatisticsResponse getDashboardStatistics() {

        return registrationService
                .getDashboardStatistics();
    }

    @GetMapping("/search")
    public Page<RegistrationResponse> search(

            @RequestParam(required = false)
            String search,

            @RequestParam(required = false)
            CampGroup group,

            @RequestParam(required = false)
            Boolean checkedIn,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size

    ) {
        return registrationService.search(
                search,
                group,
                checkedIn,
                page,
                size
        );
    }
    

}