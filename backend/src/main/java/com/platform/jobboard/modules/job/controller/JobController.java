package com.platform.jobboard.modules.job.controller;

import com.platform.jobboard.core.utils.ApiResponse;
import com.platform.jobboard.modules.job.dto.JobDto.JobCreateRequest;
import com.platform.jobboard.modules.job.dto.JobDto.JobResponse;
import com.platform.jobboard.modules.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ApiResponse<JobResponse> createJob(@Valid @RequestBody JobCreateRequest request,
                                               @AuthenticationPrincipal com.platform.jobboard.modules.user.entity.User user) {
        // only employer can create
        if (user.getRole() != com.platform.jobboard.modules.user.entity.User.Role.EMPLOYER) {
            return ApiResponse.error("Only employers can post jobs");
        }
        JobResponse response = jobService.createJob(request, user.getId());
        return ApiResponse.success(response);
    }

    @GetMapping
    public ApiResponse<Page<JobResponse>> listJobs(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Page<JobResponse> jobs = jobService.listOpenJobs(page, size);
        return ApiResponse.success(jobs);
    }

    @GetMapping("/{id}")
    public ApiResponse<JobResponse> getJob(@PathVariable UUID id) {
        return ApiResponse.success(jobService.getJob(id));
    }
}
