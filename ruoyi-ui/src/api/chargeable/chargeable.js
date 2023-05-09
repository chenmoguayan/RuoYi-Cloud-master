import request from '@/utils/request'

export default {
  // //创建支付订单，
  // payCharge(searchObj){
  //   return request({
  //     url: `/pay/chargeable/payCharge`,
  //     method: 'POST',
  //     params: searchObj
  //   })
  // }
}
// 查询收费订单列表
export function listPay(query) {
  return request({
    url: '/pay/chargeable/list',
    method: 'get',
    params: query
  })
}

// 查询收费订单详细
export function getPay(coId) {
  return request({
    url: '/pay/chargeable/' + coId,
    method: 'get'
  })
}

// 新增收费订单
export function addPay(data) {
  return request({
    url: '/pay/chargeable',
    method: 'post',
    data: data,
    dataType: 'json'
  })
}

// 修改收费订单
export function updatePay(data) {
  return request({
    url: '/pay/chargeable',
    method: 'put',
    data: data
  })
}

// 删除收费订单
export function delPay(coId) {
  return request({
    url: '/pay/chargeable/' + coId,
    method: 'delete'
  })
}
