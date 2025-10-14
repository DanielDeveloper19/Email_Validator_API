package com.HARD_CODE.API_VALIDATION.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String apiKey;

    private Integer apiQuota; // validaciones permitidas/mes

    private Integer apiUsed; // validaciones usadas este mes

    private LocalDateTime createdAt;

    private LocalDateTime lastQuotaReset;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        lastQuotaReset = LocalDateTime.now();
        apiQuota = 10; // 10 validaciones gratis al inicio
        apiUsed = 0;
    }


}
