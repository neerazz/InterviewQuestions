package com.real.matcher.domain.filters;

import com.real.matcher.model.ExternalMovie;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.real.matcher.utils.StringUtils.hasValue;
import static com.real.matcher.utils.StringUtils.normalize;


public class ActorNameFilter implements FilterStrategy {


    Map<String, Set<Integer>> moviesByActor;

    public ActorNameFilter(Map<String, Set<Integer>> moviesByActor) {
        this.moviesByActor = moviesByActor;
    }

    @Override
    public Set<Integer> filter(ExternalMovie externalMovie) {
        List<String> actors = externalMovie.getActors();
        if (!hasValue(actors)) {
            return new HashSet<>();
        }
        Set<Integer> result = new HashSet<>();
        for (String actorName : actors) {
            String normalized = normalize(actorName);
            result.addAll(moviesByActor.getOrDefault(normalized, new HashSet<>()));
        }
        return result;
    }
}
