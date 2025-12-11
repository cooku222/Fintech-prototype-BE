package com.example.fintechauth.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private static class OtpInfo {
        String code;
        Instant expiresAt;
    }

    private final Map<String, OtpInfo> store = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public String generateOtp(String email) {
        String code = String.format("%06d", random.nextInt(1_000_000));
        OtpInfo info = new OtpInfo();
        info.code = code;
        info.expiresAt = Instant.now().plusSeconds(300); // 5분

        store.put(email, info);

        // 개발용: 콘솔에 출력
        System.out.println("[OTP DEBUG] " + email + " -> " + code);
        return code;
    }

    public boolean validateOtp(String email, String code) {
        OtpInfo info = store.get(email);
        if (info == null) {
            return false;
        }

        if (Instant.now().isAfter(info.expiresAt)) {
            store.remove(email);
            return false;
        }

        boolean ok = info.code.equals(code);
        if (ok) {
            store.remove(email); // 1회용
        }
        return ok;
    }
}
