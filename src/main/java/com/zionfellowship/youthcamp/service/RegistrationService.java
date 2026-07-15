package com.zionfellowship.youthcamp.service;

import com.zionfellowship.youthcamp.dto.RegistrationCreateRequest;
import com.zionfellowship.youthcamp.dto.RegistrationResponse;
import com.zionfellowship.youthcamp.entity.Registration;
import com.zionfellowship.youthcamp.mapper.RegistrationMapper;
import com.zionfellowship.youthcamp.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zionfellowship.youthcamp.exception.ResourceNotFoundException;
import com.zionfellowship.youthcamp.dto.RegistrationUpdateRequest;
import com.zionfellowship.youthcamp.enums.CampGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final RegistrationEmailService emailService;

    @Transactional
    public RegistrationResponse create(
            RegistrationCreateRequest request
    ) {

        if (registrationRepository.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalArgumentException(
                    "A registration already exists with this email"
            );
        }

        Registration registration =
                registrationMapper.toEntity(request);

        Registration savedRegistration =
                registrationRepository.save(registration);

        emailService.sendConfirmationEmail(
                savedRegistration
        );

        return registrationMapper.toResponse(savedRegistration);
    }

    @Transactional(readOnly = true)
    public Page<RegistrationResponse> findAll(
            String search,
            CampGroup group,
            Pageable pageable
    ) {

        Page<Registration> registrations;

        boolean hasSearch =
                search != null &&
                        !search.trim().isEmpty();

        if (hasSearch && group != null) {

            registrations =
                    registrationRepository
                            .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneNumberContainingAndGroup(
                                    search,
                                    search,
                                    search,
                                    group,
                                    pageable
                            );

        } else if (hasSearch) {

            registrations =
                    registrationRepository
                            .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneNumberContaining(
                                    search,
                                    search,
                                    search,
                                    pageable
                            );

        } else if (group != null) {

            registrations =
                    registrationRepository.findByGroup(
                            group,
                            pageable
                    );

        } else {

            registrations =
                    registrationRepository.findAll(pageable);
        }

        return registrations.map(
                registrationMapper::toResponse
        );
    }

    @Transactional(readOnly = true)
    public RegistrationResponse findById(Long id) {

        Registration registration =
                registrationRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Registration not found with id: " + id
                                )
                        );

        return registrationMapper.toResponse(registration);
    }

    @Transactional
    public RegistrationResponse update(
            Long id,
            RegistrationUpdateRequest request
    ) {

        Registration registration =
                registrationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Registration not found with id: " + id
                                )
                        );

        registrationRepository.findByEmailIgnoreCase(request.email())
                .ifPresent(existing -> {

                    if (!existing.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "A registration already exists with this email"
                        );
                    }
                });

        registrationMapper.updateEntity(registration, request);

        Registration updatedRegistration =
                registrationRepository.save(registration);

        return registrationMapper.toResponse(updatedRegistration);
    }

    @Transactional
    public void delete(Long id) {

        Registration registration =
                registrationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Registration not found with id: " + id
                                )
                        );

        registrationRepository.delete(registration);
    }

    @Transactional
    public RegistrationResponse checkIn(Long id) {

        Registration registration =
                registrationRepository
                        .findByIdAndCheckedInFalse(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Registration not found or already checked in"
                                )
                        );

        registration.setCheckedIn(true);
        registration.setCheckedInAt(LocalDateTime.now());

        Registration savedRegistration =
                registrationRepository.save(registration);

        return registrationMapper.toResponse(savedRegistration);
    }


}