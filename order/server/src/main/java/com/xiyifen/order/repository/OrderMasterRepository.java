package com.xiyifen.order.repository;

import com.xiyifen.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

}
