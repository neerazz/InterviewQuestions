package com.real.matcher.domain.filters;

import com.real.matcher.model.ExternalMovie;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.real.matcher.utils.StringUtils.hasValue;
import static com.real.matcher.utils.StringUtils.normalize;


public class TitleFilter implements FilterStrategy {


    Map<String, Set<Integer>> moviesByTitle;

    public TitleFilter(Map<String, Set<Integer>> moviesByTitle) {
        this.moviesByTitle = moviesByTitle;
    }

    @Override
    public Set<Integer> filter(ExternalMovie externalMovie) {
        String title = externalMovie.getTitle();
        if (!hasValue(title)) {
            return new HashSet<>();
        }
        String normalized = normalize(title);
        return moviesByTitle.getOrDefault(normalized, new HashSet<>());
    }
}
