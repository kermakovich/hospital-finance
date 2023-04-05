package solvd.laba.ermakovich.hf.event.account;

import lombok.experimental.SuperBuilder;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
@SuperBuilder
public class DeleteAccountEventRoot extends EventRoot {

    public static final String EVENT_TYPE = "DeleteAccount";

    public DeleteAccountEventRoot(String aggregateId) {
        super(EVENT_TYPE, aggregateId);
    }

    @Override
    public String getPayload() {
        return this.getAggregateId();
    }

}
