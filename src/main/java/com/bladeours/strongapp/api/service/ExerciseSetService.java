package com.bladeours.strongapp.api.service;

import com.bladeours.strongapp.api.model.entity.ExerciseSet;

import java.util.List;

public interface ExerciseSetService {
    void save(ExerciseSet exerciseSet);
    List<ExerciseSet> saveAll(Iterable<ExerciseSet> exerciseSets);
}
