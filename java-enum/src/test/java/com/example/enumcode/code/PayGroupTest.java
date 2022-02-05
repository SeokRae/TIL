package com.example.enumcode.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PayGroupTest {

    @DisplayName("결제 요청이 어떤 결제 종류인지 확인하는 테스트")
    @Test
    void testCase1() {
        PayGroup code = PayGroup.findByPayCode("카카오페이");
        assertThat(code.name()).isEqualTo("CARD");
        assertThat(code.title()).isEqualTo("카드");
    }
}