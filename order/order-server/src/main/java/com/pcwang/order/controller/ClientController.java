package com.pcwang.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ClientController {

 /*   @Autowired
    //springcloud提供
    private LoadBalancerClient loadBalancerClient;

    //@Autowired
    //定义配置类中的对象
    //private  RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getFromProductMsg(){
        //第一种方式
        *//*RestTemplate restTemplate = new RestTemplate();
        //缺陷，服务地址固定，多台服务器，多个动态地址的场景下不适用
        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);*//*

        //第二种方式,RestTemplate和LoadBalancerClient配合使用
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
        String response = restTemplate.getForObject(url, String.class);

        //第三种方式,利用@Loadbalance注解,直接使用应用名称调用
        *//*String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);*//*
        log.info("response = {}",response);
        return response;
    }*/


    /**
     * 利用feign客户端通信
     */

   // @Autowired
//    private ProductClient productClient;
//
//    @GetMapping("/getProductMsg")
//    public String getProductMsg() {
//        String response = productClient.productMsg();
//        log.info("response = {}", response);
//        return response;
//    }
//
//    @PostMapping("/product/listForOrder")
//    public String getProductInfoList(List<String> productIdList){
//        List<ProductInfoOutput> response = productClient.listForOrder(Arrays.asList("164103465734242707"));
//        log.info("response = {}", response);
//        return "ok";
//    }
//
//    @GetMapping("/product/decreaseStock")
//    public String decreaseStock(){
//        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("157875227953464068",3)));
//        return "ok";
//    }

}
