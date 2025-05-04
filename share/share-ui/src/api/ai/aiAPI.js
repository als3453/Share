import axios from 'axios';

const API_URL = 'https://api.coze.cn/v3/chat';
const API_KEY = 'pat_y9Io6I1NZbHEigiXQil3MfzJ041DlTbYvKKvvalqS7YJKDfHi2bqN62gxMAb8dFV'; // 请替换为实际的 API 密钥

export async function callAiApi(additionalMessages) {
  try {
    const response = await axios.post(API_URL, {
      bot_id: 7481225533465428007,
      user_id: userId,
      stream: false,
      auto_save_history: true,
      additional_messages: '你好'
    }, {
      headers: {
        'Authorization': `Bearer ${API_KEY}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('调用 AI API 时出错:', error);
    throw error;
  }
}
