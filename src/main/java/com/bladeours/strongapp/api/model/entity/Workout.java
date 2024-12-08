package com.bladeours.strongapp.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(exclude = "exerciseSets")
@ToString(exclude = "exerciseSets")
public class Workout {
    @Id
    private Long id;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer durationSec;
    @Column
    private String notes;
    @OneToMany(fetch = FetchType.EAGER)
    @Setter
    private List<ExerciseSet> exerciseSets;
}
