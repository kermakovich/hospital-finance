package solvd.laba.ermakovich.hf.kafka.consumer;

/**
 * @author Ermakovich Kseniya
 */
public interface MessageMapper<T, S> {

    T toEvent(S event);

}
