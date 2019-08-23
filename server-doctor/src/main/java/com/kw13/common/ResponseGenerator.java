package com.kw13.common;

/**
 * Created by cheng on 2019/8/20.
 */
public class ResponseGenerator {

    public static BaseResponse genSuccessResult(Object data) {
        BaseResponse result = new BaseResponse();
        result.setResult(true);
        result.setCode("000000");
        result.setMsg("");
        result.setData(data);
        return result;
    }

    public static BaseResponse genFailResult(String message, String code) {
        BaseResponse result = new BaseResponse();
        result.setResult(false);
        result.setCode(code);
        result.setMsg(message);
        result.setData(null);
        return result;
    }
}
