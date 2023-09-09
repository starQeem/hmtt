package com.heima.freemarker.controller;

import com.heima.freemarker.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2023/7/10 21:42
 * @author: Qeem
 */
@Controller
public class HelloController {
    @GetMapping("/basic")
    public String test(Model model){
        //纯文本
        model.addAttribute("name","freemarker");
        //对象
        Student student = new Student();
        student.setAge(18);
        student.setName("星星");
        student.setMoney(300f);
        student.setBirthday(new Date());
        model.addAttribute("stu",student);
        return "01-basic";
    }
    @GetMapping("/list")
    public String list(Model model){
        //list集合
        Student stu1 = new Student();
        stu1.setMoney(100f);
        stu1.setAge(18);
        stu1.setName("嘎嘎");
        stu1.setBirthday(new Date());

        Student stu2 = new Student();
        stu2.setMoney(200f);
        stu2.setBirthday(new Date());
        stu2.setAge(20);
        stu2.setName("咕咕");

        List<Student> studentList = new ArrayList<>();
        studentList.add(stu1);
        studentList.add(stu2);

        model.addAttribute("stus",studentList);

        //map集合
        HashMap<String, Student> studentMap = new HashMap<>();
        studentMap.put("stu1",stu1);
        studentMap.put("stu2",stu2);

        model.addAttribute("stuMap",studentMap);

        return "02-list";
    }
}
