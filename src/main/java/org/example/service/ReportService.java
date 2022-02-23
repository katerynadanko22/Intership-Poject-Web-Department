package org.example.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ReportService {

    XSSFWorkbook exportOccupationReport();

    XSSFWorkbook exportLastGeneratedReport();

    void generateAvailableUsersReport();
}