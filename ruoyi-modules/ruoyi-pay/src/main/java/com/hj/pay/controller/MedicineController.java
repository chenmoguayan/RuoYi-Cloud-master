package com.hj.pay.controller;

import com.hj.pay.api.MedicineClient;
import com.hj.pay.domain.MedicineInfo;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hj
 * @data 2023/4/19 17:32
 */
@RestController
@RequestMapping("/pay/medicine")
public class MedicineController {
    @Autowired
    private MedicineClient medicineClient;

    @GetMapping("/list")
    public TableDataInfo list(MedicineInfo medicineInfo){
        return medicineClient.list(medicineInfo);
    }

    @GetMapping("/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id){
        return medicineClient.getInfo(id);
    }

    @PutMapping
    public AjaxResult edit(@RequestBody MedicineInfo medicineInfo){
        return medicineClient.edit(medicineInfo);
    }
}
