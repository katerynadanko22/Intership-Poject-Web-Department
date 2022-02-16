package org.example.entity;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Api(value = "ResetPassword", description = "REST Apis related to ResetPassword Entity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResetPassword {
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordOld;

    @Column(nullable = false)
    private String passwordNew;
}
