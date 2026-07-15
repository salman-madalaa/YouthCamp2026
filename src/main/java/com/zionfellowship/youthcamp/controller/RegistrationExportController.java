package com.zionfellowship.youthcamp.controller;

import com.zionfellowship.youthcamp.service.RegistrationExcelExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registrations/export")
@RequiredArgsConstructor
public class RegistrationExportController {

    private final RegistrationExcelExportService exportService;

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportExcel() {

        byte[] excelFile =
                exportService.exportRegistrations();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_OCTET_STREAM
        );

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename("youth-camp-registrations.xlsx")
                        .build()
        );

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excelFile);
    }
}