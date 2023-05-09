package com.hj.pay.service.impl;

import com.hj.pay.domain.PayChargeable;
import com.hj.pay.mapper.PayChargeableMapper;
import com.hj.pay.service.IPayChargeableService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收费订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@Service
public class PayChargeableServiceImpl implements IPayChargeableService 
{
    @Autowired
    private PayChargeableMapper payChargeableMapper;

    /**
     * 查询收费订单
     * 
     * @param coId 收费订单主键
     * @return 收费订单
     */
    @Override
    public PayChargeable selectPayChargeableByCoId(Long coId)
    {
        return payChargeableMapper.selectPayChargeableByCoId(coId);
    }

    /**
     * 查询收费订单列表
     * 
     * @param payChargeable 收费订单
     * @return 收费订单
     */
    @Override
    public List<PayChargeable> selectPayChargeableList(PayChargeable payChargeable)
    {
        return payChargeableMapper.selectPayChargeableList(payChargeable);
    }

    /**
     * 新增收费订单
     *
     * @param payChargeable 收费订单
     * @return 结果
     */
    @Override
    public PayChargeable insertPayChargeable(PayChargeable payChargeable)
    {
        if (payChargeableMapper.selectPayChargeableByCoId(payChargeable.getCoId()) != null){
            return null;
        }
        payChargeable.setCreateTime(DateUtils.getNowDate());
        int i = payChargeableMapper.insertPayChargeable(payChargeable);
        if (i != 0){
            System.out.println(payChargeable.getCoId());
        }
        return payChargeable;
//        return payChargeableMapper.insertPayChargeable(payChargeable);
    }

    /**
     * 修改收费订单
     * 
     * @param payChargeable 收费订单
     * @return 结果
     */
    @Override
    public int updatePayChargeable(PayChargeable payChargeable)
    {
        payChargeable.setUpdateTime(DateUtils.getNowDate());
        return payChargeableMapper.updatePayChargeable(payChargeable);
    }

    /**
     * 批量删除收费订单
     * 
     * @param coIds 需要删除的收费订单主键
     * @return 结果
     */
    @Override
    public int deletePayChargeableByCoIds(Long[] coIds)
    {
        return payChargeableMapper.deletePayChargeableByCoIds(coIds);
    }

    /**
     * 删除收费订单信息
     * 
     * @param coId 收费订单主键
     * @return 结果
     */
    @Override
    public int deletePayChargeableByCoId(Long coId)
    {
        return payChargeableMapper.deletePayChargeableByCoId(coId);
    }

    /**
     * 支付成功之后生成订单
     */
    public boolean addChargeableOlder(PayChargeable payChargeable){
        return (payChargeableMapper.insertPayChargeable(payChargeable) != 0);
    }
}
