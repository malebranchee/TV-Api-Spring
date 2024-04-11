package com.example.lab5;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

@NonNullApi
public interface TvRepository extends CrudRepository<TvEntity, Integer> {
}
