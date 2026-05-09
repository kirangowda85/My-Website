package com.platform.jobboard.modules.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "employer_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployerProfile {

    @Id
    private UUID userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "owner_name")
    private String ownerName;

    private String phone;
    private String location;

    @Column(name = "company_logo_url")
    private String companyLogoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    @Builder.Default
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @Column(name = "employer_rating", precision = 3, scale = 2)
    @Builder.Default
    private BigDecimal employerRating = BigDecimal.ZERO;

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }
}
