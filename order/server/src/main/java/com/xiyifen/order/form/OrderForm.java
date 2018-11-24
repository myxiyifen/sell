package com.xiyifen.order.form;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 从前端传回的值
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    // 买家微信号
    @NotEmpty(message = "openId必填")
    private String openId;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
