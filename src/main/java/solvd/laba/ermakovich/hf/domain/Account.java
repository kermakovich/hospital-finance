package solvd.laba.ermakovich.hf.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@Document
public class Account {

    @Id
    private String id;

    @Indexed
    private UUID externalId;
    private String accountNumber;
    private BigDecimal balance;

    public Account(UUID externalId, String accountNumber) {
        this.externalId = externalId;
        this.balance = BigDecimal.ZERO;
        this.accountNumber = accountNumber;
    }

}
