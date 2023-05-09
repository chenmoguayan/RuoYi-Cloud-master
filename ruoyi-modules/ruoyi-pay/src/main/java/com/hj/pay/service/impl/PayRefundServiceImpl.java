package com.hj.pay.service.impl;

import com.hj.pay.domain.PayRefund;
import com.hj.pay.mapper.PayRefundMapper;
import com.hj.pay.service.IPayRefundService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退费订单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@Service
public class PayRefundServiceImpl implements IPayRefundService 
{
    @Autowired
    private PayRefundMapper payRefundMapper;

    /**
     * 查询退费订单信息
     * 
     * @param roId 退费订单信息主键
     * @return 退费订单信息
     */
    @Override
    public PayRefund selectPayRefundByRoId(Long roId)
    {
        return payRefundMapper.selectPayRefundByRoId(roId);
    }

    /**
     * 查询退费订单信息列表
     * 
     * @param payRefund 退费订单信息
     * @return 退费订单信息
     */
    @Override
    public List<PayRefund> selectPayRefundList(PayRefund payRefund)
    {
        return payRefundMapper.selectPayRefundList(payRefund);
    }

    /**
     * 新增退费订单信息
     *
     * @param payRefund 退费订单信息
     * @return 结果
     */
    @Override
    public PayRefund insertPayRefund(PayRefund payRefund)
    {
        payRefund.setCreateTime(DateUtils.getNowDate());
        int i = payRefundMapper.insertPayRefund(payRefund);
        return payRefund;
    }

    /**
     * 修改退费订单信息
     * 
     * @param payRefund 退费订单信息
     * @return 结果
     */
    @Override
    public int updatePayRefund(PayRefund payRefund)
    {
        payRefund.setUpdateTime(DateUtils.getNowDate());
        return payRefundMapper.updatePayRefund(payRefund);
    }

    /**
     * 批量删除退费订单信息
     * 
     * @param roIds 需要删除的退费订单信息主键
     * @return 结果
     */
    @Override
    public int deletePayRefundByRoIds(Long[] roIds)
    {
        return payRefundMapper.deletePayRefundByRoIds(roIds);
    }

    /**
     * 删除退费订单信息信息
     * 
     * @param roId 退费订单信息主键
     * @return 结果
     */
    @Override
    public int deletePayRefundByRoId(Long roId)
    {
        return payRefundMapper.deletePayRefundByRoId(roId);
    }
}
