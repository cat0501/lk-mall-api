export default {
  development: {
    baseUrl: '/api' // 测试接口域名
  },
  beta: {
    // baseUrl: '//backend-api-02.newbee.ltd/manage-api/v1' // 测试接口域名
    // baseUrl: '//127.0.0.1:28019/manage-api/v1' // 测试接口域名
    baseUrl: '//127.0.0.1:8001/manage-api/v1' // 测试接口域名
  },
  release: {
    // baseUrl: '//127.0.0.1:28019/manage-api/v1' // 正式接口域名
    baseUrl: '//127.0.0.1:8001/manage-api/v1' // 正式接口域名
  }
}