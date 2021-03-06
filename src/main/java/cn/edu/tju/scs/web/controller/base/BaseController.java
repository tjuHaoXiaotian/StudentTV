package cn.edu.tju.scs.web.controller.base;


import cn.edu.tju.scs.common.BizCode;
import cn.edu.tju.scs.common.StateCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BaseController 异常处理的基类
 * Created by jack on 2016/1/5.
 */
public class BaseController {
    @ExceptionHandler
    public @ResponseBody StateCode handleException(Exception ex, HttpServletRequest request,HttpServletResponse response) throws IOException {

        StateCode stateCode =  StateCode.buildCode(BizCode.FAIL);
        stateCode.addData("error",ex.getMessage());

//        // 根据不同的错误返回不同的状态码
//        if(ex instanceof ArithmeticException){
//            System.out.println("抛出异常： 数学运算异常 ---------------------------");
//            return new ModelAndView("error1",model);
//        }else if(ex instanceof RuntimeException){
//            System.out.println("抛出异常： 运行时异常 ---------------------------");
//            return new ModelAndView("error1",model);
//        }else{
//            System.out.println("抛出异常： 其他异常 ---------------------------");
//            return new ModelAndView("error1",model);
//        }
        return stateCode;


    }
}
