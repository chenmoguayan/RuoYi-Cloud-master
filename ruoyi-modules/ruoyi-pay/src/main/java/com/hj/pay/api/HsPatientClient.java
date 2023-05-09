package com.hj.pay.api;

import com.hj.pay.domain.HsOrder;
import com.hj.pay.domain.HsPrescription;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * @author hj
 * @data 2023/4/17 16:39
 */
@Repository
@FeignClient(contextId = "hsPatientClient",value = "ruoyi-visit",url = "192.168.31.92:8001")
public interface HsPatientClient {
    @PostMapping("/visit/order/list?pageNum=1&pageSize=10")
    TableDataInfo list( HsOrder hsOrder);
    @GetMapping("/visit/prescription/queryByOid/{oId}")
    TableDataInfo queryByOid(@PathVariable("oId") String oId);

    @PutMapping("/visit/prescription")
    AjaxResult edit(@RequestBody HsPrescription hsPrescription);
}
