package com.kw13.common.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = -2463988658057458L;
    protected String msg;
    private boolean success = true;
    private T model;
    private Map<String, Object> data = new HashMap<>();

    public void put(String key, Object obj) {
        this.data.put(key, obj);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
