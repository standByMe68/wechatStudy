package com.standbyme.wechatoa.entity;

public class TextEntity extends BaseEntity{

    private String Content;
    private String MsgId;
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public String getMsgId() {
        return MsgId;
    }
    public void setMsgId(String msgId) {
        MsgId = msgId;
    }


}
