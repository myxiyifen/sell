package com.xiyifen.order.client;

import com.xiyifen.order.dataobject.ProductInfo;
import com.xiyifen.order.dto.CartDTO;
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
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
     void decreaseStock(@RequestBody List<CartDTO> cartDTOList);



}
