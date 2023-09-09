import com.heima.freemarker.freemarkerApplication;
import com.heima.freemarker.pojo.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @Date: 2023/7/12 0:30
 * @author: Qeem
 */
@SpringBootTest(classes = freemarkerApplication.class)
@RunWith(SpringRunner.class)
public class freemarkerTest {
    @Resource
    private Configuration configuration;

    @Test
    public void test() throws IOException, TemplateException {
        Template template = configuration.getTemplate("02-list.ftl");

        /*
        * 合成方法
        *
        * 两个参数:
        * 第一个参数:模型数据
        * 第二个参数: 输出流
        * */
        template.process(getDate(),new FileWriter("d:/study/heima-leadnews/list.html"));
    }
    private Map getDate(){
        Map<String,Object> map = new HashMap<>();

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

        map.put("stus",studentList);

        //map集合
        HashMap<String, Student> studentMap = new HashMap<>();
        studentMap.put("stu1",stu1);
        studentMap.put("stu2",stu2);

        map.put("stuMap",studentMap);

        //日期
        map.put("today",new Date());

        //长数值
        map.put("point",654646464564564L);

        return map;
    }
}
