package solvd.laba.ermakovich.hf.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("events")
@SuperBuilder
public class EventRoot {

    @Id
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String aggregateId;
    private String eventType;

    @Version
    private long version;
    private String payload;
    private LocalDateTime timeStamp;

    protected EventRoot(String eventType, String aggregateId) {
        this.id = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now();
    }

}
