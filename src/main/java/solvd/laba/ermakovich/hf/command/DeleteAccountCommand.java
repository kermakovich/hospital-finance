package solvd.laba.ermakovich.hf.command;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
public class DeleteAccountCommand {

    private String aggregateId;

    public DeleteAccountCommand(String aggregateId) {
        this.aggregateId = aggregateId;
    }

}
