package com.example.enumcode.advance;

public enum CardType implements EnumMapperType {

    NAVER_PAY("네이버페이"),
    KAKAO_PAY("카카오페이");

    private final String title;

    CardType(String title) {
        this.title = title;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }
}
