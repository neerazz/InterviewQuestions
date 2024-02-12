package com.real.matcher.domain.filters;

import com.real.matcher.Matcher;
import com.real.matcher.model.ExternalMovie;

import java.util.*;

public class MovieMatcher {

    private List<FilterStrategy> filters;
    private Map<String, Set<Integer>> moviesByTitle;
    private Map<Integer, Set<Integer>> moviesByYear;
    private Map<String, Set<Integer>> moviesByActor;
    private Map<String, Set<Integer>> moviesByDirector;

    public MovieMatcher(Map<String, Set<Integer>> moviesByTitle,
                        Map<Integer, Set<Integer>> moviesByYear,
                        Map<String, Set<Integer>> moviesByActor,
                        Map<String, Set<Integer>> moviesByDirector) {
        this.moviesByTitle = moviesByTitle;
        this.moviesByYear = moviesByYear;
        this.moviesByActor = moviesByActor;
        this.moviesByDirector = moviesByDirector;
        filters = new ArrayList<>();
        createFilters();
    }

    private void createFilters() {
        filters.add(new TitleFilter(moviesByTitle));
        filters.add(new YearFilter(moviesByYear));
        filters.add(new ActorNameFilter(moviesByActor));
        filters.add(new DirectorFilter(moviesByDirector));
    }

    public Matcher.IdMapping findBestMatch(ExternalMovie externalMovie) {
        // Implement matching logic based on title, year, director, and cast.
        String externalMedia = externalMovie.getMediaId();
        Set<Integer> filteredMovies = new HashSet<>();

        for (FilterStrategy filterStrategy : filters) {
            Set<Integer> currentResults = filterStrategy.filter(externalMovie);
            filteredMovies = getCommonMovies(filteredMovies, currentResults);
        }
        return filteredMovies.stream().findFirst()
                .map(internalMovieId -> new Matcher.IdMapping(internalMovieId, externalMedia))
                .orElse(null);
    }

    private Set<Integer> getCommonMovies(Set<Integer> movieIds1, Set<Integer> movieIds2) {
        boolean movies1HasValues = !movieIds1.isEmpty();
        boolean movies2HasValues = !movieIds2.isEmpty();
        if (movies1HasValues && movies2HasValues) {
            Set<Integer> intersectSet = new HashSet<>(movieIds1);
            intersectSet.retainAll(movieIds2);
            return intersectSet;
        } else if (movies1HasValues) {
            return movieIds1;
        } else {
            return movieIds2;
        }
    }
}
