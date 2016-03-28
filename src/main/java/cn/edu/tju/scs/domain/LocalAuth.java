package cn.edu.tju.scs.domain;

/**
 * 本地账户管理
 * Created by jack on 2016/1/10.
 */
public class LocalAuth extends BaseDomain{
    // 用户id
    private Long userId;
    // 密码
    private String password;
    // 账号
    private String account;

    // 用户 state 0 代表正常，1 代表被注销
    private int state;

    // 多对一映射
    private User user;

    public LocalAuth(){

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
