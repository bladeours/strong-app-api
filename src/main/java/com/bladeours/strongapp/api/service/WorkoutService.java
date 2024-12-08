package com.bladeours.strongapp.api.service;

import com.bladeours.strongapp.api.model.dto.WorkoutDto;
import com.bladeours.strongapp.api.model.entity.Workout;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutService {
    List<WorkoutDto> getAllWorkouts();
    List<WorkoutDto> getWorkouts(LocalDateTime from, LocalDateTime to);
    WorkoutDto getWorkout(Long id);
    List<Workout> saveAll(List<Workout> workouts);
    Workout save(Workout workout);
}
