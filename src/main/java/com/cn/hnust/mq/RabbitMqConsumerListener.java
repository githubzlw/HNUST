package com.cn.hnust.mq;

import com.rabbitmq.client.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.io.IOException;

public class RabbitMqConsumerListener implements ChannelAwareMessageListener {
    private Logger logger= LoggerFactory.getLogger(RabbitMqConsumerListener.class);

    //正常消费掉后通知mq服务器移除此条mq
    private void basicACK(Message message,Channel channel){
        try{
          channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch(IOException e){
            logger.error("通知服务器移除mq时异常，异常信息："+e);
        }
    }
    //处理异常，mq重回队列
    private void basicNACK(Message message,Channel channel){
        try{
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }catch(IOException e){
            logger.error("mq重新进入服务器时出现异常，异常信息："+e);
        }
    }
	@Override
	public void onMessage(Message arg0, Channel arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
