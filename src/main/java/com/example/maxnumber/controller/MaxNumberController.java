package com.example.maxnumber.controller;

import com.example.maxnumber.service.MaxNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@Tag(name = "Max Number API", description = "API for finding N-th maximum number in an XLSX file")
public class MaxNumberController {
    private final MaxNumberService maxNumberService;

    public MaxNumberController(MaxNumberService maxNumberService) {
        this.maxNumberService = maxNumberService;
    }

    @Operation(
            summary = "Find the N-th maximum number in the XLSX file",
            description = "Returns the N-th maximum number from the provided XLSX file.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Integer.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/max-number")
    public ResponseEntity<Integer> findNthMaxNumber(
            @Parameter(description = "Path to the XLSX file", required = true) @RequestParam String filePath,
            @Parameter(description = "The N-th maximum number to find", required = true) @RequestParam int n) {

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            int result = maxNumberService.findNthMaxNumber(file, n);
            return ResponseEntity.ok(result);
        } catch (IOException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
