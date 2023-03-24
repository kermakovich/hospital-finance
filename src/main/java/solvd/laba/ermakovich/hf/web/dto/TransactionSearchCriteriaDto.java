package solvd.laba.ermakovich.hf.web.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class TransactionSearchCriteriaDto {

    @Nonnull
    private String accountNumber;
    private int page = 0;
    private int size = 10;
    private String sort = "createdTime";

    public boolean hasDefaultSettings() {
        return 0 == page && 10 == size && "createdTime".equals(sort);
    }

}
