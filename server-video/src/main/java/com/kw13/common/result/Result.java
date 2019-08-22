package com.kw13.common.result;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -2463988658960057458L;
    protected String msgCode;
    protected String msgInfo;
    private boolean success = false;
    private T model;
    private long cost;
    //private GWInfo gwInfo;


    public Result(T model) {
        this.model = model;
    }

    public Result(boolean success, String msgCode, String msgInfo) {
        this.success = success;
        this.msgCode = msgCode;
        this.msgInfo = msgInfo;
    }

    public Result(boolean success, String msgCode, String msgInfo, T model) {
        this.success = success;
        this.msgCode = msgCode;
        this.msgInfo = msgInfo;
        this.model = model;
    }

    public Result() {

    }

    /**
     * @return the cost
     */
    public long getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(long cost) {
        this.cost = cost;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the model
     */
    public T getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(T model) {
        this.model = model;
    }

    /**
     * @return the msgCode
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * @param msgCode the msgCode to set
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * @return the msgInfo
     */
    public String getMsgInfo() {
        return msgInfo;
    }

    /**
     * @param msgInfo the msgInfo to set
     */
    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

}
