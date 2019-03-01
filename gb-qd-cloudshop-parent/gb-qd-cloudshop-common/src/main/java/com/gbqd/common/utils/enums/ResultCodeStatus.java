package com.gbqd.common.utils.enums;

public enum ResultCodeStatus {

    SUCCESS("1","成功"),FAIL("0","失败");//这个后面必须有分号

    private String code;
    private String name;

    private ResultCodeStatus(String code,String name) {
        this.code = code;
        this.name = name();
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }

}
