# Spring Security
 - 스프링 시큐리티 정리하기

## Table of Contents

1. 스프링 시큐리티의 보안 설정 API 와 이와 연계된 각 Filter들에 대해 학습한다.
   - 각 API의 개념과 기본적인 사용법, API 처리 과정, API 동작방식 등 학습
   - API 설정 시 생성 및 초기화 되어 사용자의 요청을 처리하는 Filter 학습

2. 스프링 시큐리티 내부 아키텍처와 각 객체의 역할 및 처리과정을 학습
   - 초기화 과정, 인증 과정, 인가 과정등을 아키텍처적인 관점에서 학습

3. 실전 프로젝트
   - 인증 기능 구현 - Form 방식, Ajax 인증 처리
   - 인가 기능 구현 - DB와 연동하여 권한 제어 시스템 구현

### 2. 스프링 시큐리티 기본 API & Filter 이해

### 3. 시큐리티 주요 아키텍처 이해

3. 시큐리티 주요 아키텍처 이해
    1) Proxy
    2) 필터 초기화와 다중 보안 설정
    3) Authentication
    4) SecurityContextHolder, SecurityContext
    5) SecurityContextPersistenceFilter
    6) Authentication
       (1) AuthenticationManager Basic
       (2) AuthenticationManager Advance
    7) AuthenticationProvider
    8) Authorization, FilterSecurityInterceptor
    9) AccessDecisionManager, AccessDecisionVoter
    10) 스프링 시큐리티 필터 및 아키텍처 정리

### 4. 인증(Authentication) 프로세스 구현

1. 실전 프로젝트 구성
2. 메뉴 권한 및 WebIgnore 설정
3. Form 인증
    1) User 등록 / PasswordEncoder
    2) CustomUserDetailsService 구현
    3) CustomAuthenticationProvider 구현
    4) CustomLoginFormPage
    5) 로그아웃 및 화면 보안 처리
    6) WebAuthenticationDetails, AuthenticationDetailsSource
    7) CustomAuthenticationSuccessHandler
    8) CustomAuthenticationFailureHandler
    9) AccessDeniedHandler
    10) CSRF, csrfFilter
    11) 인증 사용자 정보 구하기

4. Ajax 인증
    1) 흐름 및 개요
    2) AjaxAuthenticationFilter
    3) AjaxAuthenticationProvider
    4) AjaxAuthenticationSuccessHandler
    5) AjaxAuthenticationFailureHandler
    6) AjaxLoginUrlAuthenticationEntryPoint & AjaxAccessDeniedHandler
    7) DSL로 Config 설정하기
    8) 로그인 Ajax 구현 & CSRF

### 5. 인가(Authorization) 프로세스 구현 - DB 연동
