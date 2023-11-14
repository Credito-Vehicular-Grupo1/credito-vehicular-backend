package com.grupo1.creditovehicular.plan.resource.result;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateResultResource {
    @NotNull
    private double van;

    @NotNull
    private double tir;
}
