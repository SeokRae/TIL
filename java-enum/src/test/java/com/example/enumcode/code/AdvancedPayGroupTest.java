package com.example.enumcode.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdvancedPayGroupTest {

    @DisplayName("결제 타입 조회 테스트")
    @Test
    void testCase1() {
        AdvancedPayGroup payGroup = AdvancedPayGroup.findByPayCode(PayType.가상계좌);

        assertThat(payGroup.name()).isEqualTo("CASH");
        assertThat(payGroup.title()).isEqualTo("현금");
    }
}