package solvd.laba.ermakovich.hf.command;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountCommand {

    private String aggregateId;
    private UUID userId;

    public CreateAccountCommand(UUID userId) {
        this.aggregateId = UUID.randomUUID().toString();
        this.userId = userId;
    }

}
