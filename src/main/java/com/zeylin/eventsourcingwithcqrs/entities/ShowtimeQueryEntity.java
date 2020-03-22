package com.zeylin.eventsourcingwithcqrs.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeQueryEntity {

    @Id
    private String id;
    @NotNull
    private Integer movieId;
    @NotNull
    private LocalDateTime time;
    @NotNull
    private Integer auditoriumId;

}
