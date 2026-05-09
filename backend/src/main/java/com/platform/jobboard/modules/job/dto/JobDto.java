package com.platform.jobboard.modules.job.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JobCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Double salary;

    @NotNull
    private Integer categoryId;

    @NotNull
    @Positive
    private Integer workersNeeded;

    @NotBlank
    private String location;

    @NotNull
    private String shiftDate; // ISO format

    private String shiftStartTime; // optional
    private String shiftEndTime;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JobResponse {
    private String id;
    private String title;
    private String description;
    private Double salary;
    private Integer categoryId;
    private Integer workersNeeded;
    private String location;
    private String shiftDate;
    private String shiftStartTime;
    private String shiftEndTime;
    private String status;
    private String employerId;
}
