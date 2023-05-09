package com.hj.pay.api;

import com.hj.pay.domain.MedicineInfo;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hj
 * @data 2023/4/19 17:28
 */
@Repository
@FeignClient(contextId = "medicineClient",value = "ruoyi-medicine",url="192.168.31.179:9300")
public interface MedicineClient {
    @GetMapping("/medicine/medicine/list")
    TableDataInfo list(MedicineInfo medicineInfo);

    @GetMapping("/medicine/medicine/getOne/{id}")
    AjaxResult getInfo(@PathVariable("id") String id);

    @PutMapping("/medicine/medicine")
    AjaxResult edit(@RequestBody MedicineInfo medicineInfo);
}
