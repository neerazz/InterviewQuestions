package com.real.matcher.domain.filters;

import com.real.matcher.model.ExternalMovie;

import java.util.Set;


public interface FilterStrategy {

    Set<Integer> filter(ExternalMovie externalMovie);
}
