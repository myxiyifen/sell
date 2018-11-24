package com.xiyifen.product.DTO;

import lombok.Data;

/**
 * Created by fs.z
 * Date 2018/10/25 14:56
 */
@Data
public class CartDTO {

    // 商品id
    private String productId;

    // 商品数量
    private Integer productQuantity;



    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
