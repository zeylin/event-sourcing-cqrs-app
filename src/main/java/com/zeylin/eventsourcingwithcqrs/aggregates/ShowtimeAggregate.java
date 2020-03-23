package com.zeylin.eventsourcingwithcqrs.aggregates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShowtimeAggregate {

    @Id
    private String id;
    @NotNull
    private Integer movieId;
    @NotNull
    private LocalDateTime time;
    @NotNull
    private Integer auditoriumId;

}
