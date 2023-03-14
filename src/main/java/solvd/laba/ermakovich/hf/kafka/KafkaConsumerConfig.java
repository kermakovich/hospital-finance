package solvd.laba.ermakovich.hf.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;

    @Value("${spring.kafka.topic}")
    private String TOPIC;

    protected Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> kafkaPropertiesMap = new HashMap<>(4);
        kafkaPropertiesMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        kafkaPropertiesMap.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
        kafkaPropertiesMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaPropertiesMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class);
        return kafkaPropertiesMap;
    }

    @Bean
    public ReceiverOptions<String, UUID> kafkaReceiverOptions() {
        ReceiverOptions<String, UUID> options = ReceiverOptions.create(kafkaConsumerProperties());
        return options.subscription(
                        Collections.singletonList(TOPIC)
                )
                .addAssignListener(receiverPartitions -> log.debug("assign consumer {}", receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.debug("revoke consumer {}", receiverPartitions));

    }

    @Bean
    KafkaReceiver<String, UUID> kafkaReceiver() {
        return KafkaReceiver.create(kafkaReceiverOptions());
    }

}