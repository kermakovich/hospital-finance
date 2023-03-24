package solvd.laba.ermakovich.hf.domain;

import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class TransactionSearchCriteria {

    private String accountNumber;
    private int page;
    private int size;
    private String sort;

}
