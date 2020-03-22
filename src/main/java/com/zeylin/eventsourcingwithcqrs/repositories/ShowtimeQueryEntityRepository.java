package com.zeylin.eventsourcingwithcqrs.repositories;

import com.zeylin.eventsourcingwithcqrs.entities.ShowtimeQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeQueryEntityRepository extends JpaRepository<ShowtimeQueryEntity, Integer> {
}
