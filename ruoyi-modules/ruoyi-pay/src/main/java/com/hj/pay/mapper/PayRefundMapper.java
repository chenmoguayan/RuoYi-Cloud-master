package com.hj.pay.mapper;

import com.hj.pay.domain.PayRefund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 退费订单信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@Mapper
public interface PayRefundMapper
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
    public int insertPayRefund(PayRefund payRefund);

    /**
     * 修改退费订单信息
     * 
     * @param payRefund 退费订单信息
     * @return 结果
     */
    public int updatePayRefund(PayRefund payRefund);

    /**
     * 删除退费订单信息
     * 
     * @param roId 退费订单信息主键
     * @return 结果
     */
    public int deletePayRefundByRoId(Long roId);

    /**
     * 批量删除退费订单信息
     * 
     * @param roIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayRefundByRoIds(Long[] roIds);
}
