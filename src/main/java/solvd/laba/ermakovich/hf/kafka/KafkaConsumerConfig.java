package solvd.laba.ermakovich.hf.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
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
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.config-file}")
    private String configPath;

    private static final String TOPIC_KEY = "topic";

    protected Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> kafkaPropertiesMap = new HashMap<>(4);
        kafkaPropertiesMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        kafkaPropertiesMap.put(ConsumerConfig.GROUP_ID_CONFIG,
                XmlUtils.getValue(configPath, "groupId"));
        kafkaPropertiesMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                XmlUtils.getValue(configPath, "keyDeserializer"));
        kafkaPropertiesMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                XmlUtils.getValue(configPath, "valueDeserializer"));
        kafkaPropertiesMap.put(TOPIC_KEY,
                XmlUtils.getValue(configPath, TOPIC_KEY));
        return kafkaPropertiesMap;
    }


    @Bean
    public ReceiverOptions<String, UUID> kafkaReceiverOptions() {
        var properties = kafkaConsumerProperties();
        ReceiverOptions<String, UUID> options = ReceiverOptions.create(properties);
        return options.subscription(
                        (Collections.singletonList(
                                (String) properties.get(TOPIC_KEY)
                        )))
                .addAssignListener(receiverPartitions -> log.debug("assign consumer {}", receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.debug("revoke consumer {}", receiverPartitions));

    }

    @Bean
    KafkaReceiver<String, UUID> kafkaReceiver() {
        return KafkaReceiver.create(kafkaReceiverOptions());
    }

}