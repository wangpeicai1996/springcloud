package com.pcwang.order.convertoer;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pcwang.order.dto.OrderDTO;
import com.pcwang.order.entity.OrderDetail;
import com.pcwang.order.enums.ResultEnum;
import com.pcwang.order.exception.OrderException;
import com.pcwang.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {


        public static OrderDTO covert(OrderForm orderForm) {
            Gson gson = new Gson();
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setBuyerName(orderForm.getName());
            orderDTO.setBuyerPhone(orderForm.getPhone());
            orderDTO.setBuyerAddress(orderForm.getAddress());
            orderDTO.setBuyerOpenid(orderForm.getOpenid());

            List<OrderDetail> orderDetailList = new ArrayList<>();
            try {
                orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {

                }.getType());
            } catch (Exception e) {
                log.error("【json转换】错误，string={}", orderForm.getItems());
                throw new OrderException(ResultEnum.PARAM_ERROR);
            }
            orderDTO.setOrderDetailList(orderDetailList);
            return orderDTO;
        }
}
