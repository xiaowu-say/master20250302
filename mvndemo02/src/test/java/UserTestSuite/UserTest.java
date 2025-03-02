package UserTestSuite;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestSuitUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: 张洪武
 * Date: 2025/02/28
 */
public class UserTest {

    @Test
    public void selectUserByIdTest(){
        long id = 1;
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("id",1);
            param.put("requestMethod","GET");
            String response = TestSuitUtils.request(param);

            /* 验证返回参数为非空 */
            Assert.assertNotNull(response);

            /* 验证调用接口是否成功，验证code和status */
            JSONObject json = JSON.parseObject(response);
            int code = json.getInteger("code");
            Assert.assertEquals(code ,200);
            String status =json.getString("status");
            Assert.assertEquals(status, "OK");
            json.getString("message");
            json.getString("data");
            System.out.println("[selectUserByIdTest] GET \n"
                    +JSON.parseObject(response));
        }catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
