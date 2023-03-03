package solvd.laba.ermakovich.hf.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Getter
@Setter
public class AccountDto {

    @JsonIgnore
    private Long id;
    private UUID externalId;
    private String accountNumber;
    private BigDecimal balance;

}
