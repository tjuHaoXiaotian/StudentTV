package cn.edu.tju.scs.domain.base;



import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by jack on 2015/1/1.
 */
// 实现了 Serializable 接口，以便JVM 可以序列化 PO 实例
public class BaseDomain implements Serializable {

    // 统一的 toString（）方法
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
