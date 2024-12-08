package com.bladeours.strongapp.api.model.dto;

import com.bladeours.strongapp.api.model.entity.ExerciseSet;

public record ExerciseSetDto(
    Integer setOrder,
    Double weightKg,
    Integer reps,
    Double rpe,
    Double distanceM,
    Integer seconds,
    String exercise
) {
    public static ExerciseSetDto of(ExerciseSet exerciseSet) {
        return new ExerciseSetDto(
            exerciseSet.getSetOrder(),
            exerciseSet.getWeightKg(),
            exerciseSet.getReps(),
            exerciseSet.getRpe(),
            exerciseSet.getDistanceM(),
            exerciseSet.getSeconds(),
            exerciseSet.getExercise().getName()
        );
    }
}
