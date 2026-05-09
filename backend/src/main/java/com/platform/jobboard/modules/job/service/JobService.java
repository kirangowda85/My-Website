package com.platform.jobboard.modules.job.service;

import com.platform.jobboard.core.exception.ResourceNotFoundException;
import com.platform.jobboard.core.utils.ApiResponse;
import com.platform.jobboard.modules.job.dto.JobDto.JobCreateRequest;
import com.platform.jobboard.modules.job.dto.JobDto.JobResponse;
import com.platform.jobboard.modules.job.entity.Job;
import com.platform.jobboard.modules.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public JobResponse createJob(JobCreateRequest request, UUID employerId) {
        Job job = Job.builder()
                .employerId(employerId)
                .title(request.getTitle())
                .description(request.getDescription())
                .salary(java.math.BigDecimal.valueOf(request.getSalary()))
                .category(JobCategory.builder().id(request.getCategoryId()).build()) // Assume category exists
                .workersNeeded(request.getWorkersNeeded())
                .location(request.getLocation())
                .shiftDate(LocalDate.parse(request.getShiftDate()))
                .shiftStartTime(request.getShiftStartTime() != null ? java.time.LocalTime.parse(request.getShiftStartTime()) : null)
                .shiftEndTime(request.getShiftEndTime() != null ? java.time.LocalTime.parse(request.getShiftEndTime()) : null)
                .status(Job.JobStatus.OPEN)
                .build();
        Job saved = jobRepository.save(job);
        return mapToResponse(saved);
    }

    public Page<JobResponse> listOpenJobs(int page, int size) {
        Page<Job> pageJobs = jobRepository.findAllByStatusAndShiftDateAfter(
                Job.JobStatus.OPEN,
                LocalDate.now(),
                PageRequest.of(page, size)
        );
        return pageJobs.map(this::mapToResponse);
    }

    public JobResponse getJob(UUID jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        return mapToResponse(job);
    }

    private JobResponse mapToResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId().toString())
                .title(job.getTitle())
                .description(job.getDescription())
                .salary(job.getSalary().doubleValue())
                .categoryId(job.getCategory() != null ? job.getCategory().getId() : null)
                .workersNeeded(job.getWorkersNeeded())
                .location(job.getLocation())
                .shiftDate(job.getShiftDate().format(DateTimeFormatter.ISO_DATE))
                .shiftStartTime(job.getShiftStartTime() != null ? job.getShiftStartTime().toString() : null)
                .shiftEndTime(job.getShiftEndTime() != null ? job.getShiftEndTime().toString() : null)
                .status(job.getStatus().name())
                .employerId(job.getEmployerId().toString())
                .build();
    }
}
