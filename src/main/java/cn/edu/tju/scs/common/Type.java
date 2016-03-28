package cn.edu.tju.scs.common;

/**
 * 视频种类
 * Created by jack on 2016/3/28.
 */
public enum Type {

    TVPLAY(0,"电视剧"),
    MOVIE(1,"电影"),
    ENTERTAINMENT(2,"娱乐"),
    cartoon(3,"动漫"),
    MUSIC(4,"音乐"),
    EDUCATION(5,"教育"),
    Science(6,"科技"),
    original(7,"原创"),
    LIFE(8,"生活"),
    STV(9,"学视")

    ;

    // 种类编号
    private int type;

    // 说明
    private String message;

    private Type(int type,String message){
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
