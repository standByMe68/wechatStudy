package com.standbyme.wechatoa.controller;



import com.standbyme.wechatoa.util.Constants;
import com.standbyme.wechatoa.util.MessageUtil;
import com.standbyme.wechatoa.util.WxUtil;
import com.standbyme.wechatoa.util.XmlUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class FollowController {

    private static final Logger logger = LoggerFactory.getLogger(FollowController.class);

    public String authenticationServer(HttpServletRequest request, HttpServletResponse response){

        logger.info("Prepare for server validation");

        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter(Constants.SIGNATURE);
        // 时间戳
        String timestamp = request.getParameter(Constants.TIMESTAMP);
        // 随机数
        String nonce = request.getParameter(Constants.NONCE);
        // 随机字符串
        String echostr = request.getParameter(Constants.ECHOSTR);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (WxUtil.checkSignature(signature, timestamp, nonce)) {
                logger.info("微信加密签名:" + signature + ";时间戳:" + timestamp + ";随机数:" + nonce);

            }
            out.print(echostr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
        return echostr;
    }

    @RequestMapping("/wx/follow")
    public String followtest(HttpServletRequest req, HttpServletResponse resp)throws IOException{

        logger.info("Wechat platform request processing");

        // 随机字符串
        String echostr = req.getParameter(Constants.ECHOSTR);

        if (echostr == null || "".equals(echostr)){
            logger.info("Processing message events");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            String message = "success";
            try {
                //把微信返回的xml信息转义成map
                Map<String, String> map = XmlUtil.xmlToMap(req);
                String fromUserName = map.get("FromUserName");
                String toUserName = map.get("ToUserName");
                logger.info("用户的openID"+fromUserName);
                String msgType = map.get("MsgType");
                String content = map.get("Content");

                String eventType = map.get("Event");
                if(MessageUtil.MSGTYPE_EVENT.equals(msgType)){
                    if(MessageUtil.MESSAGE_SUBSCIBE.equals(eventType)){
                        //获取对应的数据存储数据库

                        //生成关注消息
                        message = MessageUtil.subscribeForText(toUserName, fromUserName);
                    }else if(MessageUtil.MESSAGE_UNSUBSCIBE.equals(eventType)){
                        message = MessageUtil.unsubscribe(toUserName, fromUserName);
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }finally{
                out.println(message);
                if(out!=null){
                    out.close();
                }
            }
            return null;
        }else {
            return authenticationServer(req,resp);
        }
    }
}
