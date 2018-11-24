package com.xiyifen.product.exception;

import com.xiyifen.product.enums.ResultEnum;

/**
 * Created by fs.z
 * Date 2018/10/25 15:07
 */
public class ProductException extends RuntimeException{

    private Integer code;

    public ProductException(Integer code,String message) {
        super(message);
        this.code = code;
    }


    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
}
