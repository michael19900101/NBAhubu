package com.aotuman.nbahubu.common;


public class RadarItem {
    String labelName;//标签名称
    String value;//数值
    float progress;//0-1;

    public RadarItem(String labelName, String value, float progress) {
        this.labelName = labelName;
        this.value = value;
        this.progress = progress;
    }
}