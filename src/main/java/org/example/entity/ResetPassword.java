package org.example.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Api(value = "ResetPassword", description = "REST Apis related to ResetPassword Entity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResetPassword {
    @NotBlank
    private String email;

    @NotBlank
    private String passwordOld;

    @NotBlank
    private String passwordNew;
}
