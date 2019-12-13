package com.pcwang.product.service;



import com.pcwang.product.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
