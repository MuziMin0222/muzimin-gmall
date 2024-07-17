import http from '@/utils/httpRequest.js'

export function policy(file) {
  return new Promise((resolve, reject) => {
    http({
      url: http.adornUrl(`/thirdpart/minio/policy/${file.name}`),
      method: 'get',
      params: http.adornParams({})
    }).then(({data}) => {
      resolve(data);
    })
  });
}
