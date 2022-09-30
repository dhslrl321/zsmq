import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8291',
  headers: {
    'Content-Type': 'application/json'
  }
});

export default API;