package org.example.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.exception.WritingReportException;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/users/reports")
@Api(tags = "API for exporting workload reports of users")
@Slf4j
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Export a monthly occupation-report of users")
    public void exportUsers(HttpServletResponse response) {
        log.info("executing exportUsers() method");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Users.xlsx");
        response.setCharacterEncoding("utf-8");
        XSSFWorkbook workbook = reportService.exportOccupationReport();
        writeToResponse(workbook, response);
    }

    @GetMapping("/last")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Export last generated report of available users for the next month")
    public void exportLastGeneratedReport(HttpServletResponse response) {
        log.info("executing exportLastGeneratedReport() method");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Users-Last.xlsx");
        XSSFWorkbook workbook = reportService.exportLastGeneratedReport();
        writeToResponse(workbook, response);
    }

    private void writeToResponse(XSSFWorkbook workbook, HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new WritingReportException("Failed to write a monthly report", e);
        }
    }
}
