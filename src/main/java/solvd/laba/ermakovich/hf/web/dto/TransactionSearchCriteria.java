package solvd.laba.ermakovich.hf.web.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class TransactionSearchCriteria {

    @Nonnull
    private String accountNumber;
    private int page = 0;
    private int size = 10;
    private String sort = "createdTime";

}