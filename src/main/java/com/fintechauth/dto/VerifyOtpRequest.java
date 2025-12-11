package com.fintechauth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class VerifyOtpRequest {

    @Email
    private String email;

    @NotBlank
    private String otp;

    public VerifyOtpRequest() {
    }

    public VerifyOtpRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public String getOtp() {
        return otp;
    }
}
