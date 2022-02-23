package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProjectPositionDTO;
import org.example.facade.AvailableProjectPositionsFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Swagger2 RestController", description = "REST Apis related to Available ProjectPositions")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/project-positions")
public class AvailableProjectPositionsController {
    private final AvailableProjectPositionsFacade availableProjectPositionsFacade;

    @ApiOperation(value = "Get list of ProjectPositions with Users Available Now in the System ", response = Iterable.class, tags = "findAllAvailableNow")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/available-now")
    private List<ProjectPositionDTO> findAllAvailableProjectPositionsNow() {
        return availableProjectPositionsFacade.findAllAvailableProjectPositionsNow();
    }

    @ApiOperation(value = "Get list of ProjectPositions with Users Available Next assigned days in the System ", response = Iterable.class, tags = "findAllAvailableNext")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value = "/available-next/{days}")
    private List<ProjectPositionDTO> findAllAvailableProjectPositionsNext(@PathVariable("days") int days) {
        return availableProjectPositionsFacade.findAllAvailableProjectPositionsNext(days);
    }
}
