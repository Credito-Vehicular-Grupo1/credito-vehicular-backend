package com.grupo1.creditovehicular.plan.domain.service;

import com.grupo1.creditovehicular.plan.domain.model.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResultService {
    List<Result> getAll();
    Page<Result> getAll(Pageable pageable);
    Result create(Result result);
    Result update(Long resultId, Result result);
    ResponseEntity<?> delete(Long resultId);
}
