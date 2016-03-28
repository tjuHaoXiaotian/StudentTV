package cn.edu.tju.scs;

import cn.edu.tju.scs.util.CreatePhotos;
import cn.edu.tju.scs.util.VideoTran;
import org.junit.Test;

import java.io.File;

/**
 * Created by jack on 2016/3/17.
 */
public class MovieInfoTest {

    @Test
    public void testGetInfo(){
        File file = new File("D:\\2016Project\\JSP_Study\\StudentProject\\StudentTV\\src\\main\\webapp\\video\\《琅琊榜》MV-刘涛《红颜旧》_标清.flv");
        System.out.println("文件路径" +file.getAbsolutePath());
        System.out.println(CreatePhotos.getMovieInfo("D:\\2016Project\\JSP_Study\\StudentProject\\StudentTV\\src\\main\\webapp\\video\\《琅琊榜》MV-刘涛《红颜旧》_标清.flv"));

    }

    @Test
    public void testSystem(){
        System.out.println("===========os.name:"+System.getProperties().getProperty("os.name").toLowerCase());
        System.out.println("===========file.separator:"+System.getProperties().getProperty("file.separator"));
    }

    @Test
    public void testReplace(){
        System.out.println("http:localhost:8080/test/test/test".replace("/","\\"));
    }


    @Test
    public void Convert(){
        VideoTran.convert("G:\\Youku Files\\download\\《琅琊榜》MV-刘涛《红颜旧》_标清.flv","G:\\Youku Files\\download\\《琅琊榜》MV-刘涛《红颜旧》_标清.mp4");
    }
}
