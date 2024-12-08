package com.bladeours.strongapp.api.controller;

import com.bladeours.strongapp.api.model.dto.WorkoutDto;
import com.bladeours.strongapp.api.service.CSVService;
import com.bladeours.strongapp.api.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final CSVService csvService;
    private final WorkoutService workoutService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleCSVFile(@RequestParam("file") MultipartFile file) throws IOException {
        csvService.putDataInDatabase(file);
        return "essunia";
    }

    @GetMapping("/workouts")
    public List<WorkoutDto> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping(value = "/workouts", params = {"from", "to"})
    public List<WorkoutDto> getWorkoutBetween(@RequestParam @DateTimeFormat LocalDateTime from,
                                              @RequestParam @DateTimeFormat LocalDateTime to) {
        return workoutService.getWorkouts(from, to);
    }

}
