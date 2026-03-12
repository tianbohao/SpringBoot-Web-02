package org.example.controller;

import cn.hutool.core.io.IoUtil;
import org.example.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//用户信息controller


public class UserController {

    @RequestMapping("/list")
    public List<User> list() throws Exception {

        //1.加载并读取user.txt用户数据，引入工具类hutool,在pom.xml配置

        //不推荐,传的是绝对路径
        //FileInputStream in = new FileInputStream(new File("D:\\代码\\java\\Project\\maven-demo\\springboot-web-02\\src\\main\\resources\\user.txt"));

        //我用了ptg,如何获取用户数据

        //怎么获取
        //把获取用户数据的代码重写

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());

        //2.解析用户信息，封装为User对象 -> list集合

        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");

            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            return new User(id, username, password, name, age, updateTime);

        }).toList();


        //3.给前端返回数据,json格式

        return userList;
    }
}
