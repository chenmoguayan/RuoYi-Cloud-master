package com.hj.pay.service;

import com.hj.pay.domain.PayRefund;

import java.util.List;

/**
 * 退费订单信息Service接口
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
public interface IPayRefundService 
{
    /**
     * 查询退费订单信息
     * 
     * @param roId 退费订单信息主键
     * @return 退费订单信息
     */
    public PayRefund selectPayRefundByRoId(Long roId);

    /**
     * 查询退费订单信息列表
     * 
     * @param payRefund 退费订单信息
     * @return 退费订单信息集合
     */
    public List<PayRefund> selectPayRefundList(PayRefund payRefund);

    /**
     * 新增退费订单信息
     *
     * @param payRefund 退费订单信息
     * @return 结果
     */
    public PayRefund insertPayRefund(PayRefund payRefund);

    /**
     * 修改退费订单信息
     * 
     * @param payRefund 退费订单信息
     * @return 结果
     */
    public int updatePayRefund(PayRefund payRefund);

    /**
     * 批量删除退费订单信息
     * 
     * @param roIds 需要删除的退费订单信息主键集合
     * @return 结果
     */
    public int deletePayRefundByRoIds(Long[] roIds);

    /**
     * 删除退费订单信息信息
     * 
     * @param roId 退费订单信息主键
     * @return 结果
     */
    public int deletePayRefundByRoId(Long roId);
}
