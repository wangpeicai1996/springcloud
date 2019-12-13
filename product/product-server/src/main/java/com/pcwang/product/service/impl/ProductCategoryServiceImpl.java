package com.pcwang.product.service.impl;

import com.pcwang.product.dao.ProductCategoryRepository;
import com.pcwang.product.entity.ProductCategory;

import com.pcwang.product.service.ProductCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList){
        return productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11,22));
    }
}
