package com.zzx.kafka;

import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * 消费线程
 * @author zzx
 *
 */
public class ConsumerThread extends Thread{

	/**
	 * 线程停止标记
	 */
	private Boolean stopFlag = true;
	
	 /**
     * kafka 消费者
     */
    private  KafkaConsumer consumer;

    /**
     *  topic
     */
    private  String topic;

    /**
     *  组id
     */
    private  String groupId;
    
    /**
     * poll 向kafka获取消息的间隔
     */
    private long pollTime = 1000;

    public ConsumerThread(Consumer consumer) {
        this.consumer = consumer.getConsumer();
        this.topic = consumer.getTopic();
        this.groupId = consumer.getA_groupId();
    }
    
    
    /**
     *  * 监听主题,有消息就读取
     * 从kafka里面得到数据后,具体怎么去处理. 如果需要开启kafka处理消息的广播模式,多个监听要监听不同的group,
     * 即方法上的注解@KafkaListener里的group一定要不一样.如果多个监听里的group写的一样,就会造成只有一个监听能处理其中的消息,
     * 另外监听就不能处理消息了.也即是kafka的分布式消息处理方式.
     * 在同一个group里的监听,共同处理接收到的消息,会根据一定的算法来处理.如果不在一个组,但是监听的是同一个topic的话,就会形成广播模式
     */
	@Override
	public void run() {
		try {
			//设置监听的topic
			consumer.subscribe(Collections.singletonList(this.topic));
			while (stopFlag) {
			    ConsumerRecords<String, String> records = consumer.poll(pollTime);
			    for (ConsumerRecord<String, String> record : records) {
			        System.out.println("Thread: "+Thread.currentThread().getName()
			                +"Received message: (" + this.groupId + ", " + record.value() + ") at offset "
			                + record.offset()+" partition : "+records.partitions());
			    }
			}
		} finally {
			consumer.close();
		}
		
    }


	public Boolean getStopFlag() {
		return stopFlag;
	}


	public void setStopFlag(Boolean stopFlag) {
		this.stopFlag = stopFlag;
	}
	
	
}
