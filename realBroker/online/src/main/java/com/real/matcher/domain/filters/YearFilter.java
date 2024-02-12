package com.real.matcher.domain.filters;

import com.real.matcher.model.ExternalMovie;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class YearFilter implements FilterStrategy {


    Map<Integer, Set<Integer>> moviesByYear;

    public YearFilter(Map<Integer, Set<Integer>> moviesByYear) {
        this.moviesByYear = moviesByYear;
    }

    @Override
    public Set<Integer> filter(ExternalMovie externalMovie) {
        LocalDateTime releaseDate = externalMovie.getOriginalReleaseDate();
        if (releaseDate == null) {
            return new HashSet<>();
        }
        int year = releaseDate.getYear();
        return moviesByYear.getOrDefault(year, new HashSet<>());
    }
}
