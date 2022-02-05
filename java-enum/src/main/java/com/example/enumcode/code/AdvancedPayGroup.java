package com.example.enumcode.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum AdvancedPayGroup {

    CASH("현금", Arrays.asList(PayType.계좌이체, PayType.가상계좌, PayType.현금영수증)),
    CARD("카드", Arrays.asList(PayType.신용카드, PayType.네이버페이, PayType.카카오페이)),
    ETC("기타", Arrays.asList(PayType.쿠폰, PayType.문화상품권)),
    EMPTY("없음", Collections.emptyList());

    private final String title;
    private final List<PayType> payList;

    AdvancedPayGroup(String title, List<PayType> payList) {
        this.title = title;
        this.payList = payList;
    }

    public static AdvancedPayGroup findByPayCode(PayType payType) {
        return Arrays.stream(values())
                .filter(payGroup -> payGroup.hasPayCode(payType))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasPayCode(PayType type) {
        return payList.stream().anyMatch(pay -> pay == type);
    }

    public String title() {
        return title;
    }
}
