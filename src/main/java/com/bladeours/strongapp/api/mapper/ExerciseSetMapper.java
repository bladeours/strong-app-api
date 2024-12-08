package com.bladeours.strongapp.api.mapper;

import com.bladeours.strongapp.api.model.entity.Exercise;
import com.bladeours.strongapp.api.model.entity.ExerciseSet;
import com.bladeours.strongapp.api.model.entity.Workout;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;

import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class ExerciseSetMapper implements Function<CSVRecord, ExerciseSet> {
    private final Map<String, Exercise> exercisesByName;
    private final Map<Long, Workout> workoutsById;

    @Override
    public ExerciseSet apply(CSVRecord record) {
        return ExerciseSet.builder()
                .weightKg(getDouble(record, "Weight (kg)"))
                .setOrder(getInt(record, "Set Order"))
                .reps(getInt(record, "Reps"))
                .rpe(getDouble(record, "RPE"))
                .distanceM(getDouble(record, "Distance (meters)"))
                .seconds(getInt(record, "Seconds"))
                .workout(workoutsById.get(Long.parseLong(record.get("Workout #"))))
                .exercise(exercisesByName.get(record.get("Exercise Name")))
                .build();
    }

    private Double getDouble(CSVRecord csvRecord, String header) {
        return csvRecord.get(header).isEmpty() ? null : Double.parseDouble(csvRecord.get(header));
    }
    private Integer getInt(CSVRecord csvRecord, String header) {
        return csvRecord.get(header).isEmpty() ? null : Integer.parseInt(csvRecord.get(header));
    }

}
