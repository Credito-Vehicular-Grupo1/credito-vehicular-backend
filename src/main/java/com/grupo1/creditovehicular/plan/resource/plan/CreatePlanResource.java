package com.grupo1.creditovehicular.plan.resource.plan;

import com.grupo1.creditovehicular.user.domain.model.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanResource {
    @NotBlank
    @NotNull
    @Column(name = "periodo_gracia")
    private String periodoGracia;

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
    @Column(name = "numero_periodo_gracia")
    private int numeroPeriodoGracia;

    @NotNull
    private double tasa;

    @NotNull
    private double cok;

    @NotNull
    private int periodo;

}
