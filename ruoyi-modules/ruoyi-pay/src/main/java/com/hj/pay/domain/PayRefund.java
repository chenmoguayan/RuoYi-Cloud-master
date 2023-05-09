package com.hj.pay.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * 退费订单信息对象 pay_refund
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@Validated
public class PayRefund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 退费订单单号 */
    @Range(max = 30)
    @Excel(name = "退费订单号")
    private Long roId;

    /** 挂号单号 */
    @Range(max = 30)
    @Excel(name = "挂号单号")
    private Long oId;

    /** 收费订单号 */
    @Length(max = 30)
    @Excel(name = "收费订单号")
    private Long coId;

    /** 患者姓名 */
    @Range(max = 10)
    @Excel(name = "患者姓名")
    private String pName;
    /** 退费总金额 */
    @Digits(integer = 8,fraction = 2)
    @Excel(name = "退费总金额")
    private BigDecimal roTotalAmonut;

    /** 订单状态 */
    @Range(max = 2)
    @Excel(name = "订单状态")
    private Long roStatus;

    /** 逻辑删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    public void setRoId(Long roId) 
    {
        this.roId = roId;
    }

    public Long getRoId() 
    {
        return roId;
    }
    public void setoId(Long oId) 
    {
        this.oId = oId;
    }

    public Long getoId() 
    {
        return oId;
    }
    public void setCoId(Long coId) 
    {
        this.coId = coId;
    }

    public Long getCoId() 
    {
        return coId;
    }
    public void setRoTotalAmonut(BigDecimal roTotalAmonut) 
    {
        this.roTotalAmonut = roTotalAmonut;
    }

    public BigDecimal getRoTotalAmonut() 
    {
        return roTotalAmonut;
    }
    public void setRoStatus(Long roStatus) 
    {
        this.roStatus = roStatus;
    }

    public Long getRoStatus() 
    {
        return roStatus;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    @Override
    public String toString() {
        return "PayRefund{" +
                "roId=" + roId +
                ", oId=" + oId +
                ", coId=" + coId +
                ", pName='" + pName + '\'' +
                ", roTotalAmonut=" + roTotalAmonut +
                ", roStatus=" + roStatus +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
