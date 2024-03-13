package com.rgs.kafkaexample.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Random;

@Component
@Slf4j
public class KafkaListenerT {


    @KafkaListener(topics = "${rgs.topic.name}", groupId = "GROUP_${rgs.topic.name}", containerFactory="test")
    public void listener(String message, Acknowledgment ack,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partitionId,
            @Header(KafkaHeaders.OFFSET) String offSet,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName,
            @Header(KafkaHeaders.GROUP_ID) String groupId

    ){
        Random random = new Random();
        if(random.nextBoolean()){
            log.info("## KafkaMessageListener commited");
            ack.acknowledge();
        }else{
            log.error("## KafkaMessageListener not commit");
        }

        log.info("## KafkaMessageListener partitionId:[{}], offset:[{}], timesTemp[{}]", partitionId, offSet, ts);
        System.out.println("## KafkaMessageListener ["+message+"]");
    }
}
