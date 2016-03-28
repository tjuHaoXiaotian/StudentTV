package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.common.Constants;
import cn.edu.tju.scs.dao.VideoDao;
import cn.edu.tju.scs.domain.Video;
import cn.edu.tju.scs.service.VideoService;
import cn.edu.tju.scs.util.CreatePhotos;
import cn.edu.tju.scs.util.FileUtil;
import cn.edu.tju.scs.util.VideoTran;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jack on 2015/10/22.
 */

/**
 * 事物注解方式: @Transactional
 * 当标于类前时，标示类中所有方法都进行事物处理，以下代码在service层进行事务处理（给Service层配置事务是比较好的方式，
 * 因为一个Service层方法操作可以关联到多个DAO的操作。在Service层执行这些Dao操作，多DAO操作有失败全部回滚，成功则全部提交。）
 */
@Service(value = "videoService")
public class VideoServiceImpl implements VideoService{



    @Autowired
    VideoDao videoDao;

    @Override
    public List<Video> getAll() {
        return videoDao.getAll();
    }

    @Override
    public Video getVideo(int id) {
        return videoDao.get(id);
    }

    @Override
    public void updateVideo(Video video) {
        videoDao.update(video);
    }


    @Override
    public void save(Video video) {
        videoDao.save(video);
    }

    //单文件上传，图片
    public  boolean uploadImage( MultipartFile file,String targetDir,String baseUrl){
        try{

            String uploadFileName = file.getOriginalFilename();

            InputStream isRef = file.getInputStream();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
            String getDateString = sdf.format(new Date());
            String fileName = getDateString + "_" + System.nanoTime() + "_" + uploadFileName;

            // filePath 此处是图片存储路径，浏览器可以直接访问的地址
            String filePath =baseUrl + "/" + fileName;
            System.out.println("图片路径"+filePath);


            File targetFile = new File(targetDir,fileName);// 目录结构： upload/image/2015_10_23/xxx.jpg
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream fosRef = new FileOutputStream(targetFile);

            IOUtils.copy(isRef, fosRef);

            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }



    //单文件上传，视频
    public  String[] uploadVideo( MultipartFile file,String targetDir,String baseUrl){
        try{
            // 图片保存目标地址 即 文件保存地址
            String targetImageDir = targetDir;

            System.out.println("传进来的" + targetDir);
            String uploadFileName = file.getOriginalFilename();
            uploadFileName = uploadFileName.replaceAll("\\s+","");

            System.out.println("处理后的file name is："+ uploadFileName);

            InputStream isRef = file.getInputStream();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
            String getDateString = sdf.format(new Date());
//            String fileName = getDateString + "_" + System.nanoTime() + "_" + uploadFileName;

            // 保存在硬盘上的文件名儿（视频）
            String fileName = getDateString + "_" + uploadFileName;

            File targetFile = new File(targetDir,fileName);// 目录结构： upload/image/2015_10_23/xxx.jpg

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }



            if (file.getSize() > 0) {
                //写入目标文件
//                FileOutputStream fosRef = new FileOutputStream(targetFile);
//                IOUtils.copy(isRef, fosRef);
                file.transferTo(targetFile);//写入目标文件
            }else{
                return null;
            }

//            /**
//             *  视频转换操作
//             */
//            String targetPath = targetFile.getAbsolutePath();
//            int targetIndex = targetPath.lastIndexOf(".");
//            VideoTran.convert(targetPath,targetPath.substring(0,targetIndex)+".mp4");

            /**
             *  截取图片操作
             */
            String imagePath = null;
            int index = uploadFileName.lastIndexOf(".");
            String imageName = "1.jpg";
            if(index > -1){
                imageName = uploadFileName.substring(0,index)+".jpg";
            }
            // 保存在硬盘上的文件名儿（图片）
            String targetImageName = getDateString  + "_" + imageName;
            // 存放目标文件的地址
            File targetImageDirectory = new File(targetImageDir);
            if(!targetImageDirectory.exists() && !targetImageDirectory.isDirectory()){
                targetImageDirectory.mkdirs();
            }

            if(Constants.SYSTEM_TYPE.contains("windows")){
                // 是 windows
                if(CreatePhotos.processImg(targetFile.getAbsolutePath(), targetImageDir + "\\" + targetImageName)){
                    imagePath = baseUrl +'/'+ targetImageName;
                }
            }else{   // 是 linux
                if(CreatePhotos.processImg(targetFile.getAbsolutePath(), targetImageDir + "/" + targetImageName)){
                    imagePath = baseUrl +'/'+ targetImageName;
                }
            }


           String duration =  CreatePhotos.getMovieInfo(targetFile.getAbsolutePath());
            System.out.println("文件路径：" + targetFile.getAbsolutePath());
            System.out.println("duration: " + duration);


            // filePath 此处是图片存储路径，+主机名+端口号+应用名，可以直接访问，浏览器可以直接访问的地址
//            String filePath =baseUrl + "/" + fileName.substring(0, fileName.lastIndexOf("."))+".mp4";
            String filePath =baseUrl + "/" + fileName;
            return new String[] {filePath,imagePath,duration};
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String[] getVideoPath(int id) {
        Video targetVideo = videoDao.get(id);
        String targetDir =targetVideo.getTargetDir();
        String path = targetVideo.getPath();
        String targetName = "";
        if(Constants.SYSTEM_TYPE.contains("windows")){
            targetName = path.substring(path.lastIndexOf('\\'));
        }else{
            targetName = path.substring(path.lastIndexOf('/'));
        }
        return new String []{targetDir+targetName,targetName};
    }

    @Override
    public boolean deleteVideo(int id) {
        Video video = videoDao.get(id);
        if(FileUtil.deleteDirectory(video.getTargetDir())){
            videoDao.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean praiseVideo(int id) {
        Video video =  videoDao.get(id);
        if(video != null){
            video.setPraise(video.getPraise() + 1);
            return true;
        }else{
            return false;
        }
    }


}