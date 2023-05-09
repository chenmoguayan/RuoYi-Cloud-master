import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listChargeable(query) {
  return request({
    url: '/system/chargeable/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getChargeable(coId) {
  return request({
    url: '/system/chargeable/' + coId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addChargeable(data) {
  return request({
    url: '/system/chargeable',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateChargeable(data) {
  return request({
    url: '/system/chargeable',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delChargeable(coId) {
  return request({
    url: '/system/chargeable/' + coId,
    method: 'delete'
  })
}
