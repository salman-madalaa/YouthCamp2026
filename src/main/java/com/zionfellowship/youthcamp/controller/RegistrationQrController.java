package com.zionfellowship.youthcamp.controller;

import com.zionfellowship.youthcamp.service.RegistrationQrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationQrController {

    private final RegistrationQrService qrService;

    @GetMapping(
            value = "/{id}/qr",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public ResponseEntity<byte[]> generateQrCode(
            @PathVariable Long id
    ) {

        byte[] qrCode =
                qrService.generateQrCode(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);
    }
}