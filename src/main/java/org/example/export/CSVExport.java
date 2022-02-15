package org.example.export;

import org.example.entity.User;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CSVExport extends AbstractExporter{
    public void export(List<User> users, HttpServletResponse response) throws IOException, IOException {
        super.setResponseHeader(response, "text/csv", ".csv");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"User ID", "First Name", "Last name", "E-mail", "Password", "Job Title", "Status", "Roles", "Department"};
        String[] fieldMapping = {"id", "firstName", "lastName", "email", "password", "jobTitle", "status", "roles", "department"};

        csvWriter.writeHeader(csvHeader);
        for(User user : users){
            csvWriter.write(user, fieldMapping);
        }
        csvWriter.close();
    }
    }
