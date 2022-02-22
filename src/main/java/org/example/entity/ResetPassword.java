package org.example.entity;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "ResetPassword", description = "REST Apis related to ResetPassword")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResetPassword {
    private String email;
    private String passwordOld;
    private String passwordNew;
}
