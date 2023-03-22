package solvd.laba.ermakovich.hf.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class TransactionDto {

    private String id;
    private String accountNumber;
    private LocalDateTime createdTime;
    private BigDecimal amount;
}
