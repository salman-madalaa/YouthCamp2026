package com.zionfellowship.youthcamp.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.zionfellowship.youthcamp.entity.Registration;
import com.zionfellowship.youthcamp.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RegistrationQrService {

    private final RegistrationRepository registrationRepository;

    @Transactional(readOnly = true)
    public byte[] generateQrCode(Long registrationId) {

        Registration registration =
                registrationRepository.findById(registrationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Registration not found with id: "
                                                + registrationId
                                )
                        );

        String qrContent = buildQrContent(registration);

        try {

            QRCodeWriter qrCodeWriter =
                    new QRCodeWriter();

            BitMatrix bitMatrix =
                    qrCodeWriter.encode(
                            qrContent,
                            BarcodeFormat.QR_CODE,
                            400,
                            400
                    );

            try (ByteArrayOutputStream outputStream =
                         new ByteArrayOutputStream()) {

                MatrixToImageWriter.writeToStream(
                        bitMatrix,
                        "PNG",
                        outputStream
                );

                return outputStream.toByteArray();
            }

        } catch (WriterException | IOException exception) {

            throw new IllegalStateException(
                    "Failed to generate QR code",
                    exception
            );
        }
    }

    private String buildQrContent(
            Registration registration
    ) {

        return """
                Youth Camp 2026
                Registration ID: %d
                Name: %s
                Group: %s
                """.formatted(
                registration.getId(),
                registration.getName(),
                registration.getGroup().name()
        );
    }
}