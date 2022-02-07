package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Integer id;
    @ApiModelProperty(notes = "Title of the Project", name = "Title", required = true, value = "Java Project")
    private String title;
    @ApiModelProperty(notes = "Start date of the Project", name = "Start", required = true, value = "16.02.2022")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @ApiModelProperty(notes = "End date of the Project", name = "End", required = true, value = "26.02.2022")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}


