package com.example.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * 发送 GET 请求
     *
     * @param url 请求的 URL
     * @param headers 请求头（可选）
     * @param responseType 响应的数据类型
     * @param <T> 响应的泛型类型
     * @return 响应对象
     */
    public static <T> T get(String url, Map<String, String> headers, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
        return responseEntity.getBody();
    }

    /**
     * 发送 POST 请求
     *
     * @param url 请求的 URL
     * @param body 请求体
     * @param headers 请求头（可选）
     * @param responseType 响应的数据类型
     * @param <T> 响应的泛型类型
     * @return 响应对象
     */
    public static <T> T post(String url, Object body, Map<String, String> headers, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
        return responseEntity.getBody();
    }

    /**
     * 发送 PUT 请求
     *
     * @param url 请求的 URL
     * @param body 请求体
     * @param headers 请求头（可选）
     * @param responseType 响应的数据类型
     * @param <T> 响应的泛型类型
     * @return 响应对象
     */
    public static <T> T put(String url, Object body, Map<String, String> headers, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType);
        return responseEntity.getBody();
    }

    /**
     * 发送 DELETE 请求
     *
     * @param url 请求的 URL
     * @param headers 请求头（可选）
     * @param responseType 响应的数据类型
     * @param <T> 响应的泛型类型
     * @return 响应对象
     */
    public static <T> T delete(String url, Map<String, String> headers, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType);
        return responseEntity.getBody();
    }
}
