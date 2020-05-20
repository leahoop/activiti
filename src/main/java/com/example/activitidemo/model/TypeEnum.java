package com.example.activitidemo.model;

public enum  TypeEnum {

    SCHOLARSHIP(1, "奖学金"),
    GRANT(2, "助学金");

    private Integer code;
    private String value;

    TypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
