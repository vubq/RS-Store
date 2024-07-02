import request from '@/utils/request'

const ENV = '/category'

export const getListOfCategoriesByCriteria = (params) =>
  request({
    url: ENV + '/get-list-of-categories-by-criteria',
    method: 'get',
    params
  })

export const getCategoryById = (id) =>
  request({
    url: ENV + '/' + id,
    method: 'get'
  })

export const createCategory = (data) =>
  request({
    url: ENV,
    method: 'post',
    data
  })

export const updateCategory = (data) =>
  request({
    url: ENV,
    method: 'put',
    data
  })

export const getAllCategory = () =>
  request({
    url: '/category',
    method: 'get'
  })
