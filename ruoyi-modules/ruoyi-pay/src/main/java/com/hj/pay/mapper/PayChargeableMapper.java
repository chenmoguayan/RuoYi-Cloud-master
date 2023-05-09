package com.hj.pay.mapper;

import com.hj.pay.domain.PayChargeable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 收费订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@Mapper
public interface PayChargeableMapper
{
    /**
     * 查询收费订单
     * 
     * @param coId 收费订单主键
     * @return 收费订单
     */
    public PayChargeable selectPayChargeableByCoId(Long coId);

    /**
     * 查询收费订单列表
     * 
     * @param payChargeable 收费订单
     * @return 收费订单集合
     */
    public List<PayChargeable> selectPayChargeableList(PayChargeable payChargeable);

    /**
     * 新增收费订单
     * 
     * @param payChargeable 收费订单
     * @return 结果
     */
    public int insertPayChargeable(PayChargeable payChargeable);

    /**
     * 修改收费订单
     * 
     * @param payChargeable 收费订单
     * @return 结果
     */
    public int updatePayChargeable(PayChargeable payChargeable);

    /**
     * 删除收费订单
     * 
     * @param coId 收费订单主键
     * @return 结果
     */
    public int deletePayChargeableByCoId(Long coId);

    /**
     * 批量删除收费订单
     * 
     * @param coIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayChargeableByCoIds(Long[] coIds);


}
