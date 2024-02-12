package com.real.matcher.domain;

import com.real.matcher.Matcher;
import com.real.matcher.model.ExternalMovie;
import com.real.matcher.model.Movie;
import com.real.matcher.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileDataConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDataConverter.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm:ss a");

    public static Movie parseMovie(String line) {
        String pattern = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] parts = line.split(pattern);
        Integer id = parseInteger(parts[0].trim());
        String title = parts[1].trim();
        Integer year = parseInteger(parts[2].trim());
        return Movie.builder()
                .id(id)
                .title(title)
                .year(year)
                .build();
    }

    private static Integer parseInteger(String intStr) {
        try {
            return (intStr.isEmpty() || intStr.equalsIgnoreCase("null")) ? null : Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            LOGGER.error("Error parsing integer value: {}", intStr, e);
            return null;
        }
    }

    public static Person parsePerson(String line) {
        String pattern = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] parts = line.split(pattern);
        Integer movieId = parseInteger(parts[0].trim());
        String name = parts[1].trim();
        String role = parts[2].trim();
        return Person.builder()
                .movieId(movieId)
                .name(name)
                .role(role)
                .build();
    }

    private static List<String> parseActors(String actorsStr) {
        return actorsStr.isEmpty() ? new ArrayList<>() : Arrays.asList(actorsStr.split("\\|"));
    }

    public static ExternalMovie parseExternalMovieLine(Matcher.DatabaseType databaseType, String line) {
//        I can have multiple implementation, based on the Database Type;
//          Like:
//              XBOX, can Have a XBOXExternalMovie,
//              VUDU can have VUDUExternalMovie, Object.
//          Currently, I have assumed that we have only one object type.
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] parts = line.split(regex, -1); // Splitting with -1 to preserve trailing empty strings


        return ExternalMovie.builder()
                .country(parts[0].trim())
                .studioNetwork(parts[1].trim().isEmpty() ? null : parts[1].trim())
                .mediaId(parts[2].trim())
                .title(parts[3].trim())
                .originalReleaseDate(parseDateTime(parts[4].trim()))
                .mediaType(parts[5].trim())
                .seriesMediaId(parts[6].trim().isEmpty() ? null : parts[6].trim())
                .seasonMedaId(parts[7].trim().isEmpty() ? null : parts[7].trim())
                .seriesTitle(parts[8].trim().isEmpty() ? null : parts[8].trim())
                .seasonNumber(parseInteger(parts[9].trim()))
                .episodeNumber(parseInteger(parts[10].trim()))
                .licenseType(parts[11].trim().isEmpty() ? null : parts[11].trim())
                .actualRetailPrice(parseDouble(parts[12].trim()))
                .offerStartDate(parseDateTime(parts[13].trim()))
                .offerEndDate(parseDateTime(parts[14].trim()))
                .actors(parseActors(parts[15].trim()))
                .director(parts[16].trim().isEmpty() ? null : parts[16].trim())
                .xboxLiveURL(parts[17].trim().isEmpty() ? null : parts[17].trim())
                .build();
    }

    private static Double parseDouble(String doubleStr) {
        try {
            return doubleStr.isEmpty() ? null : Double.parseDouble(doubleStr);
        } catch (NumberFormatException e) {
            LOGGER.error("Error parsing double value: {}", doubleStr, e);
            return null;
        }
    }

    private static LocalDateTime parseDateTime(String dateStr) {
        if (dateStr.isEmpty()) return null;
        return LocalDateTime.parse(dateStr, formatter);
    }

}
