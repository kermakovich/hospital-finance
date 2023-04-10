package solvd.laba.ermakovich.hf.aggregate;

import solvd.laba.ermakovich.hf.event.account.CreateAccount;
import solvd.laba.ermakovich.hf.event.account.DeleteAccount;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountAggregateService {

    void apply(CreateAccount eventRoot);

    void apply(DeleteAccount eventRoot);

}
