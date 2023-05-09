package com.hj.pay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 订单中间表对象 hs_prescription
 * 
 * @author ruoyi
 * @date 2023-04-20
 */
public class HsPrescription extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long prId;

    /** 订单id */
    @Excel(name = "订单id")
    private String oId;

    /** 处方类型 */
    @Excel(name = "处方类型")
    private String prType;

    /** 检查或者药品id */
    @Excel(name = "检查或者药品id")
    private String cId;

    /** 数量 */
    @Excel(name = "数量")
    private Long number;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String payType;
    private HsOrder hsOrder;

    public HsOrder getHsOrder() {
        return hsOrder;
    }

    public void setHsOrder(HsOrder hsOrder) {
        this.hsOrder = hsOrder;
    }

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatBy;
    private String checkid;

    public String getCheckid() {
        return checkid;
    }

    public void setCheckid(String checkid) {
        this.checkid = checkid;
    }

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    public void setPrId(Long prId) 
    {
        this.prId = prId;
    }

    public Long getPrId() 
    {
        return prId;
    }
    public void setoId(String oId) 
    {
        this.oId = oId;
    }

    public String getoId() 
    {
        return oId;
    }
    public void setPrType(String prType) 
    {
        this.prType = prType;
    }

    public String getPrType() 
    {
        return prType;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setCreatBy(String creatBy) 
    {
        this.creatBy = creatBy;
    }

    public String getCreatBy() 
    {
        return creatBy;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("prId", getPrId())
            .append("oId", getoId())
            .append("prType", getPrType())
            .append("cId", getcId())
            .append("number", getNumber())
            .append("payType", getPayType())
            .append("creatBy", getCreatBy())
            .append("creatTime", getCreatTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
