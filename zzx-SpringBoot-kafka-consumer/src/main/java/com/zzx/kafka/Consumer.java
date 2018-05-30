package com.zzx.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer  {

    /**
     *  日志处理
     */
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     *  topic
     */
    private final String topic;

    /**
     *  公共连接属性
     */
    private  Properties props ;

    /**
     * 消费者组id
     */
    private final String groupId;
    

    public Consumer(String groupId, String topic, KafkaConsumerConfig consumerConfig) {
        createConsumerConfig(groupId,consumerConfig);
        this.topic = topic;
        this.groupId = groupId;
    }


    private Properties createConsumerConfig(String groupId, KafkaConsumerConfig consumerConfig) {
        props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,consumerConfig.servers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerConfig.enableAutoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerConfig.autoCommitInterval);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerConfig.sessionTimeout);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerConfig.autoOffsetReset);
        // 其他配置再配置
        return props;
    }

    public KafkaConsumer getConsumer() {
        return new KafkaConsumer(props);
    }

    /**
     *  其他类获取topic
     * @return
     */
    public String getTopic() {
        return topic;
    }

    public String getA_groupId() {
        return groupId;
    }
}