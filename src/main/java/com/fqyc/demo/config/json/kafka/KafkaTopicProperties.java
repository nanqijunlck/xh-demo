package com.fqyc.demo.config.json.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author stiven_wu
 */
@ConfigurationProperties("kafka.topic")
@Data
public class KafkaTopicProperties implements Serializable {

    private String groupId;

    private String[] topicName;

}
