package com.example.fintechauth.dto;

public class AccountResponse {

    private Long id;
    private String accountNumber;
    private Long balance;
    private String ownerEmail;

    public AccountResponse(Long id, String accountNumber, Long balance, String ownerEmail) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.ownerEmail = ownerEmail;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }
}
