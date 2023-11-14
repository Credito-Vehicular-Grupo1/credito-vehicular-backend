package com.grupo1.creditovehicular.plan.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double van;

    @NotNull
    private double tir;

    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY)
    private Plan plan;
}
