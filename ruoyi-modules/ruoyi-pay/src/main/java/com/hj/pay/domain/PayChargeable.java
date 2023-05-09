package com.hj.pay.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 收费订单对象 pay_chargeable
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@Validated
public class PayChargeable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收费订单号 */
    @Range(max = 30)
    @Excel(name = "收费订单号")
    private Long coId;

    /** 挂号单号 */
    @Range(max = 30)
    @Excel(name = "挂号单号")
    private Long oId;

    /**
     * 患者姓名
     */
    @Length(max = 10)
    @Excel(name = "患者姓名")
    private String pName;


    /** 药品费用,检查费用 */
    @Digits(integer = 8,fraction = 2)
    @Excel(name = "收费总金额")
    private BigDecimal coTotalAmount;

    /** 支付类型 */
    @Range(max = 2)
    @Excel(name = "支付类型")
    private Long coPayType;

    /** 支付状态 */
    @Range(max = 2)
    @Excel(name = "支付状态")
    private Long coStatus;

    public PayChargeable() {
    }

    public PayChargeable(Long coId, Long oId, String pName, BigDecimal coTotalAmount, Long coPayType, Long coStatus, String searchValue, String createBy, Date createTime, String updateBy, Date updateTime, String remark, Integer isDeleted) {
        this.coId = coId;
        this.oId = oId;
        this.pName = pName;
        this.coTotalAmount = coTotalAmount;
        this.coPayType = coPayType;
        this.coStatus = coStatus;
        this.isDeleted = isDeleted;
    }

    /** hidden */
    private Integer isDeleted;


    public void setCoId(Long coId)
    {
        this.coId = coId;
    }

    @Override
    public String toString() {
        return "PayChargeable{" +
                "coId=" + coId +
                ", oId=" + oId +
                ", pName='" + pName + '\'' +
                ", coTotalAmount=" + coTotalAmount +
                ", coPayType=" + coPayType +
                ", coStatus=" + coStatus +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Long getCoId()
    {
        return coId;
    }
    public void setoId(Long oId)
    {
        this.oId = oId;
    }

    public Long getoId()
    {
        return oId;
    }
    public void setCoTotalAmount(BigDecimal coTotalAmount)
    {
        this.coTotalAmount = coTotalAmount;
    }

    public BigDecimal getCoTotalAmount()
    {
        return coTotalAmount;
    }
    public void setCoPayType(Long coPayType)
    {
        this.coPayType = coPayType;
    }

    public Long getCoPayType()
    {
        return coPayType;
    }
    public void setCoStatus(Long coStatus)
    {
        this.coStatus = coStatus;
    }

    public Long getCoStatus()
    {
        return coStatus;
    }
    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted()
    {
        return isDeleted;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
