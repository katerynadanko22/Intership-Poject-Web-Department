package org.example.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Integer id;
    @ApiModelProperty(notes = "Title of the Department",name="Title",required=true, value="Java Department")
    private String title;

    public DepartmentDTO(Integer id) {
        this.id = id;
    }
}