package com.kw13.common.result;

import java.io.Serializable;

public class ChatResult  implements Serializable {
    public static final int TYPE_NO_ANSWER = 0;
    public static final int TYPE_FROM_TL = 1;
    public static final int TYPE_HERB = 2;
    public static final int TYPE_DOCTOR = 3;
    public static final int TYPE_ILLNESS = 4;
    public static final int TYPE_FROM_QA = 5;
    private int type;//0 : no answer 1:from tl  2: herbs 3 doctor 4 fangzi  5 illness
    private String code;
    private String text;
    private String url;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
