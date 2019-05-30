package com.unicom.blog.utils;



import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 接口的统一返回对象
 * @author 张凯南
 *
 */
public class Result<T> {

    /**
     * 返回码
     */
    @JSONField(name = "RESP_CODE", ordinal = 1)
    private String respCode;

    /**
     * 返回描述
     */
    @JSONField(name = "RESP_DESC", ordinal = 2)
    private String respDesc;

    /**
     * 返回数据
     */
    @JSONField(name = "RESP_DATA", ordinal = 3)
    private T respData;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public T getRespData() {
        return respData;
    }

    public void setRespData(T respData) {
        this.respData = respData;
    }

    /**
     * 示例方法
     * @param args
     */
    public static void main(String[] args) {
        Result<List<String>> r = new Result<>();
        r.setRespCode("0000");
        r.setRespDesc("成功");
        List<String> respData = new ArrayList<>();
        respData.add("数据1");
        respData.add("数据2");
        r.setRespData(respData);
        System.out.println(JSON.toJSONString(r));
        // {"RESP_CODE":"0000","RESP_DESC":"成功","RESP_DATA":["数据1","数据2"]}
    }

}
