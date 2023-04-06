package solvd.laba.ermakovich.hf.web.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class AccountAggregateDto extends AggregateDto {

    private UUID userId;
    private String accountNumber;
    private BigDecimal balance;

    public AccountAggregateDto(String id) {
        super(id);
    }

}
