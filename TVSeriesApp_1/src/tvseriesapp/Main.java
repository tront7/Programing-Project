package tvseriesapp;
import java.util.Scanner;
public class Main  
{
public static void main(String[] args) {
        Scanner menuScanner = new Scanner(System.in);
        Series seriesManager = new Series();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- TV Series Management Menu ---");
            System.out.println("1. Capture a new TV series");
            System.out.println("2. Search for a TV series");
            System.out.println("3. Update a TV series");
            System.out.println("4. Delete a TV series");
            System.out.println("5. View series report");
            System.out.println("6. Exit Application");
            System.out.print("Please enter your choice: ");

            String choice = menuScanner.nextLine();

            switch (choice) {
                case "1":
                    seriesManager.captureSeries();
                    break;
                case "2":
                    seriesManager.searchSeries();
                    break;
                case "3":
                    seriesManager.updateSeries();
                    break;
                case "4":
                    seriesManager.deleteSeries();
                    break;
                case "5":
                    seriesManager.seriesReport();
                    break;
                case "6":
                    seriesManager.exitSeriesApplication();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        menuScanner.close();
    }   
}
