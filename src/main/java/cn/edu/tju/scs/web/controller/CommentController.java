package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.common.BizCode;
import cn.edu.tju.scs.common.StateCode;
import cn.edu.tju.scs.domain.Comment;
import cn.edu.tju.scs.domain.User;
import cn.edu.tju.scs.service.CommentService;
import cn.edu.tju.scs.service.UserService;
import cn.edu.tju.scs.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 * Created by jack on 2016/3/28.
 */

@Controller(value = "commentController")
@RequestMapping(value = {"comments"})
public class CommentController extends BaseController{


    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;
    /**
     * 添加一个评论，可能有错
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    StateCode comment(@RequestBody Comment comment,@RequestParam(value = "userId") int userId ) {
        StateCode stateCode;
        User user = userService.getUser(userId);
        if(user != null) {
            stateCode =  StateCode.buildCode(BizCode.SUCCESS);
            comment.setUser(user);
            commentService.add(comment);
        }else{
            stateCode = StateCode.buildCode(BizCode.FAIL);
        }
        return stateCode;
    }


    /**
     * 删除一个评论
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public @ResponseBody
    StateCode deComment(@PathVariable int id) {
        commentService.removeComment(id);
        return StateCode.buildCode(BizCode.SUCCESS);
    }
}
