package com.real.matcher.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ExternalMovie {

    private String country;
    private String studioNetwork;
    private String mediaId;
    private String title;
    private LocalDateTime originalReleaseDate;
    private String mediaType;
    private String seriesMediaId;
    private String seasonMedaId;

    private String seriesTitle;
    private Integer seasonNumber;
    private Integer episodeNumber;

    private String licenseType;
    private Double actualRetailPrice;
    private LocalDateTime offerStartDate;
    private LocalDateTime offerEndDate;
    private List<String> actors;
    private String director;
    private String xboxLiveURL;

}
