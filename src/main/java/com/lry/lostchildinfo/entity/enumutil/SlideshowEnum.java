package com.lry.lostchildinfo.entity.enumutil;

import lombok.Data;

/**
 * @author : jdl
 * @description : 轮播图状态
 * @ClassName : SlideshowEnum
 * @create : 2022-01-24 18:16
 */
public enum SlideshowEnum {

    ShufflingIn(0,"轮播中"),
    ShufflingOut(0,"不在轮播中");

    private Integer type;

    private String value;

    SlideshowEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueByType(Integer type){
        for(SlideshowEnum slideshowEnum : values()){
            if (slideshowEnum.type.equals(type))
                return slideshowEnum.value;
        }
        return null;
    }
}
