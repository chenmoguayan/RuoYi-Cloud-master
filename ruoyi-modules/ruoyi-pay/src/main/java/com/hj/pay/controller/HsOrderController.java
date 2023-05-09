package com.hj.pay.controller;

import com.hj.pay.api.HsPatientClient;
import com.hj.pay.domain.HsOrder;
import com.hj.pay.domain.HsPrescription;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 患者库Controller
 * 
 * @author ruoyi
 * @date 2023-04-13
 */
@RestController
@RequestMapping("/pay/order")
public class HsOrderController extends BaseController
{
    @Autowired
    private HsPatientClient hsPatientClient;


    /**
     * 查询患者库列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody Long oid)
    {
        HsOrder hsOrder = new HsOrder();
        hsOrder.setoId(oid);
        System.out.println(hsOrder);
        TableDataInfo list = hsPatientClient.list(hsOrder);
        System.out.println(list.toString());
        return list;
    }


    @GetMapping("/queryByOid/{oId}")
    public TableDataInfo queryByOid(@PathVariable("oId") String oId){
        return hsPatientClient.queryByOid(oId);
    }


    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody HsPrescription hsPrescription){
        return hsPatientClient.edit(hsPrescription);
    }

}
