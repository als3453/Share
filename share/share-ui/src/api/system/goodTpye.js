import request from '@/utils/request'

// 查询商品种类表列表
export function listGoodTpye(query) {
  return request({
    url: '/system/goodTpye/list',
    method: 'get',
    params: query
  })
}

// 查询商品种类表详细
export function getGoodTpye(id) {
  return request({
    url: '/system/goodTpye/' + id,
    method: 'get'
  })
}

// 新增商品种类表
export function addGoodTpye(data) {
  return request({
    url: '/system/goodTpye',
    method: 'post',
    data: data
  })
}

// 修改商品种类表
export function updateGoodTpye(data) {
  return request({
    url: '/system/goodTpye',
    method: 'put',
    data: data
  })
}

// 删除商品种类表
export function delGoodTpye(id) {
  return request({
    url: '/system/goodTpye/' + id,
    method: 'delete'
  })
}
