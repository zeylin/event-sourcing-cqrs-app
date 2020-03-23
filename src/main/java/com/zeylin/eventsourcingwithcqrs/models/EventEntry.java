package com.zeylin.eventsourcingwithcqrs.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.zeylin.eventsourcingwithcqrs.utils.EventType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "EventEntry")
@Getter
@Setter
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class EventEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String aggregateId;
    private LocalDateTime timestamp;
    @NotNull
    private EventType eventType;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String payload;

}
