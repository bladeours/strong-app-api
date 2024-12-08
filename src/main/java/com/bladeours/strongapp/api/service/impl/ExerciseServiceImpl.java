package com.bladeours.strongapp.api.service.impl;

import com.bladeours.strongapp.api.model.entity.Exercise;
import com.bladeours.strongapp.api.repository.ExerciseRepository;
import com.bladeours.strongapp.api.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;


    @Override
    public Exercise getByName(String name) {
        return exerciseRepository.findByName(name).orElseThrow();
    }

    @Override
    public void save(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> saveAll(Iterable<Exercise> exercises) {
        return exerciseRepository.saveAll(exercises);
    }
}
