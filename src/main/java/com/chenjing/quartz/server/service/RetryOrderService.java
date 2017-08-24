package com.chenjing.quartz.server.service;


import com.chenjing.quartz.server.entity.RetryOrder;

import java.util.List;

/**
 * Created by Chenjing on 2017/6/17.
 */
public interface RetryOrderService {
    int deleteByPrimaryKey(Integer id);

    int insert(RetryOrder record);

    int insertSelective(RetryOrder record);

    RetryOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RetryOrder record);

    int updateByPrimaryKeyWithBLOBs(RetryOrder record);

    int updateByPrimaryKey(RetryOrder record);

    int deleteByOrderId(String orderId);

    List<RetryOrder> listByAll();

    int counyByOrderId(String orderId);
}
