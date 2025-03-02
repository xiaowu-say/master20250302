package com.example.testdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.testdemo.model.User;
import com.example.testdemo.service.UserService;
import com.example.utils.ApiResponse;
import com.example.utils.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // GET请求：查询所有用户
    @GetMapping
    public ApiResponse getAllUsers() {
        List<User> userList;
        try {
            ApiResponse apiResponse = new ApiResponse();
            userList = userService.getAllUsers();
            if (!userList.isEmpty()) {
                apiResponse = buildApiResponse(200,HttpStatus.getHttpStatusByCode(200),
                        HttpStatus.getMessageByCode(200),JSON.toJSONString(userList));
            } else {
                apiResponse.setCode(404);
                apiResponse.setStatus(HttpStatus.getHttpStatusByCode(404));
                apiResponse.setMessage("查询失败，数据库为空表");
            }
            return apiResponse;
        } catch (Exception e) {
            logger.error("获取用户列表失败", e); // 记录异常日志
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(500);
            apiResponse.setStatus(HttpStatus.getHttpStatusByCode(500));
            apiResponse.setMessage("服务器内部错误：" + e.getMessage());
            return apiResponse;
        }
    }

    // GET请求：通过ID查询用户
    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {
        ResponseEntity<User> responseEntity;
        try {
            responseEntity = userService.getUserById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
            return ResponseEntity(responseEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse ResponseEntity(ResponseEntity<User> responseEntity) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setCode(responseEntity.getStatusCodeValue());
            apiResponse.setStatus(HttpStatus.getHttpStatusByCode(responseEntity.getStatusCodeValue()));
            if (responseEntity.getStatusCodeValue()==200){
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(responseEntity.getBody());
                apiResponse.setData(jsonString);
            }else {
                apiResponse.setData("");
            }
            apiResponse.setMessage("status:"+apiResponse.getStatus()+" code:"+apiResponse.getCode()+" data:"+
                    apiResponse.getData());
            apiResponse.setFormattedTime(getDateTimeStamp());
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return apiResponse;
    }

    // POST请求：创建新用户
    @PostMapping
    public ApiResponse createUser(@RequestBody User user) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User resultUser = userService.saveUser(user);
            if (resultUser.getName().equals(user.getName())){
                apiResponse = buildApiResponse(200,HttpStatus.getHttpStatusByCode(200),
                        HttpStatus.getMessageByCode(200),JSON.toJSONString(resultUser));
            }else {
                System.out.println("新增失败，请及时排查");
            }
            return apiResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // PUT请求：更新用户信息
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE请求：删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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

    private ApiResponse buildApiResponse(int code, HttpStatus status, String message, String data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setStatus(status);
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setFormattedTime(getDateTimeStamp());
        return apiResponse;
    }

}
