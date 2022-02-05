package com.example.enumcode.service;

public class PayService {

    public static String getPayGroup(String payCode) {
        if("계좌이체".equals(payCode) || "가상계좌".equals(payCode)) {
            return "현금";
        } else if("페이코".equals(payCode) || "카카오페이".equals(payCode)) {
            return "카드";
        } else {
            return "제공되지 않은 결제 방법";
        }
    }
}
