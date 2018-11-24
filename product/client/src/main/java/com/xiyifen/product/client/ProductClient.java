package com.xiyifen.product.client;


import com.xiyifen.product.common.DecreaseStockInput;
import com.xiyifen.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by fs.z
 * Date 2018/10/25 11:31
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

//    @Component
//    static class ProductClientFallback implements ProductClient {
//
//        @Override
//        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
//            return null;
//        }
//
//        @Override
//        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
//
//        }
//    }
}
