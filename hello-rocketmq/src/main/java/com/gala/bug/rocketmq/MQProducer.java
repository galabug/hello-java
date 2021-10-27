package com.gala.bug.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MQProducer {
    private  static final Logger LOGGER = LoggerFactory.getLogger(MQProducer.class);

    private final DefaultMQProducer producer = new DefaultMQProducer("defaultProducer");

    @Value("${rocketmq.namesrvaddr}")
    private String nameservAddr;
    /*
     * 初始化
     */
    @PostConstruct
    public void  start(){
        try {
            LOGGER.info("MQ:启动生产者");
            producer.setNamesrvAddr(nameservAddr);
            producer.start();
            //发送异步失败时的重试次数(默认值2)
            //producer.setRetryTimesWhenSendAsyncFailed(2);
        }catch (MQClientException e){
            LOGGER.error("MQ:启动生产者失败:{}-{}",e.getResponseCode(),e.getErrorMessage());
            throw  new RuntimeException(e.getErrorMessage(),e);
        }
    }

    /*
     *发送消息
     */
    public void sendMessage(String data,String topic,String tags,String keys){
        try {
            byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message message = new Message(topic,tags,keys,messageBody);
            //topic 主题
            //Message Key一般用于消息在业务层面的唯一标识。
            //tags 消息标签，消费者用来过滤消息
            // messageBody 消息本体-字节数组

            // 生产者单向发送,不管是否发送成功 一般不用
            message = new Message(topic,"oneway",keys,messageBody);
            producer.sendOneway(message);

            // 生产者可靠异步发送
            message = new Message(topic,"sync",keys,messageBody);
            SendResult sendResult = producer.send(message);
            System.out.printf("%s%n%n%n", "生产者可靠异步发送-->"+sendResult.getSendStatus()+":(MsgId):"
                    +sendResult.getMsgId()+":(queueId):"
                    +sendResult.getMessageQueue().getQueueId()
                    +"(value):"+ new String(message.getBody()));

            // 生产者可靠异步发送
            message = new Message(topic,"async",keys,messageBody);
            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    LOGGER.info("MQ: 生产者可靠异步发送{}",sendResult);
                }
                public void onException(Throwable e) {
                    LOGGER.error(e.getMessage(),e);
                }
            });

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
    }

    @PreDestroy
    public void stop(){
        if(producer !=null){
            producer.shutdown();
            LOGGER.info("MQ:关闭生产者");
        }
    }
}
