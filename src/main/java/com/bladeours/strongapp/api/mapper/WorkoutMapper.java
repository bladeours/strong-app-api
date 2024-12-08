package com.bladeours.strongapp.api.mapper;

import com.bladeours.strongapp.api.model.entity.Workout;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@RequiredArgsConstructor
public class WorkoutMapper implements Function<CSVRecord, Workout> {

    @Override
    public Workout apply(CSVRecord record) {
        return Workout.builder()
                .name(record.get("Workout Name"))
                .id(getLong(record, "Workout #"))
                .date(getLocalDateTime(record, "Date"))
                .durationSec(getInt(record, "Duration (sec)"))
                .notes(record.get("Workout Notes"))
                .build();
    }

    private LocalDateTime getLocalDateTime(CSVRecord csvRecord, String header) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");
        return LocalDateTime.parse(csvRecord.get(header), formatter);
    }
    private Integer getInt(CSVRecord csvRecord, String header) {
        return csvRecord.get(header).isEmpty() ? null : Integer.parseInt(csvRecord.get(header));
    }
    private Long getLong(CSVRecord csvRecord, String header) {
        return csvRecord.get(header).isEmpty() ? null : Long.parseLong(csvRecord.get(header));
    }

}
