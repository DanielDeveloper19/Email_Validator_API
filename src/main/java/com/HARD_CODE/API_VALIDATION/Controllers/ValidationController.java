package com.HARD_CODE.API_VALIDATION.Controllers;


import com.HARD_CODE.API_VALIDATION.DTO.ValidationResultDto;
import com.HARD_CODE.API_VALIDATION.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin(origins = "*")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping("/validate")
    public ValidationResultDto validate(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        if (email == null || email.isEmpty()) {
            return new ValidationResultDto("", false, "email requerido");
        }

        return validationService.validateEmail(email);
    }

    @PostMapping("/validate/bulk")
    public Map<String, Object> validateBulk(@RequestBody Map<String, List<String>> request) {
        List<String> emails = request.get("emails");

        if (emails == null || emails.isEmpty()) {
            throw new IllegalArgumentException("lista de emails requerida");
        }

        return validationService.validateBulk(emails);
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }

}
