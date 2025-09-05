package tvseriesapp;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Contains all the business logic for managing the TV series collection.
 */
public class Series {

    private final ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int idCounter = 1; // Used to generate unique IDs like SER001, SER002, etc.

    /**
     * Captures details for a new TV series and saves it to the list.
     */
    public void captureSeries() {
        SeriesModel newSeries = new SeriesModel();

        // Automatically generate a unique, formatted ID.
        newSeries.seriesId = String.format("SER%03d", idCounter++);

        System.out.println("\n--- Capture a New TV Series ---");
        System.out.println("Generated Series ID: " + newSeries.seriesId);

        System.out.print("Enter Series Name: ");
        newSeries.seriesName = scanner.nextLine();

        // Use a robust helper method to get a valid age.
        newSeries.seriesAgeRestriction = captureValidAge("Enter Age Restriction (2-18): ");

        // Use a robust helper method to get a valid number of episodes.
        newSeries.seriesNumberOfEpisodes = captureValidInteger("Enter Number of Episodes: ");

        seriesList.add(newSeries);
        System.out.println("\n>> Series details have been successfully saved. <<");
    }

    /**
     * Searches for a TV series by its ID and displays the details if found.
     */
    public void searchSeries() {
        System.out.println("\n--- Search for a TV Series ---");
        System.out.print("Enter the Series ID to search for (e.g., SER001): ");
        String searchId = scanner.nextLine();

        // Use a reusable helper method to find the series.
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

    /**
     * Finds a series and allows the user to update its details.
     */
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
    
    /**
     * Finds a series and deletes it after user confirmation.
     */
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
                // removeIf is a safe and efficient way to remove items from a list.
                seriesList.removeIf(s -> s.seriesId.equalsIgnoreCase(searchId));
                System.out.println("\n>> Series has been successfully deleted. <<");
            } else {
                System.out.println("\n>> Deletion cancelled. <<");
            }
        } else {
            System.out.println("\n>> Error: No series data could be found for ID '" + searchId + "'. <<");
        }
    }
    
    /**
     * Displays a formatted report of all series currently stored in memory.
     */
    public void seriesReport() {
        System.out.println("\n===================================================================");
        System.out.println("                          SERIES REPORT                          ");
        System.out.println("===================================================================");
        
        if (seriesList.isEmpty()) {
            System.out.println("              No series data available to display.               ");
        } else {
            // Use printf for clean, column-aligned formatting.
            System.out.printf("%-12s %-30s %-15s %-10s%n", "SERIES ID", "SERIES NAME", "AGE RESTRICTION", "EPISODES");
            System.out.println("-------------------------------------------------------------------");
            for (SeriesModel series : seriesList) {
                System.out.printf("%-12s %-30s %-15d %-10d%n", 
                        series.seriesId, 
                        series.seriesName, 
                        series.seriesAgeRestriction, 
                        series.seriesNumberOfEpisodes);
            }
        }
        System.out.println("===================================================================");
    }
    
    /**
     * Prints an exit message.
     */
    public void exitSeriesApplication() {
        System.out.println("\nThank you for using the TV Series Management Application. Goodbye! 👋");
    }

    //-------------------------------------------------------------------------//
    // PRIVATE HELPER METHODS (for internal use, improving code quality)
    //-------------------------------------------------------------------------//

    /**
     * A reusable helper method to find a series by its ID.
     * This avoids repeating the same search logic in multiple places.
     * @param seriesId The ID to search for.
     * @return An Optional containing the series if found, otherwise an empty Optional.
     */
    Optional<SeriesModel> findSeriesById(String seriesId) {
        return seriesList.stream()
                .filter(s -> s.seriesId.equalsIgnoreCase(seriesId))
                .findFirst();
    }
    
    /**
     * A robust helper method to capture any positive integer from the user.
     * It handles non-numeric input and ensures the number is greater than zero.
     * @param prompt The message to display to the user.
     * @return A valid positive integer.
     */
    private int captureValidInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Error: Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter numbers only.");
            }
        }
    }

    /**
     * A robust helper method specifically for capturing the age restriction.
     * It ensures the input is a number and falls within the valid range (2-18).
     * @param prompt The message to display to the user.
     * @return A valid age between 2 and 18.
     */
    private int captureValidAge(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int age = Integer.parseInt(input);
                if (age >= 2 && age <= 18) {
                    return age;
                } else {
                    System.out.println("Error: Invalid age. Please enter a number between 2 and 18.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter numbers only.");
            }
        }
    }

    void addSeriesForTesting(SeriesModel testSeries1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}