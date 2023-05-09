import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listRefund(query) {
  return request({
    url: '/system/refund/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getRefund(roId) {
  return request({
    url: '/system/refund/' + roId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addRefund(data) {
  return request({
    url: '/system/refund',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateRefund(data) {
  return request({
    url: '/system/refund',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delRefund(roId) {
  return request({
    url: '/system/refund/' + roId,
    method: 'delete'
  })
}
