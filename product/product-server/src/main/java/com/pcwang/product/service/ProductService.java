package com.pcwang.product.service;


import com.pcwang.product.DTO.CartDTO;
import com.pcwang.product.common.DecreaseStockInput;
import com.pcwang.product.entity.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
