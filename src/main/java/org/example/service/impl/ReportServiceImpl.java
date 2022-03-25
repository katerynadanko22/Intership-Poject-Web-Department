package org.example.service.impl;

import org.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.entity.Department;
import org.example.entity.Project;
import org.example.entity.ProjectPosition;
import org.example.entity.User;
import org.example.exception.ReadingReportException;
import org.example.exception.WritingReportException;
import org.example.repository.ProjectPositionRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    private final static String FIRST_NAME_COL = "First Name";
    private final static String LAST_NAME_COL = "Last Name";
    private final static String DEPARTMENT_COL = "Department";
    private final static String OCCUPATION_COL = "Occupation";
    private final static String DURATION_COL = "Duration";

    @Value("${reports-path}")
    private String reportsPath;
    private final XSSFWorkbook workbook;
    private final XSSFSheet sheet;
    private final ProjectPositionRepository projectPositionRepository;

    @Autowired
    public ReportServiceImpl(ProjectPositionRepository projectPositionRepository) {
        this.workbook = new XSSFWorkbook();
        this.sheet = (workbook.createSheet("Users info"));
        this.projectPositionRepository = projectPositionRepository;
    }

    @Override
    @Scheduled(cron = "${reports-generating-cron}")
    @Transactional
    public void generateAvailableUsersReport() {
        log.info("start to create Table Headers");
        createTableHeaders(FIRST_NAME_COL, LAST_NAME_COL, DEPARTMENT_COL);
        List<User> availableUsers = projectPositionRepository
                .findAllAvailableProjectPositionsNextDays(30)
                .stream()
                .map(ProjectPosition::getUser)
                .collect(Collectors.toList());
        for (int i = 0; i < availableUsers.size(); i++) {
            User user = availableUsers.get(i);

            Row row = sheet.createRow(i + 1);

            Cell firstName = row.createCell(0);
            firstName.setCellValue(user.getFirstName());

            Cell lastName = row.createCell(1);
            lastName.setCellValue(user.getLastName());

            Cell department = row.createCell(2);
            department.setCellValue(user.getDepartment().getTitle());
        }

        File file = new File(String.format("%s/AvailableUsers-%s.xlsx",
                reportsPath, LocalDate.now()));
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new WritingReportException("Failed to write down a monthly report", e);
        }
        log.info("Monthly report generated successfully");
    }

    @Override
    @Transactional
    public XSSFWorkbook exportOccupationReport() {
        createTableHeaders(FIRST_NAME_COL, LAST_NAME_COL, DEPARTMENT_COL, OCCUPATION_COL, DURATION_COL);
        List<ProjectPosition> projectPositions = projectPositionRepository.findAll();
        for (int i = 0; i < projectPositions.size(); i++) {
            ProjectPosition projectPosition = projectPositions.get(i);
            User user = projectPosition.getUser();
            Project project = projectPosition.getProject();

            Row row = sheet.createRow(i + 1);

            Cell firstName = row.createCell(0);
            firstName.setCellValue(user.getFirstName());

            Cell lastName = row.createCell(1);
            lastName.setCellValue(user.getLastName());

            Cell departmentName = row.createCell(2);
            Department department = user.getDepartment();
            departmentName.setCellValue(department == null ? null : department.getTitle());

            Cell occupation = row.createCell(3);
            occupation.setCellValue(String.format("%s", projectPosition.getOccupation()));

            Cell duration = row.createCell(4);
            duration.setCellValue(String.format("%s, (%s - %s)", project.getTitle(),
                    projectPosition.getPositionStartDate(), projectPosition.getPositionEndDate()));
        }
        log.info("Occupation report created successfully");
        return workbook;
    }

    @Override
    public XSSFWorkbook exportLastGeneratedReport() {
        File latestReport = getLatestReport();
        log.info("Latest report retrieved successfully");
        try {
            return new XSSFWorkbook(OPCPackage.open(latestReport));
        } catch (IOException | InvalidFormatException e) {
            throw new WritingReportException("Failed to write report", e);
        }
    }

    private File getLatestReport() {
        File[] reports = new File(reportsPath).listFiles();
        return Arrays.stream(reports)
                .sorted((f1, f2) -> {
                    BasicFileAttributes attr1 = null;
                    BasicFileAttributes attr2 = null;
                    try {
                        attr1 = Files.readAttributes(f1.toPath(), BasicFileAttributes.class);
                        attr2 = Files.readAttributes(f2.toPath(), BasicFileAttributes.class);
                    } catch (IOException e) {
                        throw new ReadingReportException("Failed to read file attributes", e);
                    }
                    return (-1) * attr1.creationTime().compareTo(attr2.creationTime());
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No reports found"));
    }

    private void createTableHeaders(String ... names) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < names.length; i++) {
            sheet.setColumnWidth(i, 12000);
            Cell cell = row.createCell(i);
            cell.setCellValue(names[i]);
        }
    }
}
