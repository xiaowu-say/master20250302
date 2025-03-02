package com.example.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private HttpStatus status; // 状态：SUCCESS 或 FAILED
    private int code;      // 状态码：200 表示成功，其他表示失败
    private String message; // 提示信息
    private String data;
    private String formattedTime; // 请求处理的时间戳
}
