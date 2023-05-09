import request from '@/utils/request'

export default {
  // 根据输入的挂号单号查询挂号单信息
  getOrderList(oid){
    return request({
      url: `/pay/order/list`,
      method: 'POST',
      data: oid
    })
  },
  queryByOid(oId){
    return request({
      url: `/pay/order/queryByOid/`+oId,
      method: 'GET'
    })
  },
  getProtect(proId){
    return request({
      url: `/pay/protect/info/`+proId,
      method: 'GET'
    })
  },
  getMedicine(id){
    return request({
      url: `/pay/medicine/info/`+id,
      method: 'GET'
    })
  },
  edit(obj){
    return request({
      url: `/pay/order/edit`,
      method: 'PUT',
      data: obj
    })
  },
  updateDrug(obj){
    return request({
      url: `/pay/medicine`,
      method: 'PUT',
      data: obj
    })
  }

}
