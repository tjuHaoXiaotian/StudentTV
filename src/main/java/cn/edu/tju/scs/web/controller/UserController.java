package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User Controller
 * Created by jack on 2016/3/28.
 */

@Controller(value = "userController")
@RequestMapping(value = {"user"})
public class UserController extends BaseController{

}
