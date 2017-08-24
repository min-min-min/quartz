package com.chenjing.quartz.server.mapper;


import com.chenjing.quartz.server.entity.RetryOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RetryOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RetryOrder record);

    int insertSelective(RetryOrder record);

    RetryOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RetryOrder record);

    int updateByPrimaryKeyWithBLOBs(RetryOrder record);

    int updateByPrimaryKey(RetryOrder record);

    int deleteByOrderId(String orderId);

    List<RetryOrder> listByAll();

    int countByOrderId(String orderId);
}