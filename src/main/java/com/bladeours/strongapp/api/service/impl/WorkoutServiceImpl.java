package com.bladeours.strongapp.api.service.impl;

import com.bladeours.strongapp.api.model.dto.WorkoutDto;
import com.bladeours.strongapp.api.model.entity.Workout;
import com.bladeours.strongapp.api.repository.WorkoutRepository;
import com.bladeours.strongapp.api.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    @Override
    public List<WorkoutDto> getAllWorkouts() {
        return workoutRepository.findAll().stream().map(WorkoutDto::of).toList();
    }

    @Override
    public List<WorkoutDto> getWorkouts(LocalDateTime from, LocalDateTime to) {
        return workoutRepository.findAllByDateBetween(from, to).stream().map(WorkoutDto::of).toList();
    }


    @Override
    public WorkoutDto getWorkout(Long id) {
        return null;
    }

    @Override
    public List<Workout> saveAll(List<Workout> workouts) {
        return workoutRepository.saveAll(workouts);
    }

    @Override
    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }
}
