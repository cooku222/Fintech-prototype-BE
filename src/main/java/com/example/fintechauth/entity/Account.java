package com.example.fintechauth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;   // 계좌번호 (ex. 100-1234-5678)

    @Column(nullable = false)
    private Long balance;           // 잔액 (원 단위)

    // 이 계좌의 소유자 (User와 Many-To-One)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    public Account() {
    }

    public Account(String accountNumber, Long balance, User owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
