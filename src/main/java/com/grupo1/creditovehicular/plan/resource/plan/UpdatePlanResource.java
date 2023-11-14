package com.grupo1.creditovehicular.plan.resource.plan;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlanResource {
    @NotBlank
    @NotNull
    @Column(name = "tipo_tasa")
    private String tipoTasa;

    @NotBlank
    @NotNull
    @Column(name = "tipo_moneda")
    private String tipoMoneda;

    @NotNull
    @Column(name = "costo_total")
    private float costoTotal;

    @NotNull
    @Column(name = "cuota_inicial")
    private float cuotaInicial;

    @NotNull
    private double tasa;

    @NotNull
    private int nCuotas;
}
