package com.cyq7on.template.bean;

import java.io.Serializable;

/**
 * @Description:
 * @author: cyq7on
 * @date: 2016/8/2 18:02
 * @version: V1.0
 */
public class BaseBean implements Serializable {
    //0成功，1失败
    public String RES_CODE;
    //错误信息
    public String RES_DESC;
    //为空，数据转换时使用
    public String RES_DATA;
}
