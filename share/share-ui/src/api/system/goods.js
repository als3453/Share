import request from '@/utils/request'

// 查询商品信息表列表
export function listGoods(query) {
  return request({
    url: '/system/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息表详细
export function getGoods(id) {
  return request({
    url: '/system/goods/' + id,
    method: 'get'
  })
}

// 新增商品信息表
export function addGoods(data) {
  return request({
    url: '/system/goods',
    method: 'post',
    data: data
  })
}

// 修改商品信息表
export function updateGoods(data) {
  return request({
    url: '/system/goods',
    method: 'put',
    data: data
  })
}

// 删除商品信息表
export function delGoods(id) {
  return request({
    url: '/system/goods/' + id,
    method: 'delete'
  })
}
