package com.example.enumcode.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PayGroup {

    CASH("현금", Arrays.asList("계좌이체", "가상계좌", "현금영수증")),
    CARD("카드", Arrays.asList("신용카드", "네이버페이", "카카오페이")),
    ETC("기타", Arrays.asList("쿠폰", "문화상품권")),
    EMPTY("없음", Collections.emptyList());

    private final String title;
    private final List<String> payList;

    PayGroup(String title, List<String> payList) {
        this.title = title;
        this.payList = payList;
    }

    public static PayGroup findByPayCode(String code) {
        return Arrays.stream(values())
                .filter(payGroup -> payGroup.hasPayCode(code))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasPayCode(String code) {
        return payList.stream().anyMatch(pay -> pay.equals(code));
    }

    public String title() {
        return title;
    }
}
