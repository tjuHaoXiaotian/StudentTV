package cn.edu.tju.scs.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目返回码
 * Created by catold on 15/12/19.
 */
public class StateCode{
    private int state;
    private String message;
    private Map<String, Object> data;

    public static StateCode buildCode(BizCode bizCode){
        return new StateCode(bizCode);
    }

    private StateCode(BizCode bizCode) {
        this.state = bizCode.getState();
        this.message = bizCode.getMessage();
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void addData(String key, Object o) {
        if(data == null){
            data = new HashMap();
        }
        data.put(key, o);
    }
}
