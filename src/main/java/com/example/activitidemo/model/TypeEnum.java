package com.example.activitidemo.model;

import java.util.HashMap;
import java.util.Map;

public enum  TypeEnum {

    SCHOLARSHIP(1, "奖学金"),
    GRANT(2, "助学金");

    private Integer code;
    private String value;

    TypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    static Map<Integer, TypeEnum> codeMap = new HashMap<>();

    static {
        for (TypeEnum type : TypeEnum.values()) {
            codeMap.put(type.getCode(), type);
        }
    }

    public static TypeEnum valueOf(Integer value) {
        return codeMap.get(value);
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
