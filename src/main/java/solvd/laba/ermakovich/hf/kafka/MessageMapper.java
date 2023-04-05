package solvd.laba.ermakovich.hf.kafka;

/**
 * @author Ermakovich Kseniya
 */
public interface MessageMapper<T, S> {

    T toEvent(S event);

}
