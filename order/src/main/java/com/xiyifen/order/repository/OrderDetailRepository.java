package com.xiyifen.order.repository;

import com.xiyifen.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: fs.z
 * @Date: 2018/11/20 16:28
 * @Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,
        String> {


}
