package com.cn.hnust.util;

import java.io.Serializable;

public class JsonResult implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 671162142354945640L;
    /**
     * 操作结果（成功或失败）
     */
    boolean isOk = true;
    /**
     * 操作提示信息
     */
    String message;
    /**
     * 备注
     */
    String comment;
    /**
     * 数据
     */
    Object data;

    Long total;

    public boolean isOk() {
        return isOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


    public static JsonResult success(Object data, long total) {
        JsonResult json = new JsonResult();
        json.isOk = true;
        json.data = data;
        json.total = total;
        return json;
    }

    public static JsonResult success(Object data) {
        JsonResult json = new JsonResult();
        json.isOk = true;
        json.data = data;
        return json;
    }

    public static JsonResult error(String msg) {
        JsonResult json = new JsonResult();
        json.isOk = false;
        json.message = msg;
        return json;
    }


}

