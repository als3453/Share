import request from '@/utils/request'

// 查询评价信息表列表
export function listEvalute(query) {
  return request({
    url: '/system/evalute/list',
    method: 'get',
    params: query
  })
}

// 查询评价信息表详细
export function getEvalute(id) {
  return request({
    url: '/system/evalute/' + id,
    method: 'get'
  })
}

// 新增评价信息表
export function addEvalute(data) {
  return request({
    url: '/system/evalute',
    method: 'post',
    data: data
  })
}

// 修改评价信息表
export function updateEvalute(data) {
  return request({
    url: '/system/evalute',
    method: 'put',
    data: data
  })
}

// 删除评价信息表
export function delEvalute(id) {
  return request({
    url: '/system/evalute/' + id,
    method: 'delete'
  })
}
