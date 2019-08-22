package com.kw13.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Desc http请求工具类
 * @Author yejx
 * @Date 2019/3/11
 */
@Configuration
public class HttpHandler {

    @Bean("stringBodyRspHandler")
    public ResponseHandler<String> getStringBodyRspHandler() {
        return new ResponseHandler<String>() {
            @Override
            public String handleResponse(
                    final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
    }

    @Bean("byteBodyRspHandler")
    public ResponseHandler<byte[]> getByteBodyRspHandler() {
        return new ResponseHandler<byte[]>() {
            @Override
            public byte[] handleResponse(
                    final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toByteArray(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
    }
}
