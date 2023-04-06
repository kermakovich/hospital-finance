package solvd.laba.ermakovich.hf.event;

import java.time.LocalDateTime;
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
public class IntegrationEvent {

    private String id;
    private String eventType;
    private String payload;
    private LocalDateTime timeStamp;

    protected IntegrationEvent(String eventType) {
        this.id = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now();
    }

}
