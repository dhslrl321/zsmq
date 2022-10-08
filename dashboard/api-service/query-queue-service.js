import API from "./api";

export const getQueueDescribeAPI = async () => {
  try {
    const { data } = await API.get('/api/queues');
    return data;
  } catch {
    console.log('error when request zsmq server')
  }
}