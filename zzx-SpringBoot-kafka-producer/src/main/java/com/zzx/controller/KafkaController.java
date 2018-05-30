package com.zzx.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzx.common.JacksonUtils;
import com.zzx.common.KafkaMessage;


@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${kafka.topic}")
    private String topic;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @GetMapping("/send/{message}")
    @ResponseBody 
    public String sendKafka(HttpServletRequest request, HttpServletResponse response
    		,@PathVariable(name = "message",required = true) String message) throws Exception {
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("success", false);
    	
        try {
        	Random rand = new Random();
        	int nextInt = rand.nextInt(6+1);
        	//key使用随机数  将消息放于不同的partition
        	KafkaMessage msg = new KafkaMessage(nextInt + "", message, new Date());
        	
            kafkaTemplate.send(topic, msg.getKey(), JacksonUtils.bean2Json(msg));
            
            result.put("success", true);
            
            return JacksonUtils.bean2Json(result);
        } catch (Exception e) {
        	logger.error("kafka-exception : ",e);
            return JacksonUtils.bean2Json(result);
        }
    }
    
	
}
