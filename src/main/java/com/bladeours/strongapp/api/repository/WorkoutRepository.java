package com.bladeours.strongapp.api.repository;

import com.bladeours.strongapp.api.model.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findAllByDateBetween(LocalDateTime from, LocalDateTime to);
}
