package com.example.testdemo.repository;

import com.example.testdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 可以在这里添加自定义查询方法
}
