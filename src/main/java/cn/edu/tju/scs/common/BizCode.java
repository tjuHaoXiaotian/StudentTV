package cn.edu.tju.scs.common;

/**
 * Created by catold on 15/12/19.
 */
public enum BizCode {
    // 全局错误码
    SUCCESS(200, "操作成功"),
    FAIL(101,"服务器操作失败"),
    DATE_FAIL(102,"时间转换格式失败"),
    WITHOUT_AUTHORIZATION(103, "未登录"),

    // user 错误码
    WRONGPERMISSION(403,"没有操作权限"),
    WRONGUSER(401,"用户验证错误"),
    NOTFOUND(404,"目标资源（对象）不存在")


    // 待议...根据后台逻辑分类
    ;

    private int state;
    private String message;

    BizCode(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
