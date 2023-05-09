package com.hj.pay.controller;

import com.hj.pay.api.ProtectClient;
import com.hj.pay.domain.Protect;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hj
 * @data 2023/4/19 17:22
 */
@RestController
@RequestMapping("/pay/protect")
public class ProtectController {
    @Autowired
    private ProtectClient protectClient;

    @PostMapping("/list")
    public TableDataInfo list(Protect protect)
    {
        return protectClient.list(protect);
    }

    @GetMapping("/info/{proId}")
    public AjaxResult getInfo(@PathVariable("proId") Long proId){
        return AjaxResult.success(protectClient.getInfo(proId));
    }
}
