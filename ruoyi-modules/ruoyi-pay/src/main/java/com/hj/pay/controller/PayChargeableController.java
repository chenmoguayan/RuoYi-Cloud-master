package com.hj.pay.controller;

import com.alibaba.fastjson.JSON;
import com.hj.pay.domain.PayChargeable;
import com.hj.pay.service.IPayChargeableService;
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
 * 收费订单Controller
 * 
 * @author ruoyi
 * &#064;date  2023-04-13
 */
@RestController
@RequestMapping("/pay/chargeable")
public class PayChargeableController extends BaseController
{
    @Autowired
    private IPayChargeableService payChargeableService;

    /**
     * 查询收费订单列表
     */
    @RequiresPermissions("chargeable:pay:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody PayChargeable payChargeable)
    {
        startPage();
        System.out.println(payChargeable);
        List<PayChargeable> list = payChargeableService.selectPayChargeableList(payChargeable);
        return getDataTable(list);
    }

    /**
     * 导出收费订单列表
     */
    @RequiresPermissions("chargeable:pay:export")
    @Log(title = "收费订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PayChargeable payChargeable)
    {
        List<PayChargeable> list = payChargeableService.selectPayChargeableList(payChargeable);
        ExcelUtil<PayChargeable> util = new ExcelUtil<PayChargeable>(PayChargeable.class);
        util.exportExcel(response, list, "收费订单数据");
    }

    /**
     * 获取收费订单详细信息
     */
    @RequiresPermissions("chargeable:pay:query")
    @GetMapping(value = "/{coId}")
    public AjaxResult getInfo(@PathVariable("coId") Long coId)
    {
        System.out.println(coId);
        PayChargeable payChargeable = payChargeableService.selectPayChargeableByCoId(coId);
        System.out.println(payChargeable);
        return success(payChargeable);
    }

    /**
     * 新增收费订单
     */
    @RequiresPermissions("chargeable:pay:add")
    @Log(title = "收费订单", businessType = BusinessType.INSERT)
    @PostMapping
    public String add(@RequestBody PayChargeable payChargeable)
    {
        System.out.println(payChargeable);
        PayChargeable payChargeable1 = payChargeableService.insertPayChargeable(payChargeable);
        System.out.println(JSON.toJSONString(payChargeable1));
        return JSON.toJSONString(payChargeable1);
    }

    /**
     * 修改收费订单
     */
    @RequiresPermissions("chargeable:pay:edit")
    @Log(title = "收费订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayChargeable payChargeable)
    {
        return toAjax(payChargeableService.updatePayChargeable(payChargeable));
    }

    /**
     * 删除收费订单
     */
    @RequiresPermissions("chargeable:pay:remove")
    @Log(title = "收费订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{coIds}")
    public AjaxResult remove(@PathVariable Long[] coIds)
    {
        return toAjax(payChargeableService.deletePayChargeableByCoIds(coIds));
    }
    int i = 0;
    /**
     * 支付，
     */
    @PostMapping("/payCharge")
    public AjaxResult payCharge(@RequestBody Integer payChargeable){
        i++;
        System.out.println("第"+i+"次挂号，挂号费:"+payChargeable);
        return toAjax(true);
    }

    @PutMapping("/payRefund")
    public AjaxResult payRefund(Integer payRefund){
        return toAjax(true);
    }
}
