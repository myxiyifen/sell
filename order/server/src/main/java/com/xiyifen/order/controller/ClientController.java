package com.xiyifen.order.controller;


import com.xiyifen.order.dataobject.ProductInfo;
import com.xiyifen.order.dto.CartDTO;
import com.xiyifen.product.client.ProductClient;
import com.xiyifen.product.common.DecreaseStockInput;
import com.xiyifen.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fs.z
 * Date 2018/10/25 14:05
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductList")
    public String getProductList(){

        List<ProductInfoOutput> productInfoOutputs = productClient.listForOrder(Arrays.asList("1"));
        log.info("response={}",productInfoOutputs);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String getProductDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("1",5)));

        return "ok";
    }


}
