package cn.edu.tju.scs.service;

import cn.edu.tju.scs.domain.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Video Service
 * Created by jack on 2016/3/16.
 */
public interface VideoService {

    public List<Video> getAll();

    public Video getVideo(int id);

    public void updateVideo(Video video);

    public void save(Video video);

    public boolean uploadImage( MultipartFile file,String targetDir,String baseUrl);

    public String[] uploadVideo( MultipartFile file,String targetDir,String baseUrl);

    public String[] getVideoPath(int id);

    public boolean deleteVideo(int id);

    public boolean praiseVideo(int id);
}
