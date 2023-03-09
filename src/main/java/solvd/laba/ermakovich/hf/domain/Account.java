package solvd.laba.ermakovich.hf.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {

    @Id
    private Long id;
    private UUID externalId;
    private String accountNumber;
    private BigDecimal balance;

    public Account(UUID externalId, String accountNumber) {
        this.externalId = externalId;
        this.balance = BigDecimal.ZERO;
        this.accountNumber = accountNumber;
    }

}
