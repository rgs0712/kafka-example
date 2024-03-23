package com.rgs.kafkaexample.controller;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("kafka")
public class CallKafkaController {
    @Value("${rgs.topic.name}")
    private String rgsTopicName;

    @Value("${rgs.topic2.name}")
    private String rgsTopic2Name;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("producer")
    @SneakyThrows
    public ResponseEntity<?> get(@RequestParam String message) throws ExecutionException, InterruptedException {
        kafkaTemplate.send(rgsTopicName, message);
        kafkaTemplate.send(rgsTopic2Name, message);
        return ResponseEntity.ok("test");
    }
}
