package com.hj.pay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 患者库对象 hs_patient
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
public class HsPatient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long pId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String pName;

    /** 电话 */
    @Excel(name = "电话")
    private String pPhone;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String pIdcard;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pBirth;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long pAge;

    /** 性别 */
    @Excel(name = "性别")
    private String pSex;

    /** 地址 */
    @Excel(name = "地址")
    private String pAddress;

    /** 过敏史 */
    @Excel(name = "过敏史")
    private String pAllergic;

    /** 信息状态 */
    @Excel(name = "信息状态")
    private String pInfo;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 逻辑删除 */
    private Long isDeleted;

    public void setpId(Long pId) 
    {
        this.pId = pId;
    }

    public Long getpId() 
    {
        return pId;
    }
    public void setpName(String pName) 
    {
        this.pName = pName;
    }

    public String getpName() 
    {
        return pName;
    }
    public void setpPhone(String pPhone) 
    {
        this.pPhone = pPhone;
    }

    public String getpPhone() 
    {
        return pPhone;
    }
    public void setpIdcard(String pIdcard) 
    {
        this.pIdcard = pIdcard;
    }

    public String getpIdcard() 
    {
        return pIdcard;
    }
    public void setpBirth(Date pBirth) 
    {
        this.pBirth = pBirth;
    }

    public Date getpBirth() 
    {
        return pBirth;
    }
    public void setpAge(Long pAge) 
    {
        this.pAge = pAge;
    }

    public Long getpAge() 
    {
        return pAge;
    }
    public void setpSex(String pSex) 
    {
        this.pSex = pSex;
    }

    public String getpSex() 
    {
        return pSex;
    }
    public void setpAddress(String pAddress) 
    {
        this.pAddress = pAddress;
    }

    public String getpAddress() 
    {
        return pAddress;
    }
    public void setpAllergic(String pAllergic) 
    {
        this.pAllergic = pAllergic;
    }

    public String getpAllergic() 
    {
        return pAllergic;
    }
    public void setpInfo(String pInfo) 
    {
        this.pInfo = pInfo;
    }

    public String getpInfo() 
    {
        return pInfo;
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
            .append("pId", getpId())
            .append("pName", getpName())
            .append("pPhone", getpPhone())
            .append("pIdcard", getpIdcard())
            .append("pBirth", getpBirth())
            .append("pAge", getpAge())
            .append("pSex", getpSex())
            .append("pAddress", getpAddress())
            .append("pAllergic", getpAllergic())
            .append("pInfo", getpInfo())
            .append("creatBy", getCreatBy())
            .append("creatTime", getCreatTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
