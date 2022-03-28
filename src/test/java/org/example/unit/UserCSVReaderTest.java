//package org.example.unit;
//
//import org.example.dto.UserCSV;
//import org.example.service.impl.CSVReaderServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ExtendWith(MockitoExtension.class)
//public class UserCSVReaderTest {
//
//    private final static String CORRECT_FILE = "usersCSV.csv";
//    private CSVReaderServiceImpl csvReaderService;
//
//    @BeforeEach
//    public void init() {
//        csvReaderService = new CSVReaderServiceImpl();
//    }
//
//    @Test
//    public void shouldReturnNotEmptyListIfFileIsCorrect() {
//        MultipartFile file = new CommonsMultipartFile(new FileItemImpl(CORRECT_FILE));
//        List<UserCSV> userCSVS = csvReaderService.readFromCsv(file);
//        Assertions.assertFalse(userCSVS.isEmpty());
//    }
//
//    @Test
//    public void shouldReturnCorrectEmployeesAmountIfFileIsCorrect() {
//        MultipartFile file = new CommonsMultipartFile(new FileItemImpl(CORRECT_FILE));
//        List<UserCSV> employees = csvReaderService.readFromCsv(file);
//
//        int expectedAmount = 4;
//        int actualAmount = employees.size();
//
//        Assertions.assertEquals(expectedAmount, actualAmount);
//    }
//
//    @Test
//    public void shouldReturnCorrectFirstNamesIfFileIsCorrect() {
//        MultipartFile file = new CommonsMultipartFile(new FileItemImpl(CORRECT_FILE));
//        List<UserCSV> employees = csvReaderService.readFromCsv(file);
//
//        List<String> expectedNames = Arrays.asList(new String[] {"Kira", "Kate", "Dan", "Alex"});
//        List<String> actualNames = employees
//                .stream()
//                .map(UserCSV::getFirstName)
//                .collect(Collectors.toList());
//
//        Assertions.assertEquals(expectedNames, actualNames);
//    }
//
//    @Test
//    public void shouldReturnCorrectLastNamesIfFileIsCorrect() {
//        MultipartFile file = new CommonsMultipartFile(new FileItemImpl(CORRECT_FILE));
//        List<UserCSV> employees = csvReaderService.readFromCsv(file);
//
//        List<String> expectedLastNames = Arrays.asList(new String[] {"Black", "Brown", "White", "Red"});
//        List<String> actualLastNames = employees
//                .stream()
//                .map(UserCSV::getLastName)
//                .collect(Collectors.toList());
//
//        Assertions.assertEquals(expectedLastNames, actualLastNames);
//    }
//
//    @Test
//    public void shouldReturnCorrectUsernamesIfFileIsCorrect() {
//        MultipartFile file = new CommonsMultipartFile(new FileItemImpl(CORRECT_FILE));
//        List<UserCSV> employees = csvReaderService.readFromCsv(file);
//
//        List<String> expectedUsernames = Arrays.asList(new String[] {"kkk@mail.com", "ttt@mail.com", "www@mail.com", "rrr@mail.com"});
//        List<String> actualUsernames = employees
//                .stream()
//                .map(UserCSV::getEmail)
//                .collect(Collectors.toList());
//
//        Assertions.assertEquals(expectedUsernames, actualUsernames);
//    }
//
//    @Test
//    public void shouldReturnCorrectPositionIfFileIsCorrect() {
//        MultipartFile file = new CommonsMultipartFile(new FileItemImpl(CORRECT_FILE));
//        List<UserCSV> employees = csvReaderService.readFromCsv(file);
//
//        List<String> expectedJobTitles = Arrays.asList(new String[] {"Project Owner", "Teamleader", "Admin", "Developer"});
//        List<String> actualJobTitles = employees
//                .stream()
//                .map(UserCSV::getJobTitle)
//                .collect(Collectors.toList());
//
//        Assertions.assertEquals(expectedJobTitles, actualJobTitles);
//    }
//}
