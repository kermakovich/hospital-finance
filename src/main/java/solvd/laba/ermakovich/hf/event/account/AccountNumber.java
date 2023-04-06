package solvd.laba.ermakovich.hf.event.account;

import java.security.SecureRandom;
import lombok.SneakyThrows;

/**
 * @author Ermakovich Kseniya
 */
public final class AccountNumber {

    @SneakyThrows
    public String generate() {
        StringBuilder result = new StringBuilder();
        var random = SecureRandom.getInstanceStrong();
        for (int i = 0; i < 12; i++) {
            result.append(random.nextInt(0, 9));
        }
        return result.toString();
    }

}
