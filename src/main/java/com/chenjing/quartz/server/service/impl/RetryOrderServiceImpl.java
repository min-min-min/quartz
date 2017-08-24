package com.chenjing.quartz.server.service.impl;

import com.chenjing.quartz.server.entity.RetryOrder;
import com.chenjing.quartz.server.mapper.RetryOrderMapper;
import com.chenjing.quartz.server.service.RetryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chenjing on 2017/6/17.
 */
@Service
public class RetryOrderServiceImpl implements RetryOrderService {

    @Autowired
    private RetryOrderMapper retryOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return retryOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RetryOrder record) {
        return retryOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(RetryOrder record) {
        return retryOrderMapper.insertSelective(record);
    }

    @Override
    public RetryOrder selectByPrimaryKey(Integer id) {
        return retryOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RetryOrder record) {
        return retryOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(RetryOrder record) {
        return retryOrderMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(RetryOrder record) {
        return retryOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByOrderId(String orderId) {
        return retryOrderMapper.deleteByOrderId(orderId);
    }

    @Override
    public List<RetryOrder> listByAll() {
        return retryOrderMapper.listByAll();
    }

    @Override
    public int counyByOrderId(String orderId) {
        return retryOrderMapper.countByOrderId(orderId);
    }
}
