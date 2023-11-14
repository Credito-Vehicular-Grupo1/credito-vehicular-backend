package com.grupo1.creditovehicular.plan.service;

import com.grupo1.creditovehicular.plan.domain.model.entity.Result;
import com.grupo1.creditovehicular.plan.domain.persistence.ResultRepository;
import com.grupo1.creditovehicular.plan.domain.service.ResultService;
import com.grupo1.creditovehicular.shared.exception.ResourceNotFoundException;
import com.grupo1.creditovehicular.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ResultServiceIn implements ResultService {
    private static final String ENTITY = "Result";

    private final ResultRepository resultRepository;
    private final Validator validator;

    public ResultServiceIn(ResultRepository resultRepository, Validator validator) {
        this.resultRepository = resultRepository;
        this.validator = validator;
    }

    @Override
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    @Override
    public Page<Result> getAll(Pageable pageable) {
        return resultRepository.findAll(pageable);
    }

    @Override
    public Result create(Result result) {
        Set<ConstraintViolation<Result>> violations = validator.validate(result);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return resultRepository.save(result);
    }

    @Override
    public Result update(Long resultId, Result request) {
        Set<ConstraintViolation<Result>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return resultRepository.findById(resultId).map(result ->
                resultRepository.save(result.withVan(request.getVan())
                        .withTir(request.getTir())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, resultId));
    }

    @Override
    public ResponseEntity<?> delete(Long resultId) {
        return resultRepository.findById(resultId).map(
                result -> {
                    resultRepository.delete(result);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, resultId));
    }
}
