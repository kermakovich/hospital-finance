package solvd.laba.ermakovich.hf.domain;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Account {

    @Id
    private String id;

    @Indexed
    private UUID userId;
    private String accountNumber;
    private BigDecimal balance;
    private String status;

}
