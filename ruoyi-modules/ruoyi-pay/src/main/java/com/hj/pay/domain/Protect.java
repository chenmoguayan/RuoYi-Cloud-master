package com.hj.pay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 检查项目对象 protect
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
public class Protect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long prold;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String inspectionItem;

    /** 单价 */
    @Excel(name = "单价")
    private Long unitPrice;

    /** 成本 */
    @Excel(name = "成本")
    private Long costPrice;

    /** 类别 */
    @Excel(name = "类别")
    private String category;

    public void setProld(Long prold) 
    {
        this.prold = prold;
    }

    public Long getProld() 
    {
        return prold;
    }
    public void setInspectionItem(String inspectionItem) 
    {
        this.inspectionItem = inspectionItem;
    }

    public String getInspectionItem() 
    {
        return inspectionItem;
    }
    public void setUnitPrice(Long unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public Long getUnitPrice() 
    {
        return unitPrice;
    }
    public void setCostPrice(Long costPrice) 
    {
        this.costPrice = costPrice;
    }

    public Long getCostPrice() 
    {
        return costPrice;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("prold", getProld())
            .append("inspectionItem", getInspectionItem())
            .append("unitPrice", getUnitPrice())
            .append("costPrice", getCostPrice())
            .append("category", getCategory())
            .toString();
    }
}
