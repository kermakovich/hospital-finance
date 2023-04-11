package solvd.laba.ermakovich.hf.kafka.producer;

import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface KafkaProducer {

    void send(EventRoot value);

}
