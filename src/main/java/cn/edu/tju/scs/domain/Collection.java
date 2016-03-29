package cn.edu.tju.scs.domain;

import cn.edu.tju.scs.domain.base.BaseDomain;

/**
 * 用户收藏
 * Created by jack on 2016/3/28.
 */
public class Collection extends BaseDomain{
    private int collectionId;

    private int userId;

    private int videoId;

    public Collection(){

    }

    public Collection(int userId,int videoId){
        this.userId = userId;
        this.videoId = videoId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
