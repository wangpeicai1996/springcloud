package com.pcwang.product.controller;



import com.pcwang.product.DTO.CartDTO;
import com.pcwang.product.VO.ProductInfoVO;
import com.pcwang.product.VO.ProductVO;
import com.pcwang.product.VO.ResultVO;
import com.pcwang.product.common.DecreaseStockInput;
import com.pcwang.product.entity.ProductCategory;
import com.pcwang.product.entity.ProductInfo;
import com.pcwang.product.service.ProductCategoryService;
import com.pcwang.product.service.ProductService;
import com.pcwang.product.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查询所有的在架商品
     * @return
     */
    @GetMapping("/list")
    public ResultVO list() {
         List<ProductInfo> productInfoList =    productService.findUpAll();
         List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo :: getCategoryType).collect(Collectors.toList());
         List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        /**
         * {
         *     "code": 0,
         *     "msg": "成功",
         *     "data": [
         *         {
         *             "name": "热榜",
         *             "type": 1,
         *             "foods": [
         *                 {
         *                     "id": "123456",
         *                     "name": "皮蛋粥",
         *                     "price": 1.2,
         *                     "description": "好吃的皮蛋粥",
         *                     "icon": "http://xxx.com",
         *                 }
         *             ]
         *         },
         *         {
         *             "name": "好吃的",
         *             "type": 2,
         *             "foods": [
         *                 {
         *                     "id": "123457",
         *                     "name": "慕斯蛋糕",
         *                     "price": 10.9,
         *                     "description": "美味爽口",
         *                     "icon": "http://xxx.com",
         *                 }
         *             ]
         *         }
         *     ]
         * }
         */
        //构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }

    /**
     * 获取商品列表，提供给订单服务使用
     * @param productList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productList){
        return productService.findList(productList);
    }


    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseStock(decreaseStockInputList);
    }
}
