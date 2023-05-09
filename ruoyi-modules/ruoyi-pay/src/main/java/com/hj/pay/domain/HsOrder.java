package com.hj.pay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 挂号订单对象 hs_order
 * 
 * @author ruoyi
 * @date 2023-04-15
 */
public class HsOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long oId;

    /** 患者id */
    @Excel(name = "患者id")
    private Long oPid;

    /** 医生排班id */
    @Excel(name = "医生排班id")
    private Long oScheduleid;

    /** 状态 */
    @Excel(name = "状态")
    private String oStatus;

    /** 流水编号 */
    @Excel(name = "流水编号")
    private Long oPipno;

    /** 挂号费 */
    @Excel(name = "挂号费")
    private String oRegistered;

    /** 挂号类型 */
    @Excel(name = "挂号类型")
    private String oType;

    /** 挂号时段 */
    @Excel(name = "挂号时段")
    private String oTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatBy;

    private HsPatient hsPatient;

    public HsPatient getHsPatient() {
        return hsPatient;
    }

    public void setHsPatient(HsPatient hsPatient) {
        this.hsPatient = hsPatient;
    }

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 是否删除 */
    private Long isDeleted;

    public void setoId(Long oId) 
    {
        this.oId = oId;
    }

    public Long getoId() 
    {
        return oId;
    }
    public void setoPid(Long oPid) 
    {
        this.oPid = oPid;
    }

    public Long getoPid() 
    {
        return oPid;
    }
    public void setoScheduleid(Long oScheduleid) 
    {
        this.oScheduleid = oScheduleid;
    }

    public Long getoScheduleid() 
    {
        return oScheduleid;
    }
    public void setoStatus(String oStatus) 
    {
        this.oStatus = oStatus;
    }

    public String getoStatus() 
    {
        return oStatus;
    }
    public void setoPipno(Long oPipno) 
    {
        this.oPipno = oPipno;
    }

    public Long getoPipno() 
    {
        return oPipno;
    }
    public void setoRegistered(String oRegistered) 
    {
        this.oRegistered = oRegistered;
    }

    public String getoRegistered() 
    {
        return oRegistered;
    }
    public void setoType(String oType) 
    {
        this.oType = oType;
    }

    public String getoType() 
    {
        return oType;
    }
    public void setoTime(String oTime) 
    {
        this.oTime = oTime;
    }

    public String getoTime() 
    {
        return oTime;
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
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oId", getoId())
            .append("oPid", getoPid())
            .append("oScheduleid", getoScheduleid())
            .append("oStatus", getoStatus())
            .append("oPipno", getoPipno())
            .append("oRegistered", getoRegistered())
            .append("oType", getoType())
            .append("oTime", getoTime())
            .append("creatBy", getCreatBy())
            .append("creatTime", getCreatTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("isDeleted", getIsDeleted())
                .append("hsPatient",getHsPatient())
            .toString();
    }
}
