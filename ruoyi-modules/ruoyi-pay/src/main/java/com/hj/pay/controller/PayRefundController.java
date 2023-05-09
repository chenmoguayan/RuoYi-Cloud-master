package com.hj.pay.controller;

import com.alibaba.fastjson.JSON;
import com.hj.pay.domain.PayRefund;
import com.hj.pay.service.IPayRefundService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 退费订单信息Controller
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@RestController
@RequestMapping("/pay/refund")
public class PayRefundController extends BaseController
{
    @Autowired
    private IPayRefundService payRefundService;

    /**
     * 查询退费订单信息列表
     */
    @RequiresPermissions("refund:pay:list")
    @GetMapping("/list")
    public TableDataInfo list(PayRefund payRefund)
    {
        startPage();
        List<PayRefund> list = payRefundService.selectPayRefundList(payRefund);
        return getDataTable(list);
    }

    /**
     * 导出退费订单信息列表
     */
    @RequiresPermissions("refund:pay:export")
    @Log(title = "退费订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayRefund payRefund)
    {
        List<PayRefund> list = payRefundService.selectPayRefundList(payRefund);
        ExcelUtil<PayRefund> util = new ExcelUtil<PayRefund>(PayRefund.class);
        util.exportExcel(response, list, "退费订单信息数据");
    }

    /**
     * 获取退费订单信息详细信息
     */
    @RequiresPermissions("refund:pay:query")
    @GetMapping(value = "/{roId}")
    public AjaxResult getInfo(@PathVariable("roId") Long roId)
    {
        return success(payRefundService.selectPayRefundByRoId(roId));
    }

    /**
     * 新增退费订单信息
     */
    @RequiresPermissions("refund:pay:add")
    @Log(title = "退费订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public String add(@RequestBody PayRefund payRefund)
    {
        PayRefund payRefund1 = payRefundService.insertPayRefund(payRefund);
        return JSON.toJSONString(payRefund1);
    }

    /**
     * 修改退费订单信息
     */
    @RequiresPermissions("refund:pay:edit")
    @Log(title = "退费订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayRefund payRefund)
    {
        return toAjax(payRefundService.updatePayRefund(payRefund));
    }

    /**
     * 删除退费订单信息
     */
    @RequiresPermissions("refund:pay:remove")
    @Log(title = "退费订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roIds}")
    public AjaxResult remove(@PathVariable Long[] roIds)
    {
        return toAjax(payRefundService.deletePayRefundByRoIds(roIds));
    }
}
