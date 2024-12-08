package com.bladeours.strongapp.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class ExerciseSet {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Integer setOrder;
    @Column
    private Double weightKg;
    @Column
    private Integer reps;
    @Column
    private Double rpe;
    @Column
    private Double distanceM;
    @Column
    private Integer seconds;
    @ManyToOne
    private Workout workout;
    @ManyToOne
    private Exercise exercise;
}
