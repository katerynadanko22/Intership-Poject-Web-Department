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
public class ProjectPositionDTO {
    private Integer id;
    @ApiModelProperty(notes = "Title of the ProjectPosition", name = "Title", required = true, value = "Middle ProjectPosition")
    private String positionTitle;
    @ApiModelProperty(notes = "Start date of the ProjectPosition", name = "Start", required = true, value = "16-02-2022")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate positionStartDate;
    @ApiModelProperty(notes = "End date of the ProjectPosition", name = "End", required = true, value = "26-02-2022")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate positionEndDate;
    @ApiModelProperty(notes = "occupation of the ProjectPosition", name = "occupation", required = true, value = "write code")
    private String occupation;
    @ApiModelProperty(notes = "Id of the User", name = "User", required = true, value = "User")
    private UserDTO user;
    @ApiModelProperty(notes = "Id of the Project", name = "Project", required = true, value = "Project")
    private ProjectDTO project;
}




