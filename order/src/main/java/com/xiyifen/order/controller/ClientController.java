package com.xiyifen.order.controller;

import com.xiyifen.order.client.ProductClient;
import com.xiyifen.order.dataobject.ProductInfo;
import com.xiyifen.order.dto.CartDTO;
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

        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("1"));
        log.info("response={}",productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String getProductDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("1",5)));

        return "ok";
    }


}
