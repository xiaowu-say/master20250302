package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Executable;

/**
 * Author:zhanghongwu
 * Date:2025/2/27
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        Object parameter = new Object();
        String name = "张洪武";
        int modifiers = 5;
        Executable executable = null;
        final int index = 0;
        return name + "\n" + modifiers + "\n" + "executable is null! \n" + index;
    }
}
