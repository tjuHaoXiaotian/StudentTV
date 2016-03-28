package cn.edu.tju.scs.domain;

import java.sql.Timestamp;

/**
 * 评论
 * Created by jack on 2016/3/28.
 */
public class Comment extends BaseDomain{

    private int commentId;

    // 评论内容
    private String content;

    // 用户创建时间
    private Timestamp createtime;

    // 用户
    private User user;

    private int ref_video_Id;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRef_video_Id() {
        return ref_video_Id;
    }

    public void setRef_video_Id(int ref_video_Id) {
        this.ref_video_Id = ref_video_Id;
    }
}
