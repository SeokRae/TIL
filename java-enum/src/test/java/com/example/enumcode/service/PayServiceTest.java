package com.example.enumcode.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PayServiceTest {

    @DisplayName("결제 방법 확인 테스트")
    @Test
    void testCase1() {
        String payGroup = PayService.getPayGroup("카카오페이");
        assertThat(payGroup).isEqualTo("카드");
    }
}