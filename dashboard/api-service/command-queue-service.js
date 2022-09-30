import API from "./api";
import {ERROR_NOT_FOUND} from "../commons/constants";

export const createQueueAPI = async (name) => {
  try {
    const {data} = await API.post(
      '/api/queues',
      JSON.stringify({ name })
    );
    return data;
  } catch {
    console.log('error when request zsmq server')
  }
}

export const deleteQueueAPI = async (name) => {
  try {
    const {data} = await API.delete(
      `/api/queues/${name}`,
    );
    return data;
  } catch (e) {
    const {status} = e.response;
    if (status === 404) {
      return ERROR_NOT_FOUND;
    }
  }
}