package com.example.fintechauth.controller;

import com.example.fintechauth.dto.AccountResponse;
import com.example.fintechauth.entity.Account;
import com.example.fintechauth.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ⚠️ 취약점 포인트: 본인 소유 확인 로직 제거 + URL로 ID 직접 입력 가능
    // 기존: @GetMapping("/my") -> 변경: @GetMapping("/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        
        // 1. 요청된 ID로 계좌를 조회 (소유자 체크 없음)
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("계좌를 찾을 수 없습니다."));

        // 2. 결과 반환
        AccountResponse response = new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getOwner().getEmail()
        );

        return ResponseEntity.ok(response);
    }
}