package com.zionfellowship.youthcamp.service;

import com.zionfellowship.youthcamp.entity.Registration;
import com.zionfellowship.youthcamp.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationExcelExportService {

    private final RegistrationRepository registrationRepository;

    @Transactional(readOnly = true)
    public byte[] exportRegistrations() {

        List<Registration> registrations =
                registrationRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet =
                    workbook.createSheet("Registrations");

            createHeader(sheet);

            int rowNumber = 1;

            for (Registration registration : registrations) {

                Row row = sheet.createRow(rowNumber++);

                row.createCell(0)
                        .setCellValue(registration.getId());

                row.createCell(1)
                        .setCellValue(registration.getName());

                row.createCell(2)
                        .setCellValue(registration.getEmail());

                row.createCell(3)
                        .setCellValue(registration.getPhoneNumber());

                row.createCell(4)
                        .setCellValue(registration.getAge());

                row.createCell(5)
                        .setCellValue(registration.getAddress());

                row.createCell(6)
                        .setCellValue(registration.getOccupation());

                row.createCell(7)
                        .setCellValue(
                                registration.getGroup().name()
                        );

                row.createCell(8)
                        .setCellValue(
                                registration.getCreatedAt()
                                        .toString()
                        );

                row.createCell(9)
                        .setCellValue(
                                registration.getUpdatedAt()
                                        .toString()
                        );
            }

            autoSizeColumns(sheet);

            try (ByteArrayOutputStream outputStream =
                         new ByteArrayOutputStream()) {

                workbook.write(outputStream);

                return outputStream.toByteArray();
            }

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "Failed to export registrations to Excel",
                    exception
            );
        }
    }

    private void createHeader(Sheet sheet) {

        String[] headers = {
                "ID",
                "Name",
                "Email",
                "Phone Number",
                "Age",
                "Address",
                "Occupation",
                "Group",
                "Created At",
                "Updated At"
        };

        Row headerRow = sheet.createRow(0);

        CellStyle headerStyle =
                sheet.getWorkbook().createCellStyle();

        Font font =
                sheet.getWorkbook().createFont();

        font.setBold(true);

        headerStyle.setFont(font);

        for (int i = 0; i < headers.length; i++) {

            Cell cell = headerRow.createCell(i);

            cell.setCellValue(headers[i]);

            cell.setCellStyle(headerStyle);
        }
    }

    private void autoSizeColumns(Sheet sheet) {

        for (int i = 0; i < 10; i++) {

            sheet.autoSizeColumn(i);
        }
    }
}