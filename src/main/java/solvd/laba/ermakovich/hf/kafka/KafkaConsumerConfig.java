package solvd.laba.ermakovich.hf.kafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import solvd.laba.ermakovich.hf.event.IntegrationEvent;
import solvd.laba.ermakovich.hf.parser.XmlXPath;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private static final String TOPIC_KEY = "topic";
    private final ResourceLoader resourceLoader;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.config-file}")
    private String configPath;

    protected Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> kafkaPropertiesMap = new HashMap<>(7);
        kafkaPropertiesMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        kafkaPropertiesMap.put(ConsumerConfig.GROUP_ID_CONFIG,
                new XmlXPath(configPath, "groupId", resourceLoader)
                        .getText());
        kafkaPropertiesMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                new XmlXPath(configPath, "keyDeserializer", resourceLoader)
                        .getText());
        kafkaPropertiesMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                new XmlXPath(configPath, "valueDeserializer", resourceLoader)
                        .getText());
        kafkaPropertiesMap.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,
                new XmlXPath(configPath, "useTypeInfoHeaders", resourceLoader)
                        .getText());
        kafkaPropertiesMap.put(JsonDeserializer.VALUE_DEFAULT_TYPE,
                new XmlXPath(configPath, "valueDefaultType", resourceLoader)
                        .getText());
        kafkaPropertiesMap.put(TOPIC_KEY,
                new XmlXPath(configPath, TOPIC_KEY, resourceLoader)
                        .getText());
        return kafkaPropertiesMap;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public ReceiverOptions<String, IntegrationEvent> kafkaReceiverOptions() {
        var properties = kafkaConsumerProperties();
        ReceiverOptions<String, IntegrationEvent> options = ReceiverOptions.create(properties);
        return options.subscription(
                        (Collections.singletonList(
                                (String) properties.get(TOPIC_KEY)
                        )))
                .addAssignListener(receiverPartitions -> log.debug("assign consumer {}", receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.debug("revoke consumer {}", receiverPartitions));

    }

    @Bean
    public KafkaReceiver<String, IntegrationEvent> kafkaReceiver() {
        return KafkaReceiver.create(kafkaReceiverOptions());
    }

}