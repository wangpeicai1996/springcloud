package com.pcwang.order.service;

import com.pcwang.order.dto.OrderDTO;
import com.pcwang.order.entity.OrderDetail;
import com.pcwang.order.entity.OrderMaster;

public interface OrderService {



    /**
     * 创建订单
     * @param orderDTO
        * @return
        */
        OrderDTO create(OrderDTO orderDTO);


        }
