package com.example.fintechauth.controller;

import com.example.fintechauth.dto.AccountResponse;
import com.example.fintechauth.entity.Account;
import com.example.fintechauth.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ❌ Broken Access Control / IDOR
    // - 누구의 계좌인지 확인을 안 함
    // - 단지 "로그인만 되어 있으면" 어떤 id든 조회 가능
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(
            @PathVariable Long id,
            @AuthenticationPrincipal Object currentUser   // 사용은 안 함
    ) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("계좌를 찾을 수 없습니다."));

        AccountResponse response = new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getOwner().getEmail()
        );

        return ResponseEntity.ok(response);
    }
}
