package tvseriesapp;

/**
 * A simple data model to hold information about a single TV series.
 * Using integer types for age and episodes allows for easier validation and calculations.
 */
public class SeriesModel {
    public String seriesId;
    public String seriesName;
    public int seriesAgeRestriction;
    public int seriesNumberOfEpisodes;
}