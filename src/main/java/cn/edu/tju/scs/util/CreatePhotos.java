package cn.edu.tju.scs.util;

import cn.edu.tju.scs.common.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by jack on 2015/11/28.
 */
public class CreatePhotos {

    /**
     *
     * @param video_path
     * @param image_path
     * @return
     */
    public static boolean processImg(String video_path,String image_path) {
        File file = new File(video_path);
        if (!file.exists()) {
            System.err.println("路径[" + video_path + "]对应的视频文件不存在!");
            return false;
        }
        List<String> commands = new java.util.ArrayList<String>();
//        commands.add("/usr/bin/ffmpeg");
        if(Constants.SYSTEM_TYPE.contains("windows")){
            // 是 windows
            commands.add("E:\\ffmpeg\\bin\\ffmpeg.exe");
        }else{   // 是 linux
            commands.add("/usr/bin/ffmpeg");
        }
        commands.add("-ss");
        commands.add("8");//这个参数是设置截取视频多少秒时的画面
        commands.add("-i");
        commands.add(video_path);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-t");
        commands.add("0.001");
        commands.add("-s");
        commands.add("352*240");
        commands.add(image_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            builder.start();
            System.out.println("截取成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过ffmpeg获取影片的信息
     * @param movieUrl
     * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">@return</a>
     */
    public static String getMovieInfo(String movieUrl){
        try {
            List<String> command=new java.util.ArrayList<String>();
            if(Constants.SYSTEM_TYPE.contains("windows")){
                // 是 windows
                command.add("E:\\ffmpeg\\bin\\ffmpeg.exe");
            }else{   // 是 linux
                command.add("/usr/bin/ffmpeg");
            }
            command.add("-i");
            command.add(movieUrl);
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(command);
            builder.redirectErrorStream(true);
            Process p= builder.start();
            BufferedReader buf = null;
            String line = null;
            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

//            StringBuffer sb= new StringBuffer();
            while ((line = buf.readLine()) != null) {

                if(line.contains("Duration")){
                    System.out.println(line);
                    String duration = line.toString().split(",")[0];
                    System.out.println(duration);
                    System.out.println(duration.substring(duration.indexOf("Duration") + "Duration: ".length()));

                     return duration.substring(duration.indexOf("Duration") + "Duration: ".length());
                }
//                sb.append(line);
//                continue;
            }
            int ret = p.waitFor();
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }



}
