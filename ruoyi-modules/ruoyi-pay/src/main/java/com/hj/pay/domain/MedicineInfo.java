package com.hj.pay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 药品管理对象 medicine_info
 * 
 * @author wxx
 * @date 2023-04-13
 */
public class MedicineInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String medicineName;

    /** 药品编码 */
    @Excel(name = "药品编码")
    private String medicineCode;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String factoryName;

    /** 药品类型 */
    @Excel(name = "药品类型")
    private Integer medicineType;

    /** 处方类型 */
    @Excel(name = "处方类型")
    private Integer prescriptionType;

    /** 处方价格 */
    @Excel(name = "处方价格")
    private Long price;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 库存量 */
    @Excel(name = "库存量")
    private Integer stock;

    /** 预警值 */
    @Excel(name = "预警值")
    private Integer earlyWarning;

    /** 换算量 */
    @Excel(name = "换算量")
    private String matrixing;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keywords;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 删除标记 */
    private Integer isDelete;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMedicineName(String medicineName) 
    {
        this.medicineName = medicineName;
    }

    public String getMedicineName() 
    {
        return medicineName;
    }
    public void setMedicineCode(String medicineCode)
    {
        this.medicineCode = medicineCode;
    }

    public String getMedicineCode()
    {
        return medicineCode;
    }
    public void setFactoryName(String factoryName) 
    {
        this.factoryName = factoryName;
    }

    public String getFactoryName() 
    {
        return factoryName;
    }
    public void setMedicineType(Integer medicineType) 
    {
        this.medicineType = medicineType;
    }

    public Integer getMedicineType() 
    {
        return medicineType;
    }
    public void setPrescriptionType(Integer prescriptionType) 
    {
        this.prescriptionType = prescriptionType;
    }

    public Integer getPrescriptionType() 
    {
        return prescriptionType;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setStock(Integer stock) 
    {
        this.stock = stock;
    }

    public Integer getStock() 
    {
        return stock;
    }
    public void setEarlyWarning(Integer earlyWarning) 
    {
        this.earlyWarning = earlyWarning;
    }

    public Integer getEarlyWarning() 
    {
        return earlyWarning;
    }
    public void setMatrixing(String matrixing) 
    {
        this.matrixing = matrixing;
    }

    public String getMatrixing() 
    {
        return matrixing;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("medicineName", getMedicineName())
            .append("medicine Code", getMedicineCode())
            .append("factoryName", getFactoryName())
            .append("medicineType", getMedicineType())
            .append("prescriptionType", getPrescriptionType())
            .append("price", getPrice())
            .append("unit", getUnit())
            .append("stock", getStock())
            .append("earlyWarning", getEarlyWarning())
            .append("matrixing", getMatrixing())
            .append("keywords", getKeywords())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
