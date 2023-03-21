package solvd.laba.ermakovich.hf.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @JsonIgnore
    private String id;
    private UUID externalId;
    private String accountNumber;
    private BigDecimal balance;

}
