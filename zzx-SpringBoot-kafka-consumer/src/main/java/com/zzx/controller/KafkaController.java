package com.zzx.controller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzx.kafka.Consumer;
import com.zzx.kafka.KafkaConsumerConfig;
import com.zzx.kafka.ConsumerThread;

@Controller
@RequestMapping("")
public class KafkaController {
	
	ConcurrentMap<String, ConsumerThread> consumers = new ConcurrentHashMap<>();

	@Autowired
	private KafkaConsumerConfig consumerConfig;
	
	@RequestMapping("/start/{key}")
	@ResponseBody
	public String startConsumer(@PathVariable(name="key",required=true) String key) {
		
		Consumer consumer = new Consumer("tes", "test3", consumerConfig);
		
		ConsumerThread cThread = new ConsumerThread(consumer);
		
		consumers.put(key, cThread);
		
		cThread.start();
		
		return "start";
	}
	
	@RequestMapping("/stop/{key}")
	@ResponseBody
	public String stopConsumer(@PathVariable(name="key",required=true) String key) {
		
		ConsumerThread consumerThread = consumers.get(key);
		
		consumerThread.setStopFlag(false);
		
		return "stop";
	}
}
