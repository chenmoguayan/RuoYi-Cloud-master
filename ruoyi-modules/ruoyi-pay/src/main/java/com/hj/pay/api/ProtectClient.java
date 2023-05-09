package com.hj.pay.api;

import com.hj.pay.domain.Protect;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author hj
 * @data 2023/4/19 17:15
 */
@Repository
@FeignClient(contextId = "protectClient",value = "ruoyi-examine", url = "192.168.31.183:9210")
public interface ProtectClient {
    /**
     * 查询检查项目列表
     * @param protect
     * @return
     */
    @PostMapping(value = "/examine/protect/list?pageNum=1&pageSize=10")
    TableDataInfo list(Protect protect);

    /**
     * 获取检查项目详细信息，id
     * @param proId
     * @return
     */
    @GetMapping(value = "/examine/protect/{proId}")
    AjaxResult getInfo(@PathVariable("proId") Long proId);

}
