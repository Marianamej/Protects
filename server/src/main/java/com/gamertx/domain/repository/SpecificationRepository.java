package com.gamertx.domain.repository;

import com.gamertx.domain.dto.Specification;

import java.util.Optional;

public interface SpecificationRepository {
    Optional<Specification> getAll(int id);
}
