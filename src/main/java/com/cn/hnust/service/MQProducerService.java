package com.cn.hnust.service;

public interface MQProducerService {

    void sendDataToQueue(String queueKey, Object object);
}
