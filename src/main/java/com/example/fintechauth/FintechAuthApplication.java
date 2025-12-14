package com.example.fintechauth;

import com.example.fintechauth.entity.Account;
import com.example.fintechauth.entity.Role;
import com.example.fintechauth.entity.User;
import com.example.fintechauth.repository.AccountRepository;
import com.example.fintechauth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FintechAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FintechAuthApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            UserRepository userRepository,
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (userRepository.count() == 0) {

                // 유저 2명 생성
                User alice = new User(
                        "Alice",
                        "alice@test.com",
                        passwordEncoder.encode("alice1234!"),
                        Role.USER
                );

                User bob = new User(
                        "Elon_Musk",
                        "ElonMusk@test.com",
                        passwordEncoder.encode("bob1234!"),
                        Role.USER
                );

                User admin = new User(
                        "Admin",
                        "admin@test.com",
                        passwordEncoder.encode("admin1234!"),
                        Role.ADMIN
                );

                userRepository.save(alice);
                userRepository.save(bob);
                userRepository.save(admin);

                // 각자 계좌 생성
                Account a1 = new Account(
                        "100-1111-1111",
                        1_000_000L,
                        alice
                );

                Account a2 = new Account(
                        "200-2222-2222",
                        1_000_000_000_000L,
                        bob
                );

                accountRepository.save(a1);
                accountRepository.save(a2);
            }
        };
    }
}
