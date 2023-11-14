package com.grupo1.creditovehicular.plan.resource.plan;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlanResource {
    private Long id;
    private String tipoTasa;
    private String tipoMoneda;
    private float costoTotal;
    private float cuotaInicial;
    private double tasa;
    private int nCuotas;
}
