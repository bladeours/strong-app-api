package com.bladeours.strongapp.api.mapper;

import com.bladeours.strongapp.api.model.entity.Exercise;
import org.apache.commons.csv.CSVRecord;

import java.util.function.Function;

public class ExerciseMapper implements Function<CSVRecord, Exercise> {
    @Override
    public Exercise apply(CSVRecord csvRecord) {
        return Exercise.builder()
                .name(csvRecord.get("Exercise Name"))
                .build();
    }
}
