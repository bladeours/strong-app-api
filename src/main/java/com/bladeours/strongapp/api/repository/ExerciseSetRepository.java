package com.bladeours.strongapp.api.repository;

import com.bladeours.strongapp.api.model.entity.Exercise;
import com.bladeours.strongapp.api.model.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
}
