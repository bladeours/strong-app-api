package com.bladeours.strongapp.api.service;

import com.bladeours.strongapp.api.model.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise getByName(String name);
    void save(Exercise exercise);
    List<Exercise> saveAll(Iterable<Exercise> exercises);
}
