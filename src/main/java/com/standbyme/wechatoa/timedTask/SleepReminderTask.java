package com.standbyme.wechatoa.timedTask;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author jcf
 * 用于定时发送公众消息提醒早睡
 */
public class SleepReminderTask {

    private final static Logger logger = LoggerFactory.getLogger(SleepReminderTask.class);

    @Scheduled(cron = "0 0 22 * * ? ")
    public void sleepReminder(){

        logger.info("Start early warning");

        // TODO: 2020/11/7 获取access_token凭证


        // TODO: 2020/11/7 获取对应的关注者openid

        // TODO: 2020/11/7 获取对应的模板id


        // TODO: 2020/11/7 推送公众号消息


    }


}
