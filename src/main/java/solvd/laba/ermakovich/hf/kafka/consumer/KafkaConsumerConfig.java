package solvd.laba.ermakovich.hf.kafka.consumer;

import com.jcabi.xml.XMLDocument;
import io.github.eocqrs.kafka.xml.TextXpath;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private static final String TOPIC_KEY = "topic";

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.config-file}")
    private String configPath;

    @SneakyThrows
    protected Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> kafkaPropertiesMap = new HashMap<>(7);
        kafkaPropertiesMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        var file = new XMLDocument(getClass().getResourceAsStream(configPath));
        kafkaPropertiesMap.put(ConsumerConfig.GROUP_ID_CONFIG,
                new TextXpath(
                        file,
                        "//groupId"
                ).toString());
        kafkaPropertiesMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                new TextXpath(
                        file,
                        "//keyDeserializer"
                ).toString());
        kafkaPropertiesMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                new TextXpath(
                        file,
                        "//valueDeserializer"
                ).toString());
        kafkaPropertiesMap.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,
                new TextXpath(
                        file,
                        "//useTypeInfoHeaders"
                ).toString());
        kafkaPropertiesMap.put(JsonDeserializer.VALUE_DEFAULT_TYPE,
                new TextXpath(
                        file,
                        "//valueDefaultType"
                ).toString());
        kafkaPropertiesMap.put(TOPIC_KEY,
                new TextXpath(
                        file,
                        "//" + TOPIC_KEY
                ).toString());
        return kafkaPropertiesMap;
    }

    @Bean
    public ReceiverOptions<String, EventRoot> kafkaReceiverOptions() {
        var properties = kafkaConsumerProperties();
        ReceiverOptions<String, EventRoot> options = ReceiverOptions.create(properties);
        return options.subscription(
                        (Collections.singletonList(
                                (String) properties.get(TOPIC_KEY)
                        )))
                .addAssignListener(receiverPartitions -> log.debug("assign consumer {}", receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.debug("revoke consumer {}", receiverPartitions));

    }

    @Bean
    public KafkaReceiver<String, EventRoot> kafkaReceiver() {
        return KafkaReceiver.create(kafkaReceiverOptions());
    }

}