# 수빈은행 (Subin Bank) Backend

수빈은행 서비스의 **백엔드 API 서버**입니다.  
회원가입/로그인(인증) 등 기본 인증 흐름을 제공하며, 프론트엔드와 REST API로 통신합니다.

## Tech Stack
- Java / Spring Boot
- Spring Web / Spring Security
- JPA (Hibernate)
- DB: H2 -> MySQL
- Build: Maven

## Features
- 회원가입 / 로그인
- 인증/인가 기반 API (예: JWT, 세션 등 프로젝트 설정에 따라)
- 계정/유저 데이터 CRUD (JPA)
- 예외 처리 및 공통 응답 구조(선택)

## Getting Started

### 1) Requirements
- JDK 17+ (권장)
- MySQL 

### 2) Run (Local)
```bash
# Gradle
./gradlew bootRun

# or build & run
./gradlew build
java -jar build/libs/*.jar
