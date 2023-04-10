package solvd.laba.ermakovich.hf.event.account;

import java.time.LocalDateTime;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
public class CreateAccountResponseFactory {

    public EventRoot failed(CreateAccount event) {
        return CreateAccount.builder()
                .eventType("createAccountRejected")
                .aggregateId(event.getAggregateId())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public EventRoot success(CreateAccount event) {
        return CreateAccount.builder()
                .account(event.getAccount())
                .eventType("createAccountCompleted")
                .payload(event.getPayload())
                .timeStamp(LocalDateTime.now())
                .aggregateId(event.getAggregateId())
                .build();
    }

}
