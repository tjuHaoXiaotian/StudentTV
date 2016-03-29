package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.common.BizCode;
import cn.edu.tju.scs.common.StateCode;
import cn.edu.tju.scs.domain.Comment;
import cn.edu.tju.scs.domain.LocalAuth;
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

@Controller(value = "userController")
@RequestMapping(value = "users")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    /**
     * 添加用户，用户注册
     * @param localAuth
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    StateCode addUser(@RequestBody LocalAuth localAuth) {
        System.out.println(localAuth);
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        userService.addUser(localAuth);
        return stateCode;
    }

    /**
     * 查看所有用户信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    StateCode getAllUser() {
        System.out.println("请求进来了--------");
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        stateCode.addData("users",userService.getAllUser());
        return stateCode;
    }

    /**
     * 查看 指定 id 用户信息
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody
    StateCode getAllUser(@PathVariable int id) {
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        stateCode.addData("user",userService.getUser(id));
        return stateCode;
    }

    /**
     * 添加一个收藏
     * @return
     */
    @RequestMapping(value = "/{userId}/collections",method = RequestMethod.POST)
    public @ResponseBody
    StateCode collect(@PathVariable int userId,@RequestParam(value = "videoId") int videoId ) {
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        stateCode.addData("collectionId",userService.collect(userId,videoId));
        return stateCode;
    }

    /**
     * 取消一个收藏
     * @return
     */
    @RequestMapping(value = "/{userId}/collections/{collectionId}",method = RequestMethod.DELETE)
    public @ResponseBody
    StateCode deCollect(@PathVariable int userId,@PathVariable int collectionId) {
        userService.deCollect(collectionId);
        return StateCode.buildCode(BizCode.SUCCESS);
    }


    /**
     * 查看所有收藏
     * @return
     */
    @RequestMapping(value = "/{userId}/collections",method = RequestMethod.GET)
    public @ResponseBody
    StateCode getAllCollection(@PathVariable int userId) {
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        stateCode.addData("collections",userService.getAllCollection(userId));
        return stateCode;
    }


    /**
     * 添加一个评论，可能有错
     * @return
     */
    @RequestMapping(value = "/{userId}/comments",method = RequestMethod.POST)
    public @ResponseBody
    StateCode comment(@RequestBody Comment comment,@PathVariable int userId ) {
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
    @RequestMapping(value = "/{userId}/comments/{commentId}",method = RequestMethod.DELETE)
    public @ResponseBody
    StateCode deComment(@PathVariable int userId,@PathVariable int commentId) {
        commentService.removeComment(commentId);
        return StateCode.buildCode(BizCode.SUCCESS);
    }

}
