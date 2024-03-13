package com.rgs.kafkaexample.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${rgs.topic.name}")
    private String rgsTopicName;
    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1(@Value("${rgs.topic.name}") String topicName,
                           @Value("${rgs.topic.num.partitions}") int numPartitions,
                           @Value("${rgs.topic.num.replicationFactor}") short replicationFactor){
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("retention.ms", "2000");
//        objectObjectHashMap.put("retention.bytes", "1073741824");
//        objectObjectHashMap.put("min.insync.replicas", "2");
        return new NewTopic(topicName , numPartitions, replicationFactor)
                .configs(objectObjectHashMap

                );
    }

}
