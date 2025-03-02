package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.testdemo.model.User;
import com.example.utils.ApiResponse;
import com.example.utils.HttpStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class TestSuitUtils {

    private static String beforeUrl = "http://localhost:8080/api/users/";
    private static String paramStr = "?Content-Type=application/json";
    private static String requestUrl;
    private User user;

    public static String request(Map<String,Object> param){
        String response;
        Map<String, String> headers = new HashMap<>();
        switch (param.get("requestMethod").toString()){
            case "GET" :
                // 示例：发送 GET 请求
                if (param.get("id")!=""){
                    requestUrl = beforeUrl + param.get("id") + paramStr;
                }else {
                    requestUrl = beforeUrl + paramStr;
                }
                headers.put("Authorization", "Bearer your-token");
                response = HttpUtils.get(requestUrl, headers, String.class);
                System.out.println("GET Response: " + response);
                break;
            case "POST":
                // 示例：发送 POST 请求
                headers.put("Authorization", "Bearer your-token");
                response = HttpUtils.post(beforeUrl + paramStr, param.get("user"), headers, String.class);
                System.out.println("POST Response: " + response);
                break;
            case "PUT":
                // 示例：发送 PUT 请求
                headers.put("Authorization", "Bearer your-token");
                response = HttpUtils.put(beforeUrl + paramStr, param.get("user"), headers, String.class);
                System.out.println("PUT Response: " + response);
                break;
            case "DELETE":
                // 示例：发送 DELETE 请求
                headers.put("Authorization", "Bearer your-token");
                response = HttpUtils.delete(beforeUrl + param.get("id") + paramStr, headers, String.class);
                System.out.println("DELETE Response: " + response);
                break;
            default:
                response = "请输入请求方式";
                System.out.println(response);
                break;
        }
        return response;
    }

    public ApiResponse requestResponse(String response){
        // 将字符串解析为JSON对象
        JSONObject json = JSON.parseObject(response);
        return buildApiResponse(json.getInteger("code"),HttpStatus.getHttpStatusByCode(json.getInteger("code")),
                json.getString("message"),json.getString("data"));
    }

    private ApiResponse buildApiResponse(int code, HttpStatus status, String message, String data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setStatus(status);
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setFormattedTime(getDateTimeStamp());
        return apiResponse;
    }

    public String getDateTimeStamp(){
        long timestamp = Instant.now().toEpochMilli(); // 毫秒级时间戳
        System.out.println("当前时间戳（毫秒）: " + timestamp);

        // 将时间戳转换为 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = dateTime.format(formatter);

        // 输出格式化后的时间
        System.out.println("格式化后的时间: " + formattedTime);
        return formattedTime;
    }
}
