package solvd.laba.ermakovich.hf.event.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregate;
import solvd.laba.ermakovich.hf.aggregate.AggregateRoot;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.event.Event;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
@Data
@SuperBuilder
public class CreateAccountEventRoot extends EventRoot implements Event {

    public static final String EVENT_TYPE = "CreateAccount";

    @Transient
    private Account account;

    public CreateAccountEventRoot(String aggregateId) {
        super(EVENT_TYPE, aggregateId);
    }

    public CreateAccountEventRoot(Account account, String aggregateId) {
        this(aggregateId);
        this.account = account;
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        account.setAccountNumber(new AccountNumber().generate());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus("CREATED");
        return new ObjectMapper().writeValueAsString(account);
    }

    @Override
    public void copyTo(AggregateRoot aggregate) {
        var accountAggregate = (AccountAggregate) aggregate;
        accountAggregate.setAccountNumber(account.getAccountNumber());
        accountAggregate.setBalance(account.getBalance());
        accountAggregate.setUserId(account.getUserId());
    }

}

