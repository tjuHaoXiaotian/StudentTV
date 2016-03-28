package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.common.BizCode;
import cn.edu.tju.scs.common.Constants;
import cn.edu.tju.scs.common.StateCode;
import cn.edu.tju.scs.domain.Video;
import cn.edu.tju.scs.service.VideoService;
import cn.edu.tju.scs.util.GetIP;
import cn.edu.tju.scs.util.MyDate;
import cn.edu.tju.scs.web.controller.base.BaseController;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Video Controller
 * Created by jack on 2016/3/16.
 */
@Controller
@RequestMapping(value = "videos")
public class VideoController extends BaseController{

    @Autowired
    VideoService videoService;


    /**
     * 访问所有 video
     * @return
     */
    @RequestMapping( method = RequestMethod.GET)
    public @ResponseBody
    StateCode getAll() {
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        stateCode.addData("videos", videoService.getAll());
        return stateCode;
    }

    /**
     * 访问一个 video
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody
    StateCode getVideo(@PathVariable int id ) {
        StateCode stateCode =  StateCode.buildCode(BizCode.SUCCESS);
        stateCode.addData("videos", videoService.getVideo(id));
        return stateCode;
    }

    /**
     * 更新一个 video
     * @return
     */
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public @ResponseBody
    StateCode updateVideoByPut(@RequestBody  Video video,@PathVariable int id){
        {
            // 是不是有必要
            video.setVideoId(id);

        }
        videoService.updateVideo(video);
        return  StateCode.buildCode(BizCode.SUCCESS);
    }

    /**
     * 更新一个 video
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.POST)
    public @ResponseBody
    StateCode updateVideoByPost(@RequestBody Video video,@PathVariable int id) {
        {
            // 是不是有必要
            video.setVideoId(id);

        }
        videoService.updateVideo(video);
        return  StateCode.buildCode(BizCode.SUCCESS);
    }


    /**
     * 赞
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}/praise",method= RequestMethod.GET)
    public @ResponseBody
    StateCode praiseVideoByGet(@PathVariable int id) {
        if(videoService.praiseVideo(id))
            return  StateCode.buildCode(BizCode.SUCCESS);
        else {
            StateCode stateCode =  StateCode.buildCode(BizCode.NOTFOUND);
            stateCode.addData("error","赞的视频文件不存在或已被移除");
            return stateCode;
        }

    }




    /**
     * 删除一个 video
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    StateCode delete(@PathVariable("id") int id) throws IOException {
        if (videoService.deleteVideo(id)) {
            return  StateCode.buildCode(BizCode.SUCCESS);
        }else {
            return  StateCode.buildCode(BizCode.FAIL);
        }
    }

    /**
     *  浏览器端的下载文件？？？？？？？？？？？？ 是否多余
     * @param id
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/download", method = RequestMethod.DELETE)
    public @ResponseBody
    StateCode download(@PathVariable("id") int id,HttpServletResponse response) throws IOException {
        String [] result = videoService.getVideoPath(id);
        if(result == null){
            StateCode stateCode  = StateCode.buildCode(BizCode.FAIL);
            stateCode.addData("error","下载的资源文件不存在");
            return stateCode;
        }else{
            try{
//            // 将正确能识别的中文文件名转成ISO8859-1 编码才可以正确下载
//            String fileNameEncode = new String(fileName.getBytes(),"ISO8859-1");

                response.setContentType("application/x-msdownload");
                FileInputStream fileInputStream = new FileInputStream(new File(result[0]));

                response.setHeader("Content-Disposition","attachment;filename=" + result[1]);
                OutputStream outputStream = response.getOutputStream();

                IOUtils.copy(fileInputStream, outputStream);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return StateCode.buildCode(BizCode.SUCCESS);
        }

    }

    /**
     * 上传文件
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody
    StateCode upload(MultipartHttpServletRequest request) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println("正在上传0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String getDateString = sdf.format(new Date());
        Boolean uploadResult = handler(request, result);
        if ( uploadResult) {
            return  StateCode.buildCode(BizCode.SUCCESS);
        }else {
            return  StateCode.buildCode(BizCode.FAIL);
        }
    }

    /**
     * 处理上传文件
     * @param request
     * @param result
     * @return
     * @throws IOException
     */
    public Boolean handler(MultipartHttpServletRequest request,  Map<String, Object> result) throws IOException{
        System.out.println("正在上传1");
        MultipartFile file = request.getFile("file");
        if (file == null || file.getSize() < 0) {// step-2 判断file
            System.out.println("size 为 0");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String getDateString = sdf.format(new Date());

        String uniqueDir = String.valueOf(System.nanoTime());
        // 上传视频保存位置
        String targetDir;
        String baseUrl;
        if(Constants.SYSTEM_TYPE.contains("windows")){  // 是 windows
           targetDir  = "D:\\upload\\video\\" + getDateString +"\\"+ uniqueDir+"\\";
            baseUrl =Constants.WINDOWS_HTTP+"upload/video/" + getDateString + "/" + uniqueDir;
        }else{   // 是 linux
           targetDir = "/alidata/upload/video/" + getDateString +"/"+ uniqueDir;
            baseUrl =Constants.LINUX_HTTP+"upload/video/" + getDateString + "/" + uniqueDir;
        }

        System.out.println(targetDir);


        // 获取 服务器及app路径 http://localhost:8080/chapter1/
        // 测试的时候改为 192.168.191.1
        String realIp = GetIP.getRealIp();
        System.out.println("realIp is :" + realIp);

        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress();//获得本机IP
        System.out.println("realIp is :" +ip);



        String uploadFileName = file.getOriginalFilename();
        String results[] = videoService.uploadVideo(file,targetDir,baseUrl);
        
        if( results!= null){
            // 业务操作
            Video video = new Video();
            video.setName(uploadFileName);
            video.setDescription("视频文件：" + uploadFileName);
            video.setPerformer("xx 明星");
            video.setAlbum("xx 电视剧");
            video.setPath(results[0]);
            video.setImagePath(results[1]);
            String current = MyDate.getCurrentTime();
            video.setCreatetime(Timestamp.valueOf(current));
            video.setUpdatetime(Timestamp.valueOf(current));
            video.setVisitedTimes(0);
            video.setSize(file.getSize());
            video.setDuration(results[2]);
            video.setTargetDir(targetDir);
            videoService.save(video);
            return true;
        }else{
            return false;
        }
    }







}
