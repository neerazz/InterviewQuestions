package com.real.matcher.domain.filters;

import com.real.matcher.model.ExternalMovie;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.real.matcher.utils.StringUtils.hasValue;
import static com.real.matcher.utils.StringUtils.normalize;


public class DirectorFilter implements FilterStrategy {


    Map<String, Set<Integer>> moviesByDirector;

    public DirectorFilter(Map<String, Set<Integer>> moviesByDirector) {
        this.moviesByDirector = moviesByDirector;
    }

    @Override
    public Set<Integer> filter(ExternalMovie externalMovie) {
        String director = externalMovie.getDirector();
        if (!hasValue(director)) {
            return new HashSet<>();
        }
        String normalized = normalize(director);
        return moviesByDirector.getOrDefault(normalized, new HashSet<>());
    }
}
