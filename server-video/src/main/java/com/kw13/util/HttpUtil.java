package com.kw13.util;

import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Desc http请求工具类
 * @Author yejx
 * @Date 2019/3/11
 */
@Component
public class HttpUtil {

    @Autowired
    private ResponseHandler<String> stringBodyRspHandler;
    @Autowired
    private ResponseHandler<byte[]> byteBodyRspHandler;

    /**
     * 发出post请求 返回类型String
     *
     * @param url     url
     * @param reqBody 参数json
     * @return String返回结果
     * @throws Exception 异常
     */
    public String postForString(String url, String reqBody) throws Exception {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            return httpclient.execute(getHttpPost(url, reqBody), stringBodyRspHandler);
        }
    }

    /**
     * 发出post请求 返回类型byte[]
     *
     * @param url     url
     * @param reqBody 参数json
     * @return byte[]返回结果
     * @throws Exception 异常
     */
    public byte[] bytePostForString(String url, String reqBody) throws Exception {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            return httpclient.execute(getHttpPost(url, reqBody), byteBodyRspHandler);
        }
    }

    private HttpPost getHttpPost(String url, String reqBody) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(reqBody, "utf-8");
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json;charset=utf-8");
        httpPost.setEntity(entity);
        return httpPost;
    }

    /**
     * 发出get请求
     *
     * @param url   请求url
     * @param param url参数
     * @return 请求结果
     * @throws IOException 异常
     */
    public String getForString(String url, String param) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet;
            if (param == null) {
                httpGet = new HttpGet(url);
            } else {
                httpGet = new HttpGet(url + "?" + param);
            }
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                // 请求发送成功，并得到响应
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 读取服务器返回过来的json字符串数据
                    return EntityUtils.toString(response.getEntity());
                }
            }
        }
        return null;
    }
    
    /**
     * 发出get请求
     *
     * @param url   请求url
     * @param param url参数
     * @param headers 请求头
     * @return 请求结果
     * @throws IOException 异常
     */
    public String getForString(String url, Map<String,String> params, Map<String,String> headers) throws Exception {
    	try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
       
            URIBuilder uriBuilder = new URIBuilder(url);
            for(String key:params.keySet()) {
            	uriBuilder.addParameter(key, params.get(key));
            }
          
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            for(String key:headers.keySet()) {
            	httpGet.addHeader(key, headers.get(key));
            }
            
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                // 请求发送成功，并得到响应
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 读取服务器返回过来的json字符串数据
                    return EntityUtils.toString(response.getEntity());
                }
            }
        }
        return null;
    }
}
