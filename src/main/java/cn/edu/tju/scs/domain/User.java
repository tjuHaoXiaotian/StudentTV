package cn.edu.tju.scs.domain;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * User domain
 * Created by jack on 2016/1/1.
 */
public class User extends BaseDomain{

    // user id
    private Long userId;
    // 账号
    private String account;
    // 用户姓名
    private String username;
    // 上次登录 IP
    private String lastIp;
    // 上次登录时间
    private Timestamp lastVisit;


    // 用户状态 0 正常， 1 已注销
    private int State;

    public User(){

    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Timestamp getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Timestamp lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!account.equals(user.account)) return false;
        if (!userId.equals(user.userId)) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + account.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

}
