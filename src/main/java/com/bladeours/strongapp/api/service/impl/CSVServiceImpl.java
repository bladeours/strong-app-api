package com.bladeours.strongapp.api.service.impl;

import com.bladeours.strongapp.api.mapper.ExerciseMapper;
import com.bladeours.strongapp.api.mapper.ExerciseSetMapper;
import com.bladeours.strongapp.api.mapper.WorkoutMapper;
import com.bladeours.strongapp.api.model.entity.Exercise;
import com.bladeours.strongapp.api.model.entity.ExerciseSet;
import com.bladeours.strongapp.api.model.entity.Workout;
import com.bladeours.strongapp.api.service.CSVService;
import com.bladeours.strongapp.api.service.ExerciseService;
import com.bladeours.strongapp.api.service.ExerciseSetService;
import com.bladeours.strongapp.api.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CSVServiceImpl implements CSVService {
    private final ExerciseSetService exerciseSetService;
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;
    private final String [] HEADERS = {"Workout #", "Date", "Workout Name", "Duration (sec)", "Exercise Name",
            "Set Order", "Weight (kg)", "Reps", "RPE", "Distance (meters)", "Seconds", "Notes", "Workout Notes"};
    private CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader(HEADERS)
            .setSkipHeaderRecord(true)
            .build();


    @Override
    @Transactional
    public void putDataInDatabase(MultipartFile file) throws IOException {
        Map<Long, Workout> workoutsMapById = saveWorkouts(file);
        Map<String, Exercise> exercisesMapByName = saveExercises(file);
        List<ExerciseSet> exerciseSets = saveExerciseSets(file, workoutsMapById, exercisesMapByName);

        workoutsMapById.forEach(addExerciseSetsInWorkout(exerciseSets));
        exercisesMapByName.forEach(addExerciseSetsInExercise(exerciseSets));
    }

    private static BiConsumer<String, Exercise> addExerciseSetsInExercise(List<ExerciseSet> exerciseSets) {
        return (name, exercise) -> {
            var exerciseSetsForExercise = exerciseSets.stream()
                    .filter(exerciseSet -> exerciseSet.getExercise().equals(exercise))
                    .toList();
            exercise.setExerciseSets(exerciseSetsForExercise);
        };
    }

    private static BiConsumer<Long, Workout> addExerciseSetsInWorkout(List<ExerciseSet> exerciseSets) {
        return (id, workout) -> {
            var exerciseSetsForWorkout = exerciseSets.stream()
                    .filter(exerciseSet -> exerciseSet.getWorkout().equals(workout))
                    .toList();
            workout.setExerciseSets(exerciseSetsForWorkout);
        };
    }

    private Map<Long, Workout> saveWorkouts(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        try (CSVParser records = csvFormat.parse(reader)){
            List<Workout> workoutsToSave =
                    records.stream()
                            .filter(filterOutNotesAndRestTimers())
                            .map(new WorkoutMapper())
                            .distinct()
                            .toList();
            return workoutService.saveAll(workoutsToSave)
                    .stream().collect(Collectors.toMap(Workout::getId, workout -> workout));
        }
    }
    
    private Map<String, Exercise> saveExercises(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        try (CSVParser records = csvFormat.parse(reader)){
            List<Exercise> exercisesToSave =
                    records.stream()
                            .filter(filterOutNotesAndRestTimers())
                            .map(new ExerciseMapper())
                            .distinct()
                            .toList();
            return exerciseService.saveAll(exercisesToSave)
                    .stream().collect(Collectors.toMap(Exercise::getName, exercise -> exercise));
        }
    }
    
    private List<ExerciseSet> saveExerciseSets(MultipartFile file,
                                               Map<Long, Workout> workoutMap,
                                               Map<String, Exercise> exerciseMap) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        try (CSVParser records = csvFormat.parse(reader)){
            var exerciseSetsToSave = records.stream()
                    .filter(filterOutNotesAndRestTimers())
                    .map(new ExerciseSetMapper(exerciseMap, workoutMap))
                    .toList();
            return exerciseSetService.saveAll(exerciseSetsToSave);
        }
    }

    private Predicate<CSVRecord> filterOutNotesAndRestTimers() {
        return record -> {
            try {
                Integer.parseInt(record.get("Set Order"));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
    }
}

