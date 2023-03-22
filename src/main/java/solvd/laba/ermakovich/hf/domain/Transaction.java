package solvd.laba.ermakovich.hf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Transaction {

    @Id
    private String id;

    @Indexed
    private String accountNumber;
    private LocalDateTime createdTime;
    private BigDecimal amount;

}
