package com.zzx.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;

//@Component
public class Question1  {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @KafkaListener(group = "test3",topicPartitions= {
    		@TopicPartition(topic ="test3",partitions = {"4","3","5"} )
    })
    public void listen(ConsumerRecord<?, ?> record) throws InterruptedException {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
    }
    
    
    @KafkaListener(topics = {"test3"},group = "test4")
    public void listen2(ConsumerRecord<?, ?> record) {
        logger.info("222222222222222kafka的key: " + record.key());
        logger.info("22222222222kafka的value: " + record.value().toString());
    }
    
}
