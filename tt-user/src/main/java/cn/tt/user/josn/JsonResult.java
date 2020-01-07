package cn.tt.user.josn;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 为了注解ResponseBody很好的返回数据
 * @梁梓云
 */
public class JsonResult <T> {
    //T为占位符  具体范围为java数据类型的一种
    @JsonInclude(value= JsonInclude.Include.ALWAYS)//默认序列化
    private Integer state;
    @JsonInclude(value= JsonInclude.Include.NON_NULL)//值为null不序列化
    private String message;
    @JsonInclude(value= JsonInclude.Include.ALWAYS)
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, String message) {
        super();
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, T data) {
        super();
        this.state = state;
        this.data = data;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
