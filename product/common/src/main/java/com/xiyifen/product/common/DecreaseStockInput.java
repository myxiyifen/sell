package com.xiyifen.product.common;

import lombok.Data;

/**
 * @Author: fs.z
 * @Date: 2018/11/21 22:48
 * @Description:
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
