package com.bladeours.strongapp.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = "exerciseSets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @EqualsAndHashCode.Include
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @Setter
    private List<ExerciseSet> exerciseSets = new ArrayList<>();
}
