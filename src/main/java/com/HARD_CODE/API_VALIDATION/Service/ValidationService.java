package com.HARD_CODE.API_VALIDATION.Service;


import com.HARD_CODE.API_VALIDATION.DTO.ValidationResultDto;
import com.HARD_CODE.API_VALIDATION.Model.UserEntity;
import com.HARD_CODE.API_VALIDATION.Model.ValidationEntity;
import com.HARD_CODE.API_VALIDATION.Repositories.ValidationRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    private final EmailValidator emailValidator = EmailValidator.getInstance();

    public ValidationResultDto validateEmail(String email) {
        boolean isValid = emailValidator.isValid(email);
        String reason = isValid ? "valid" : "formato inválido";
        return new ValidationResultDto(email, isValid, reason);
    }

    public Map<String, Object> validateBulk(List<String> emails) {
        List<ValidationResultDto> results = new ArrayList<>();
        int validCount = 0;
        int invalidCount = 0;

        for (String email : emails) {
            ValidationResultDto result = validateEmail(email);
            results.add(result);

            if (result.getValid()) {
                validCount++;
            } else {
                invalidCount++;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("results", results);

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", emails.size());
        stats.put("valid", validCount);
        stats.put("invalid", invalidCount);
        stats.put("validPercentage", (validCount * 100) / emails.size());

        response.put("stats", stats);

        return response;
    }

    public void saveValidation(UserEntity user, String email, ValidationResultDto result) {
        ValidationEntity validation = new ValidationEntity();
        validation.setUser(user);
        validation.setEmail(email);
        validation.setValid(result.getValid());
        validation.setReason(result.getReason());
        validationRepository.save(validation);
    }

}
