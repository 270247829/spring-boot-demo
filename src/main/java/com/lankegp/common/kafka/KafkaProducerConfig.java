package com.lankegp.common.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liugongrui on 2018/2/22.
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String brokers;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<String, String>(producerFactory());
        return kafkaTemplate;
    }

    public ProducerFactory<String, String> producerFactory() {

        // set the producer properties
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 65536);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 524288);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<String, String>(properties);
    }

}
