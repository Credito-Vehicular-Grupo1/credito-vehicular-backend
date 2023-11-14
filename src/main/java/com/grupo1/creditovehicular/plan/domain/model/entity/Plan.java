package com.grupo1.creditovehicular.plan.domain.model.entity;

import com.grupo1.creditovehicular.user.domain.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private double tasa;

    @NotNull
    private int nCuotas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    private Result result;
}
