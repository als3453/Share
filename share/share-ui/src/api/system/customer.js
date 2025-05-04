import request from '@/utils/request'

// 查询用户信息表列表
export function listCustomer(query) {
  return request({
    url: '/system/customer/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息表详细
export function getCustomer(cusId) {
  return request({
    url: '/system/customer/' + cusId,
    method: 'get'
  })
}

// 新增用户信息表
export function addCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'post',
    data: data
  })
}

// 修改用户信息表
export function updateCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'put',
    data: data
  })
}

// 删除用户信息表
export function delCustomer(cusId) {
  return request({
    url: '/system/customer/' + cusId,
    method: 'delete'
  })
}
