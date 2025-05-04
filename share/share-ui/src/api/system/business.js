import request from '@/utils/request'

// 查询商家管理列表
export function listBusiness(query) {
  return request({
    url: '/system/business/list',
    method: 'get',
    params: query
  })
}

// 查询商家管理详细
export function getBusiness(busId) {
  return request({
    url: '/system/business/' + busId,
    method: 'get'
  })
}

// 新增商家管理
export function addBusiness(data) {
  return request({
    url: '/system/business',
    method: 'post',
    data: data
  })
}

// 修改商家管理
export function updateBusiness(data) {
  return request({
    url: '/system/business',
    method: 'put',
    data: data
  })
}

// 删除商家管理
export function delBusiness(busId) {
  return request({
    url: '/system/business/' + busId,
    method: 'delete'
  })
}
