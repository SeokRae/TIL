package com.example.enumcode.code;

public enum PayType {
    계좌이체("계좌이체"),
    가상계좌("가상계좌"),
    현금영수증("현금영수증"),
    신용카드("신용카드"),
    네이버페이("네이버페이"),
    카카오페이("카카오페이"),
    쿠폰("쿠폰"),
    문화상품권("문화상품권");

    private final String title;

    PayType(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
