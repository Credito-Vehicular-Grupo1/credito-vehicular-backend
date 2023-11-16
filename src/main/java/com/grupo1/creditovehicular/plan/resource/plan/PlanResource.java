package com.grupo1.creditovehicular.plan.resource.plan;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlanResource {
    private Long userId;
    private Long id;
    private String periodoGracia;
    private String tipoTasa;
    private String tipoMoneda;
    private float costoTotal;
    private float cuotaInicial;
    private int nPeriodoGracia;
    private double tasa;
    private double cok;
    private int nCuotas;
}
