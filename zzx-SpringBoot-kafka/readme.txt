

	--consumer启动多个同样的
	--producer的commit 
	--批量消费
	--消费push 和 pull

kafka 
	-topic话题
		-partition分区
	-producer生产者
	-consumer消费者
	
	
springboot-kafka
	-consumer
		-@KafkaListener 使用注解监听并进行消费  
		-@KafkaListener(topics = "指定监听的topics",
						id = "id是消费者监听容器",
						group = "需要配置MessageListenerContainer(待测试)",
						topicPattern = "正则匹配话题",
						topicPartitions ={
							//消费test的0和3分区
							@TopicPartition(topic ="test",partitions = {"0","3"})
							//消费test1的0和1分区
							@TopicPartition(topic ="test1",partitions = {"0","1"})
							}
						)
		-注意: topicPartitions和topics、topicPattern不能同时使用
		
		
	-producer
		-使用 kafkaTemplate.send发送消息
			-指定topic
			-指定key通过算法将消息分配到partition
			-指定partition 将消息发送到指定的partition
			
			
zzx-springBoot-kafka-consumer
	--因为@KafkaListener无法设置groupId
	--所以自定义consumer 指定组	开启线程 进行消费  可以多线程消费同一个topic
	--注意线程结束后关闭consumer否则消息将会消费不到. 因为,关闭consumer和开启新的consumer kafka会自动分配 partition?未确认	


