package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.common.BizCode;
import cn.edu.tju.scs.common.StateCode;
import cn.edu.tju.scs.domain.Admin;
import cn.edu.tju.scs.service.AdminService;
import cn.edu.tju.scs.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AdminController
 * Created by jack on 2016/3/16.
 */
@Controller(value = "adminController")
@RequestMapping(value = {"admins"})
public class AdminController extends BaseController{

    @Autowired
    AdminService adminService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public @ResponseBody
    StateCode login(@RequestBody Admin admin){
        if(adminService.hasMatchAdmin(admin.getAdminName(),admin.getPassword())){
            return  StateCode.buildCode(BizCode.SUCCESS);
        }else {
            return  StateCode.buildCode(BizCode.WRONGUSER);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    StateCode add(@RequestBody Admin admin){
        adminService.saveAdmin(admin);
        return  StateCode.buildCode(BizCode.SUCCESS);
    }

}
