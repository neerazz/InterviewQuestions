package com.real.matcher;

import com.real.matcher.domain.FileDataConverter;
import com.real.matcher.domain.filters.MovieMatcher;
import com.real.matcher.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.real.matcher.utils.StringUtils.normalize;

public class MatcherImpl implements Matcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatcherImpl.class);

    private Map<Integer, Movie> moviesById = new HashMap<>();
    private Map<String, Set<Integer>> moviesByTitle = new HashMap<>();
    private Map<Integer, Set<Integer>> moviesByYear = new HashMap<>();
    private Map<String, Set<Integer>> moviesByActor = new HashMap<>();
    private Map<String, Set<Integer>> moviesByDirector = new HashMap<>();
    private final MovieMatcher movieMatcher;

    public MatcherImpl(CsvStream movieDb, CsvStream actorAndDirectorDb) {
        LOGGER.info("Initializing MatcherImpl with movie and actor/director databases.");
//        Here, I am creating multiple maps;
//          The intention is to have a constant time search.
//          In the case of SQL:
//              I would be indexing the table with fields
//                  1. movie_tile
//                  2. movie_year
//                  3. actor_name
//                  4. director_name
        processMovies(movieDb);
        processActors(actorAndDirectorDb);
        movieMatcher = new MovieMatcher(moviesByTitle, moviesByYear, moviesByActor, moviesByDirector);

    }

    private void processMovies(CsvStream movieDb) {
        movieDb.getDataRows().map(FileDataConverter::parseMovie)
                .forEach(movie -> {
                    String title = normalize(movie.getTitle());
                    Integer movieId = movie.getId();
                    moviesById.put(movieId, movie);

                    moviesByTitle.putIfAbsent(title, new HashSet<>());
                    moviesByTitle.get(title).add(movieId);

                    moviesByYear.putIfAbsent(movie.getYear(), new HashSet<>());
                    moviesByYear.get(movie.getYear()).add(movieId);
                });
    }

    private void processActors(CsvStream actorAndDirectorDb) {
        actorAndDirectorDb.getDataRows()
                .map(FileDataConverter::parsePerson)
                .forEach(person -> {
                    String personName = normalize(person.getName());
                    Integer movieId = person.getMovieId();
                    moviesByActor.putIfAbsent(personName, new HashSet<>());
                    moviesByActor.get(personName).add(movieId);

                    if (person.getRole().equalsIgnoreCase("director")) {
                        moviesByDirector.putIfAbsent(personName, new HashSet<>());
                        moviesByDirector.get(personName).add(movieId);
                    }
                });
    }

    @Override
    public List<IdMapping> match(DatabaseType databaseType, CsvStream externalDb) {
        Collection<IdMapping> idMappings = externalDb.getDataRows()
                .map(stream -> FileDataConverter.parseExternalMovieLine(databaseType, stream))
                .map(movieMatcher::findBestMatch)
                .filter(Objects::nonNull)
                // Use a LinkedHashMap to preserve the order of elements
                .collect(Collectors.toMap(IdMapping::getExternalId, Function.identity(), (existing, replacement) -> existing, LinkedHashMap::new))
                .values();
        return new ArrayList<>(idMappings);
    }

}
