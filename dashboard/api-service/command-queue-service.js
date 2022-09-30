import axios from "axios";
import API from "./api";

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
    const {data} = await API.post(
      '/api/queues',
      JSON.stringify({ name })
    );
    return data;
  } catch {
    console.log('error when request zsmq server')
  }
}