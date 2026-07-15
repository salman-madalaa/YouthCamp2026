package com.zionfellowship.youthcamp.service;

import com.zionfellowship.youthcamp.entity.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class RegistrationEmailService {

    private final JavaMailSender mailSender;

    private final QrCodeService qrCodeService;

    public void sendConfirmationEmail(
            Registration registration
    ) {

        try {

            byte[] qrCode =
                    qrCodeService.generateQrCode(
                            "REGISTRATION-" + registration.getId()
                    );

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true
                    );

            helper.setTo(
                    registration.getEmail()
            );

            helper.setSubject(
                    "Youth Camp 2026 Registration Confirmation"
            );

            helper.setText(
                    """
                    Dear %s,

                    🎉 Your Youth Camp 2026 registration is confirmed!

                    Registration ID: %d
                    Name: %s
                    Group: %s

                    Please keep the attached QR code safely.

                    God bless you!

                    Zion Fellowship Church
                    """.formatted(
                            registration.getName(),
                            registration.getId(),
                            registration.getName(),
                            registration.getGroup().name()
                    )
            );

            helper.addAttachment(
                    "registration-qr-code.png",
                    new ByteArrayResource(qrCode)
            );

            mailSender.send(message);

        } catch (MessagingException exception) {

            throw new IllegalStateException(
                    "Failed to send registration email",
                    exception
            );
        }
    }
}