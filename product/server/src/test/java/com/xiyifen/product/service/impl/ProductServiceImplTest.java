package com.xiyifen.product.service.impl;

import com.xiyifen.product.DTO.CartDTO;
import com.xiyifen.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @Author: fs.z
 * @Date: 2018/11/20 14:50
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void decreaseStock() {
        CartDTO cartDTO=new CartDTO("1",3);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}