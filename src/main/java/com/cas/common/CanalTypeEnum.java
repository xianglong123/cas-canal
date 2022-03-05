package com.cas.common;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 9:22 下午
 * @desc
 */
public enum CanalTypeEnum {

    /**
     *
     */
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    ;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static CanalTypeEnum getByCode(String code) {
        CanalTypeEnum[] values = CanalTypeEnum.values();
        for (CanalTypeEnum canalTypeEnum : values) {
            if (canalTypeEnum.code.equals(code)) {
                return canalTypeEnum;
            }
        }
        return null;
    }

    CanalTypeEnum(String code) {
        this.code = code;
    }
}
