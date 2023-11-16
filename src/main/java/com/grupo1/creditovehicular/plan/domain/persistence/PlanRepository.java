package com.grupo1.creditovehicular.plan.domain.persistence;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByUserId(Long userId);

    @Query("SELECT p FROM Plan p WHERE p.id = :planId AND p.user.id = :userId")
    Optional<Plan> findByIdAndUserId(@Param("planId") Long planId, @Param("userId") Long userId);
}
