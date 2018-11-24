package com.xiyifen.order.service.impl;


import com.xiyifen.order.dataobject.OrderDetail;
import com.xiyifen.order.dataobject.OrderMaster;
import com.xiyifen.order.dataobject.ProductInfo;
import com.xiyifen.order.dto.CartDTO;
import com.xiyifen.order.dto.OrderDTO;
import com.xiyifen.order.enums.OrderStatusEnum;
import com.xiyifen.order.enums.PayStatusEnum;
import com.xiyifen.order.repository.OrderDetailRepository;
import com.xiyifen.order.repository.OrderMasterRepository;
import com.xiyifen.order.service.OrderService;
import com.xiyifen.order.utils.KeyUtil;
import com.xiyifen.product.client.ProductClient;
import com.xiyifen.product.common.DecreaseStockInput;
import com.xiyifen.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 唯一的订单id
        String orderId = KeyUtil.genUniqueKey();

        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputList = productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoOutputList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    // 计算单价：价格 x 数量
                    orderAmout = productInfo.getProductPrice().multiply(new
                            BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    // 订单详情id
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());

                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream().map(e
                -> new DecreaseStockInput(e
                .getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
