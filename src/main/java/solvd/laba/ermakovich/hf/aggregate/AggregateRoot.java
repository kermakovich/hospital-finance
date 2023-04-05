package solvd.laba.ermakovich.hf.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
public abstract class AggregateRoot {

    @Id
    protected String id;
    protected String type;

    @Version
    protected long version;

    protected AggregateRoot(final String id, final String aggregateType) {
        this.id = id;
        this.type = aggregateType;
    }

}
