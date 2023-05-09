import request from '@/utils/request'

// 查询退费订单信息列表
export function listPay(query) {
  return request({
    url: '/pay/refund/list',
    method: 'get',
    params: query
  })
}

// 查询退费订单信息详细
export function getPay(roId) {
  return request({
    url: '/pay/refund/' + roId,
    method: 'get'
  })
}

// 新增退费订单信息
export function addPay(data) {
  return request({
    url: '/pay/refund',
    method: 'post',
    data: data
  })
}

// 修改退费订单信息
export function updatePay(data) {
  return request({
    url: '/pay/refund',
    method: 'put',
    data: data
  })
}

// 删除退费订单信息
export function delPay(roId) {
  return request({
    url: '/pay/refund/' + roId,
    method: 'delete'
  })
}
