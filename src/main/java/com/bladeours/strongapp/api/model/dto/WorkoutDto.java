package com.bladeours.strongapp.api.model.dto;

import com.bladeours.strongapp.api.model.entity.ExerciseSet;
import com.bladeours.strongapp.api.model.entity.Workout;

import java.time.LocalDateTime;
import java.util.List;

public record WorkoutDto(
        String name,
        LocalDateTime date,

        Integer durationSec,
        String notes,
        List<ExerciseSetDto> exerciseSets
        ) {
        public static WorkoutDto of(Workout workout) {
                return new WorkoutDto(
                        workout.getName(),
                        workout.getDate(),
                        workout.getDurationSec(),
                        workout.getNotes(),
                        workout.getExerciseSets().stream().map(ExerciseSetDto::of).toList()
                );
        }
}
