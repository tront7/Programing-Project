// Paste this corrected code into your Series.java file

package tvseriesapp;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Series {

    private final ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int idCounter = 1;

    // ... (keep all your other methods like captureSeries, searchSeries, etc. as they are)

    public void captureSeries() {
        SeriesModel newSeries = new SeriesModel();
        newSeries.seriesId = String.format("SER%03d", idCounter++);
        System.out.println("\n--- Capture a New TV Series ---");
        System.out.println("Generated Series ID: " + newSeries.seriesId);
        System.out.print("Enter Series Name: ");
        newSeries.seriesName = scanner.nextLine();
        newSeries.seriesAgeRestriction = captureValidAge("Enter Age Restriction (2-18): ");
        newSeries.seriesNumberOfEpisodes = captureValidInteger("Enter Number of Episodes: ");
        seriesList.add(newSeries);
        System.out.println("\n>> Series details have been successfully saved. <<");
    }

    public void searchSeries() {
        System.out.println("\n--- Search for a TV Series ---");
        System.out.print("Enter the Series ID to search for (e.g., SER001): ");
        String searchId = scanner.nextLine();
        Optional<SeriesModel> foundSeries = findSeriesById(searchId);
        if (foundSeries.isPresent()) {
            SeriesModel series = foundSeries.get();
            System.out.println("\n--- Series Found ---");
            System.out.println("Series ID             : " + series.seriesId);
            System.out.println("Series Name           : " + series.seriesName);
            System.out.println("Age Restriction       : " + series.seriesAgeRestriction);
            System.out.println("Number of Episodes    : " + series.seriesNumberOfEpisodes);
        } else {
            System.out.println("\n>> Error: No series data could be found for ID '" + searchId + "'. <<");
        }
    }

    public void updateSeries() {
        System.out.println("\n--- Update a TV Series ---");
        System.out.print("Enter the Series ID to update: ");
        String searchId = scanner.nextLine();
        Optional<SeriesModel> seriesToUpdate = findSeriesById(searchId);
        if (seriesToUpdate.isPresent()) {
            SeriesModel series = seriesToUpdate.get();
            System.out.println("Found! Updating details for: " + series.seriesName);
            System.out.print("Enter New Series Name: ");
            series.seriesName = scanner.nextLine();
            series.seriesAgeRestriction = captureValidAge("Enter New Age Restriction (2-18): ");
            series.seriesNumberOfEpisodes = captureValidInteger("Enter New Number of Episodes: ");
            System.out.println("\n>> Series has been successfully updated. <<");
        } else {
            System.out.println("\n>> Error: No series data could be found for ID '" + searchId + "'. <<");
        }
    }
    
    public void deleteSeries() {
        System.out.println("\n--- Delete a TV Series ---");
        System.out.print("Enter the Series ID to delete: ");
        String searchId = scanner.nextLine();
        Optional<SeriesModel> seriesToDelete = findSeriesById(searchId);
        if (seriesToDelete.isPresent()) {
            SeriesModel series = seriesToDelete.get();
            System.out.print("Are you sure you want to delete '" + series.seriesName + "'? (Y/N): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")) {
                seriesList.removeIf(s -> s.seriesId.equalsIgnoreCase(searchId));
                System.out.println("\n>> Series has been successfully deleted. <<");
            } else {
                System.out.println("\n>> Deletion cancelled. <<");
            }
        } else {
            System.out.println("\n>> Error: No series data could be found for ID '" + searchId + "'. <<");
        }
    }
    
    public void seriesReport() {
        System.out.println("\n===================================================================");
        System.out.println("                          SERIES REPORT                          ");
        System.out.println("===================================================================");
        if (seriesList.isEmpty()) {
            System.out.println("              No series data available to display.               ");
        } else {
            System.out.printf("%-12s %-30s %-15s %-10s%n", "SERIES ID", "SERIES NAME", "AGE RESTRICTION", "EPISODES");
            System.out.println("-------------------------------------------------------------------");
            for (SeriesModel series : seriesList) {
                System.out.printf("%-12s %-30s %-15d %-10d%n", 
                        series.seriesId, series.seriesName, 
                        series.seriesAgeRestriction, series.seriesNumberOfEpisodes);
            }
        }
        System.out.println("===================================================================");
    }
    
    public void exitSeriesApplication() {
        System.out.println("\nThank you for using the TV Series Management Application. Goodbye! 👋");
    }

    private int captureValidInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number > 0) return number;
                else System.out.println("Error: Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter numbers only.");
            }
        }
    }

    private int captureValidAge(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (isAgeRestrictionValid(input)) {
                return Integer.parseInt(input);
            }
        }
    }

    // =======================================================================
    // == METHODS ADDED FOR JUNIT TESTING ====================================
    // =======================================================================

    // FIXED: Added 'public'
    public Optional<SeriesModel> findSeriesById(String seriesId) {
        return seriesList.stream()
                .filter(s -> s.seriesId.equalsIgnoreCase(seriesId))
                .findFirst();
    }
    
    // FIXED: Added 'public'
    public void addSeriesForTesting(SeriesModel series) {
        this.seriesList.add(series);
    }

    // FIXED: Added 'public'
    public ArrayList<SeriesModel> getSeriesList() {
        return this.seriesList;
    }

    public boolean isAgeRestrictionValid(String input) {
        try {
            int age = Integer.parseInt(input);
            if (age >= 2 && age <= 18) {
                return true;
            } else {
                System.out.println("Error: Invalid age. Please enter a number between 2 and 18.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter numbers only.");
            return false;
        }
    }
}