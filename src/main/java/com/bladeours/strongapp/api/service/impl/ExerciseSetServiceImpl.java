package com.bladeours.strongapp.api.service.impl;

import com.bladeours.strongapp.api.model.entity.ExerciseSet;
import com.bladeours.strongapp.api.repository.ExerciseSetRepository;
import com.bladeours.strongapp.api.service.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseSetServiceImpl implements ExerciseSetService {
    private final ExerciseSetRepository exerciseSetRepository;

    @Override
    public void save(ExerciseSet exerciseSet) {
        exerciseSetRepository.save(exerciseSet);
    }

    @Override
    public List<ExerciseSet> saveAll(Iterable<ExerciseSet> exerciseSets) {
        return exerciseSetRepository.saveAll(exerciseSets);
    }
}
