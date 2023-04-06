package solvd.laba.ermakovich.hf.aggregate;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class AccountAggregate extends AggregateRoot {

    public static final String AGGREGATE_TYPE = "Account";
    private UUID userId;
    private String accountNumber;
    private BigDecimal balance;

    public AccountAggregate(String id) {
        super(id, AGGREGATE_TYPE);
    }

}
