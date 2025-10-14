package com.HARD_CODE.API_VALIDATION.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResultDto {

    private String email;
    private Boolean valid;
    private String reason;

    public ValidationResultDto(String email, Boolean valid, String reason) {
        this.email = email;
        this.valid = valid;
        this.reason = reason;
    }

}
