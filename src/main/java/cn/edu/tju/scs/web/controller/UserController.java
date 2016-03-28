package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.common.BizCode;
import cn.edu.tju.scs.common.StateCode;
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
@RequestMapping(value = {"users"})
public class UserController extends BaseController{

    @Autowired
    UserService userService;
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
    @RequestMapping(value = "/{userId}/collections/{collectionId}",method = RequestMethod.GET)
    public @ResponseBody
    StateCode deCollect(@PathVariable int userId,@PathVariable int collectionId) {
        userService.deCollect(collectionId);
        return StateCode.buildCode(BizCode.SUCCESS);
    }


    /**
     * 添加一个收藏
     * @return
     */
    @RequestMapping(value = "/{userId}/collections",method = RequestMethod.GET)
    public @ResponseBody
    StateCode getAllCollection(@PathVariable int userId) {
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        stateCode.addData("collections",userService.getAllCollection(userId));
        return stateCode;
    }


}
