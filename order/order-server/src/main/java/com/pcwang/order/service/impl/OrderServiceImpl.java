package com.pcwang.order.service.impl;

import com.pcwang.order.dto.OrderDTO;
import com.pcwang.order.entity.OrderDetail;
import com.pcwang.order.entity.OrderMaster;
import com.pcwang.order.enums.OrderStatusEnums;
import com.pcwang.order.enums.PayStatusEnums;
import com.pcwang.order.repository.OrderDetailRepository;
import com.pcwang.order.repository.OrderMasterRepository;
import com.pcwang.order.service.OrderService;
import com.pcwang.order.utils.KeyUtil;
import com.pcwang.product.client.ProductClient;
import com.pcwang.product.common.DecreaseStockInput;
import com.pcwang.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //1.查询商品信息(调用商品服务)
        //获取订单中的所有商品的id
        List<String> productIdList = new ArrayList<>();
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            productIdList.add(orderDetail.getProductId());
        }


        //根据商品id查询商品的详细信息
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

         //TODO 2.计算总价
        BigDecimal orderAmount = new BigDecimal(0);
        //遍历订单，商品，根据商品价格和数量计算总计
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    System.out.println("单价==="+productInfo.getProductPrice());
                    System.out.println("数量==="+orderDetail.getProductQuantity());
                    //单价*数量
                    orderAmount = productInfo.getProductPrice().
                            multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    System.out.println("orderAmount = " + orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }


        }
        //3.扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e ->new DecreaseStockInput(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
