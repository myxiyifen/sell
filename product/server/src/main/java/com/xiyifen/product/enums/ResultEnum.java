package com.xiyifen.product.enums;

import lombok.Data;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by fs.z
 * Date 2018/10/25 15:09
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_STOCK_ERROR(2,"库存有误"),
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
