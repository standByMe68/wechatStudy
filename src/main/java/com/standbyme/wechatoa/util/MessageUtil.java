package com.standbyme.wechatoa.util;


import com.standbyme.wechatoa.entity.TextEntity;

public class MessageUtil {

    public static final String MSGTYPE_EVENT = "event";
    public static final String MESSAGE_SUBSCIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCIBE = "unsubscribe";
    public static final String MESSAGE_TEXT = "text";

    /**
     * 组装文本消息
     */
    public static String textMsg(String toUserName,String fromUserName,String content){
        TextEntity text = new TextEntity();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MESSAGE_TEXT);
        text.setCreateTime(System.currentTimeMillis());
        text.setContent(content);
        return XmlUtil.textMsgToxml(text);
    }

    /**
     * 响应订阅事件--回复文本消息
     */
    public static String subscribeForText(String toUserName,String fromUserName){
        return textMsg(toUserName, fromUserName, "欢迎关注，这里是正在开发的公众号");
    }

    /**
     * 响应取消订阅事件
     */
    public static String unsubscribe(String toUserName,String fromUserName){
        //TODO 可以进行取关后的其他后续业务处理
        System.out.println("用户："+ fromUserName +"取消关注~");
        return "";
    }

}
