package cn.edu.tju.scs.service;

/**
 *
 * Created by jack on 2016/3/29.
 */

import cn.edu.tju.scs.domain.LocalAuth;
import cn.edu.tju.scs.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Spring 声明式事务管理第二种：基于AspectJ的xml配置方式
 * @author jack
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestUser {


    @Resource(name="userService")
    private UserService userService;

    @Test
    public void testAddUser(){
        LocalAuth localAuth = new LocalAuth();
        localAuth.setAccount("wutong");
        localAuth.setPassword("123");
        localAuth.setState(0);
        User user = new User();
        user.setAccount(localAuth.getAccount());
        user.setAboutMe("夕阳无限好只是，近黄昏");
        user.setAvatarPath("http://localhost:8080/upload/video/2016_03_28/1923614186601135/2016_03_28_11_59_01_何以爱情－《何以笙箫默》主题曲钢琴版_高清.jpg");


        localAuth.setUser(user);
        userService.addUser(localAuth);
    }
}
