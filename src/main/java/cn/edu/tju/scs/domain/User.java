package cn.edu.tju.scs.domain;

import cn.edu.tju.scs.domain.base.BaseDomain;

import java.sql.Timestamp;

/**
 * User domain
 * Created by jack on 2016/1/1.
 */
public class User extends BaseDomain {

    // user id
    private int userId;
    // 账号
    private String account;

    // 用户简介
    private String aboutMe;

    // 用户头像地址
    private String  avatarPath;

    // 上次登录 IP
    private String lastIp;

    // 用户创建时间
    private Timestamp createtime;
    // 更改时间
    private Timestamp updatetime;


    public User(){

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
