package com.pcwang.order.exception;

import com.pcwang.order.enums.ResultEnum;

import javax.persistence.criteria.CriteriaBuilder;

public class OrderException extends RuntimeException{
    private Integer code;

    public OrderException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();

    }
}
