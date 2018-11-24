package com.xiyifen.order.controller;

import com.xiyifen.order.converter.OrderForm2OrderDTOConverter;
import com.xiyifen.order.exception.OrderException;
import com.xiyifen.order.VO.ResultVO;
import com.xiyifen.order.dto.OrderDTO;
import com.xiyifen.order.enums.ResultEnum;
import com.xiyifen.order.form.OrderForm;
import com.xiyifen.order.service.OrderService;
import com.xiyifen.order.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class orderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建订单参数不正确】,orderForm = {}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String,String> map=new HashMap<>();
        map.put("openId",result.getOrderId());
        return ResultVoUtil.success(map);
    }
}
