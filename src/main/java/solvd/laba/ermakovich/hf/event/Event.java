package solvd.laba.ermakovich.hf.event;

import solvd.laba.ermakovich.hf.aggregate.AggregateRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface Event {

    void copyTo(AggregateRoot aggregate);

}
