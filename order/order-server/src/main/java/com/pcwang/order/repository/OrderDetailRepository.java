package com.pcwang.order.repository;

import com.pcwang.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

}
