package com.rgs.kafkaexample.service;

import com.rgs.kafkaexample.controller.CallKafkaController;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Configuration
@EnableScheduling
public class ConsumerService {
    public void consumer(){
        var consumer = new KafkaConsumer<String, String>(propertiesConsumer());
        consumer.subscribe(Collections.singletonList("NEW_MESSAGE"));
        var records = consumer.poll(Duration.ofMillis(100));
        if(records.isEmpty()){
            System.out.println("notFound Records on NEW_MESSAGE");
        }else{
            System.out.println(records);
        }
    }
    private Properties propertiesConsumer() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9082");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, CallKafkaController.class.getName());
        return properties;
    }

}
